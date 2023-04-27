package ooad.customerloyalty.rewardmanager.rewardmanager;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Reward;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("rewardRepository")
public interface RewardRepository extends MongoRepository<Reward, String> {
    @Query("{id:'?0'}")
    Reward findRewardById(String id);

    @Query("{}")
    List<Reward> getAllRewards();
}
