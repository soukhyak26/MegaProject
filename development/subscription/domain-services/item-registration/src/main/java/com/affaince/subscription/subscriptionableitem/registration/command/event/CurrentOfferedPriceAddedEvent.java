package com.affaince.subscription.subscriptionableitem.registration.command.event;

/**
 * Created by rbsavaliya on 07-08-2015.
 */
public class CurrentOfferedPriceAddedEvent {

    private String itemId;
    private double currentOfferedPrice;

    public CurrentOfferedPriceAddedEvent(String itemId, double currentOfferedPrice) {
        this.itemId = itemId;
        this.currentOfferedPrice = currentOfferedPrice;
    }

    public CurrentOfferedPriceAddedEvent() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getCurrentOfferedPrice() {
        return currentOfferedPrice;
    }

    public void setCurrentOfferedPrice(double currentOfferedPrice) {
        this.currentOfferedPrice = currentOfferedPrice;
    }
}
