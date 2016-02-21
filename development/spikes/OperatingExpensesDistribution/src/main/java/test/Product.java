package test;

/**
 * Created by rsavaliya on 21/2/16.
 */
public class Product {
    private String id;
    private double purchasePrice;
    private double weight;
    private int totalDeliveryCharges;

    public Product(String id, double purchasePrice, double weight) {
        this.id = id;
        this.purchasePrice = purchasePrice;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getWeight() {
        return weight;
    }

    public int getTotalDeliveryCharges() {
        return totalDeliveryCharges;
    }

    public void setTotalDeliveryCharges(int totalDeliveryCharges) {
        this.totalDeliveryCharges = totalDeliveryCharges;
    }
}
