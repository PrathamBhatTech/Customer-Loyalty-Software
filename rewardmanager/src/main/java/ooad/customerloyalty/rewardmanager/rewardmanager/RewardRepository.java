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
    @Query("{name:'?0'}")
    Reward findRewardByName(String name);

    @Query("{}")
    List<Reward> getAllRewards();

    // get reward that has the least value for unitsSold
//    Reward findTopByOrderByUsoldAsc();
    Reward findFirstByOrderByUsoldAsc();

}
