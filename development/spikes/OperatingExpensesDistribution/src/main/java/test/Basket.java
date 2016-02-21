package test;

import java.util.List;

/**
 * Created by rsavaliya on 21/2/16.
 */
public class Basket {
    private List<Item> items;
    private double totalWeight;
    private double totalPurchasePrice;
    private int totalDeliveryCharges;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalPurchasePrice() {
        return totalPurchasePrice;
    }

    public void setTotalPurchasePrice(double totalPurchasePrice) {
        this.totalPurchasePrice = totalPurchasePrice;
    }

    public int getTotalDeliveryCharges() {
        return totalDeliveryCharges;
    }

    public void setTotalDeliveryCharges(int totalDeliveryCharges) {
        this.totalDeliveryCharges = totalDeliveryCharges;
    }

    public void calculateItemLevelDeliveryCharges() {
        for (Item item: items) {
            double totalPurchasePriceOfItem = item.getPurchasePrice()*item.getQuantity();
            item.setDeliveryCharges((totalPurchasePriceOfItem*totalDeliveryCharges)/totalPurchasePrice);
        }
    }
}
