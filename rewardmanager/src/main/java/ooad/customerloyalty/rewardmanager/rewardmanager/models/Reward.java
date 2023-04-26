package ooad.customerloyalty.rewardmanager.rewardmanager.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rewards")
public class Reward {
    private String name;
    private int cost;
    private int usold;

    public Reward(String name, int cost, int usold) {
        this.name = name;
        this.cost = cost;
        this.usold = usold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getUsold() {
        return usold;
    }

    public void setUsold(int usold) {
        this.usold = usold;
    }
}