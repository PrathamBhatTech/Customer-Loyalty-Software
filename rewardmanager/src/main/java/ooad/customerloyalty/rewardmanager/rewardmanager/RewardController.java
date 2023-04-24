package ooad.customerloyalty.rewardmanager.rewardmanager;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Points;
import ooad.customerloyalty.rewardmanager.rewardmanager.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
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
    @CrossOrigin
    public Points getPoints()    {
        return pointFunctions.getPoints();
    }

    @PostMapping("/transaction")
    @CrossOrigin
    public String transaction(@RequestBody Transaction transaction)    {
        return pointFunctions.addPoints(transaction);
    }

    @PostMapping("/set-user")
    @CrossOrigin
    public void setUser(@RequestBody String username)    {
        pointFunctions.setUsername(username);
    }

    @PostMapping("/new-user")
    public void newUser(@RequestBody String username)    {
        pointFunctions.createUser(username);
    }
}