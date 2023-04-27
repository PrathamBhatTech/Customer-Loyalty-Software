package ooad.customerloyalty.rewardmanager.rewardmanager.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("transaction")
public class Transaction {
    private String merchant;
    private int price;
    private int pointsRedeemed;

    public Transaction(String merchant, int price, int pointsRedeemed) {
        this.merchant = merchant;
        this.price = price;
        this.pointsRedeemed = pointsRedeemed;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPointsRedeemed() {
        return pointsRedeemed;
    }

    public void setPointsRedeemed(int pointsRedeemed) {
        this.pointsRedeemed = pointsRedeemed;
    }
}
