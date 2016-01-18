package com.affaince.subscription.subscriber.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rsavaliya on 9/1/16.
 */
@Document(collection = "SubscriptionSummaryView")
public class SubscriptionSummaryView {

    @Id
    private Integer year;
    private double totalSubscriptionCount;
    private double totalSubscriptionValue;
    private double bookedSubscriptionRevenue;
    private double actualSubscriptionRevenue;
    private double totalSubscriptionProfit;

    public SubscriptionSummaryView(Integer year, double totalSubscriptionCount, double totalSubscriptionValue, double bookedSubscriptionRevenue, double actualSubscriptionRevenue, double totalSubscriptionProfit) {
        this.year = year;
        this.totalSubscriptionCount = totalSubscriptionCount;
        this.totalSubscriptionValue = totalSubscriptionValue;
        this.bookedSubscriptionRevenue = bookedSubscriptionRevenue;
        this.actualSubscriptionRevenue = actualSubscriptionRevenue;
        this.totalSubscriptionProfit = totalSubscriptionProfit;
    }

    public SubscriptionSummaryView() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public double getTotalSubscriptionCount() {
        return totalSubscriptionCount;
    }

    public void setTotalSubscriptionCount(double totalSubscriptionCount) {
        this.totalSubscriptionCount = totalSubscriptionCount;
    }

    public double getTotalSubscriptionValue() {
        return totalSubscriptionValue;
    }

    public void setTotalSubscriptionValue(double totalSubscriptionValue) {
        this.totalSubscriptionValue = totalSubscriptionValue;
    }

    public double getBookedSubscriptionRevenue() {
        return bookedSubscriptionRevenue;
    }

    public void setBookedSubscriptionRevenue(double bookedSubscriptionRevenue) {
        this.bookedSubscriptionRevenue = bookedSubscriptionRevenue;
    }

    public double getActualSubscriptionRevenue() {
        return actualSubscriptionRevenue;
    }

    public void setActualSubscriptionRevenue(double actualSubscriptionRevenue) {
        this.actualSubscriptionRevenue = actualSubscriptionRevenue;
    }

    public double getTotalSubscriptionProfit() {
        return totalSubscriptionProfit;
    }

    public void setTotalSubscriptionProfit(double totalSubscriptionProfit) {
        this.totalSubscriptionProfit = totalSubscriptionProfit;
    }
}
