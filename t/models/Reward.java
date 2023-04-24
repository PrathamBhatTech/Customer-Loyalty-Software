package ooad.customerloyalty.rewardmanager.rewardmanager.models;

public class Reward {
    private String id;
    private String name;
    private String description;
    private int pointsRequired;

    public Reward() {
    }

    public Reward(String id, String name, String description, int pointsRequired) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pointsRequired = pointsRequired;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(int pointsRequired) {
        this.pointsRequired = pointsRequired;
    }
}