package com.affaince.subscription.inventory.query.view;

import com.affaince.subscription.inventory.command.domain.SubscriptionBasketStatus;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandark on 22-08-2015.
 */
public class BasketView {

    private String basketId;
    private String subscriberId;
    private List<String> subscriptionableItemIds;
    private double totalBasketPrice;
    private LocalDate dispatchDate;
    private int dispatchStatus;
    private int reasonCode;

    public String getBasketId() {
        return this.basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getSubscriberId() {
        return this.subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public List<String> getSubscriptionableItemIds() {
        return this.subscriptionableItemIds;
    }

    public void setSubscriptionableItemIds(List<String> subscriptionableItemIds) {
        this.subscriptionableItemIds = subscriptionableItemIds;
    }

    public double getTotalBasketPrice() {
        return this.totalBasketPrice;
    }

    public void setTotalBasketPrice(double totalBasketPrice) {
        this.totalBasketPrice = totalBasketPrice;
    }

    public LocalDate getDispatchDate() {
        return this.dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public int getDispatchStatus() {
        return this.dispatchStatus;
    }

    public void setDispatchStatus(int dispatchStatus) {
        this.dispatchStatus = dispatchStatus;
    }

    public int getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }
}
