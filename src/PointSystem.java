public class PointSystem {
    public int points;

    public PointSystem(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void deductPoints(int points) {
        this.points -= points;
    }

    public int getRewards() {
        return this.points / 100;
    }
}