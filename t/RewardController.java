package ooad.customerloyalty.rewardmanager.rewardmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rewards")
public class RewardController {
//    @Autowired
//    private RewardRepository rewardRepository;
    @Autowired
    private PointsRepository pointsRepository;

    @Autowired
    private final PointFunctions pointFunctions = new PointFunctions(pointsRepository);

//    @Autowired
//    private final RewardFunctions rewardFunctions = new RewardFunctions(rewardRepository);

//    @GetMapping("/get-rewards")
//    public List<Reward> getRewards()    {
//        return rewardRepository.getAllRewards();
//    }

    @GetMapping("/get-points")
    public int getPoints()    {
        return pointFunctions.getPoints();
    }

    @PostMapping("/transaction")
    public String transaction(@RequestBody int price, String username)    {
        return pointFunctions.addPoints(price);
    }

    @PostMapping("/set-user")
    public void setUser(@RequestBody String username)    {
        pointFunctions.setUsername(username);
    }
}