package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class BasketItemInfo {
    private String productId;
    private int countPerPeriod;
    private Period period;
    private int noOfCycles;
    private double basketItemCountPerMonth;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCountPerPeriod() {
        return countPerPeriod;
    }

    public void setCountPerPeriod(int countPerPeriod) {
        this.countPerPeriod = countPerPeriod;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getNoOfCycles() {
        return noOfCycles;
    }

    public void setNoOfCycles(int noOfCycles) {
        this.noOfCycles = noOfCycles;
    }

    public double getBasketItemCountPerMonth() {
        return basketItemCountPerMonth;
    }

    public void setBasketItemCountPerMonth(double basketItemCountPerMonth) {
        this.basketItemCountPerMonth = basketItemCountPerMonth;
    }

}
