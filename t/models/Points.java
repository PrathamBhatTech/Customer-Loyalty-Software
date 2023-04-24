package ooad.customerloyalty.rewardmanager.rewardmanager.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "points")
public class Points {
    @Id
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Points is required")
    @Min(value = 0, message = "Points must be greater than 0")
    private int points;


    public Points(String username, int points) {
        this.username = username;
        this.points = points;
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
}
