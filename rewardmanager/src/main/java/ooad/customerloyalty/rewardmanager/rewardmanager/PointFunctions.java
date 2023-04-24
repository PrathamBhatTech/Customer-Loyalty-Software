package ooad.customerloyalty.rewardmanager.rewardmanager;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Points;
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
        Points points = new Points(username, 0);
        pointsRepository.save(points);
    }

    public String addPoints(int price) {
        Points points = getPoints();

        int npoints = points.getPoints();
        npoints += price/100;
        points.setPoints(npoints);

        pointsRepository.save(points);

        return "Points added successfully";
    }
}
