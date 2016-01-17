package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class SubscriptionItem {
    private String productId;
    private int countPerPeriod;
    private Period period;
    private double discountedOfferedPrice;
    private double offeredPriceWithBasketLevelDiscount;
    private int noOfCycles;
    private double subscriptionItemCountPerMonth;

    public SubscriptionItem(String productId, int countPerPeriod, Period period, double discountedOfferedPrice, double offeredPriceWithBasketLevelDiscount, int noOfCycles) {
        this.productId = productId;
        this.countPerPeriod = countPerPeriod;
        this.period = period;
        this.discountedOfferedPrice = discountedOfferedPrice;
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
        this.noOfCycles = noOfCycles;
    }

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

    public double getDiscountedOfferedPrice() {
        return discountedOfferedPrice;
    }

    public void setDiscountedOfferedPrice(double discountedOfferedPrice) {
        this.discountedOfferedPrice = discountedOfferedPrice;
    }

    public double getOfferedPriceWithBasketLevelDiscount() {
        return offeredPriceWithBasketLevelDiscount;
    }

    public void setOfferedPriceWithBasketLevelDiscount(double offeredPriceWithBasketLevelDiscount) {
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
    }

    public int getNoOfCycles() {
        return noOfCycles;
    }

    public void setNoOfCycles(int noOfCycles) {
        this.noOfCycles = noOfCycles;
    }

    public void calculateMonthlyBasketItemCount(){
        if(this.getPeriod().getUnit()== PeriodUnit.WEEK){
            subscriptionItemCountPerMonth =(this.getCountPerPeriod()/this.getPeriod().getValue())*4;
        }else if(this.getPeriod().getUnit()== PeriodUnit.MONTH){
            subscriptionItemCountPerMonth =this.getCountPerPeriod()/ this.period.getValue();
        }
    }
}
