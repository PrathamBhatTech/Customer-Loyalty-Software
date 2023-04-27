package ooad.customerloyalty.rewardmanager.rewardmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ooad.customerloyalty.rewardmanager.rewardmanager.models.Points;
import ooad.customerloyalty.rewardmanager.rewardmanager.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ooad.customerloyalty.rewardmanager.rewardmanager.models.Reward;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/rewards")
public class RewardController {
//    @Autowired
//    private RewardRepository rewardRepository;
    @Autowired
    private PointsRepository pointsRepository;

    @Autowired
    private final PointFunctions pointFunctions = new PointFunctions(pointsRepository);

    @Autowired
    private RewardRepository rewardRepository;
    @Autowired
    private final RewardFunctions rewardFunctions = new RewardFunctions(rewardRepository);

    ObjectMapper mapper = new ObjectMapper();

    Logger logger = Logger.getLogger("transactions");
    FileHandler fh;
    Date date = new Date();
    public RewardController() throws IOException {
    }


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

    @GetMapping("/get-rewards")
    @CrossOrigin
    public List<Reward> getRewards()    {
        return rewardFunctions.getAllRewards();
    }

    @GetMapping("/get-reward")
    @CrossOrigin
    public Reward getReward()    {
        return rewardFunctions.getLeastSoldReward();
    }

    @GetMapping("/get-discount")
    @CrossOrigin
    public int getDiscount()    {
        return rewardFunctions.getDiscount(pointFunctions.getMembershipTier());
    }

    @GetMapping("/get-logs")
    @CrossOrigin
    public List<String> getLogs() throws JsonProcessingException {
        return pointFunctions.getLogs();
    }
}