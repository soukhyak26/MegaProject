package test;

/**
 * Created by rsavaliya on 21/2/16.
 */
public class Item {
    private String itemId;
    private int quantity;
    private double purchasePrice;
    private double deliveryCharges;

    public Item(String itemId, int quantity, double purchasePrice) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(double deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }
}
