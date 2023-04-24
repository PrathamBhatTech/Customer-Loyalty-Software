package ooad.customerloyalty.rewardmanager.rewardmanager.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "points")
public class Points {
    @Id
    private String id;
    
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Points is required")
    @Min(value = 0, message = "Points must be greater than 0")
    private int points;

    @NotBlank(message = "Total Points is required")
    @Min(value = 0, message = "Total Points must be greater than 0")
    private int totalPoints;

    private String membershipTier;


    public Points(String username, int points, int totalPoints, String membershipTier) {
        this.username = username;
        this.points = points;
        this.totalPoints = totalPoints;
        this.membershipTier = membershipTier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(String membershipTier) {
        this.membershipTier = membershipTier;
    }
}
