package ooad.customerloyalty.rewardmanager.rewardmanager;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Points;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("PointFunctions")
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

    public int getPoints() {
        return pointsRepository.findPointsByUsername(username).getPoints();
    }

    public String addPoints(int price) {
        int points = getPoints();
        points = points + price/10;
        pointsRepository.save(new Points(username, points));
        return "Points added successfully";
    }
}
