package ooad.customerloyalty.rewardmanager.rewardmanager;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Points;
import ooad.customerloyalty.rewardmanager.rewardmanager.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component("pointFunctions")
public class PointFunctions {


    Logger logger = Logger.getLogger("transactions");
    FileHandler fh;

    private final PointsRepository pointsRepository;

    private String username;

    Map<String, Integer> pointConversionDict = new HashMap<String, Integer>();

    @Autowired
    public PointFunctions(@Qualifier("PointsRepository") PointsRepository pointsRepository) throws IOException {
        this.pointsRepository = pointsRepository;

        try    {
            fh = new FileHandler("transactions.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("Reward Manager Started");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        pointConversionDict.put("Bronze", 200);
        pointConversionDict.put("Silver", 100);
        pointConversionDict.put("Gold", 50);
        pointConversionDict.put("Platinum", 25);
        
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Points getPoints() {
        return pointsRepository.findPointsByUsername(username);
    }

    public void createUser(String username) {
        Points points = new Points(username, 0, 0, "Bronze");
        pointsRepository.save(points);
    }

    public String addPoints(Transaction transaction) {
        Points points = getPoints();

        int pointConversion = getPointConversion();

        int npoints = points.getPoints();
        npoints += transaction.getPrice()/pointConversion - transaction.getPointsRedeemed();
        points.setPoints(npoints);
        points.setTotalPoints(points.getTotalPoints() + transaction.getPrice()/pointConversion);

        pointsRepository.save(points);

        String message = "Transaction of amount " + transaction.getPrice() +
                " done by " + username +
                " at " + LocalDateTime.now() +
                " with " + transaction.getPointsRedeemed() +
                " points redeemed" +
                " to Merchant " + transaction.getMerchant();
        logger.info(message);


        checkMembership();

        return "Points added successfully";
    }

    public void checkMembership() {
        Points points = getPoints();

        points.setMembershipTier(getMembershipTier());

        pointsRepository.save(points);
    }

    public String getMembershipTier() {
        Points points = getPoints();
        int totalPoints = points.getTotalPoints();

        if (totalPoints < 5000) {
            return "Bronze";
        } else if (totalPoints < 10000) {
            return "Silver";
        } else if (totalPoints < 20000) {
            return "Gold";
        } else {
            return "Platinum";
        }
    }

    // Get the point conversion metric according to membership
    public int getPointConversion() {
        Points points = getPoints();

        return pointConversionDict.get(points.getMembershipTier());
    }

    public List<String> getLogs()    {
        List<String> logs = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("transactions.log"));
            while (scanner.hasNextLine()) {
                logs.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
