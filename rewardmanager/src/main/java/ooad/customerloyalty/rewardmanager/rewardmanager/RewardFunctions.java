package ooad.customerloyalty.rewardmanager.rewardmanager;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Reward;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RewardFunctions {
    private final RewardRepository rewardRepository;

    Map<String, Integer> discountDict = new HashMap<String, Integer>();

    public RewardFunctions(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;

        discountDict.put("Bronze", 10);
        discountDict.put("Silver", 15);
        discountDict.put("Gold", 30);
        discountDict.put("Platinum", 50);
    }

    public Reward getLeastSoldReward() {
        return rewardRepository.findFirstByOrderByUsoldAsc();
    }


    public int getDiscount(String tier) {
        return discountDict.get(tier);
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

}
