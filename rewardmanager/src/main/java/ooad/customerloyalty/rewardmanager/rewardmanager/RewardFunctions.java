package ooad.customerloyalty.rewardmanager.rewardmanager;

import org.springframework.stereotype.Component;

@Component
public class RewardFunctions {
    private final RewardRepository rewardRepository;

    public RewardFunctions(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

}
