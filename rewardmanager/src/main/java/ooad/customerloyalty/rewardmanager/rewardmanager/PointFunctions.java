package ooad.customerloyalty.rewardmanager.rewardmanager;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Points;
import ooad.customerloyalty.rewardmanager.rewardmanager.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@Component("pointFunctions")
public class PointFunctions {
    private final PointsRepository pointsRepository;

    private String username;

    @Autowired
    public PointFunctions(@Qualifier("PointsRepository") PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
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

        checkMembership();

        return "Points added successfully";
    }

    // TODO: Check total points everytime a transcation is done and update the membership tier accordingly
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

        return switch (points.getMembershipTier()) {
            case "Bronze" -> 200;
            case "Silver" -> 100;
            case "Gold" -> 80;
            case "Platinum" -> 25;
            default -> 10000;
        };
    }
}
