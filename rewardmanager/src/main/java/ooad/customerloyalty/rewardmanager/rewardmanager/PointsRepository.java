package ooad.customerloyalty.rewardmanager.rewardmanager;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Points;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("PointsRepository")
public interface PointsRepository extends MongoRepository<Points, String>   {
    @Query("{username:'?0'}")
    Points findPointsByUsername(String username);

    @Query("{}")
    List<Points> getAllPoints();

}
