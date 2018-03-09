package com.verifier.domains.business.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SubscriptionInfoView")
public class SubscriptionInfoView {
    @Id
    private String subscriptionId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalSubscriptionAmount;
    private double bookingAmount;
    private double currentDueAmount;

    public SubscriptionInfoView(String subscriptionId, LocalDate startDate, LocalDate endDate, double totalSubscriptionAmount) {
        this.subscriptionId = subscriptionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalSubscriptionAmount = totalSubscriptionAmount;
        this.bookingAmount=0;
        this.currentDueAmount=totalSubscriptionAmount;
    }


    public String getSubscriptionId() {
        return subscriptionId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalSubscriptionAmount() {
        return totalSubscriptionAmount;
    }

    public void setTotalSubscriptionAmount(double totalSubscriptionAmount) {
        this.totalSubscriptionAmount = totalSubscriptionAmount;
    }

    public double getCurrentDueAmount() {
        return currentDueAmount;
    }

    public void setCurrentDueAmount(double currentDueAmount) {
        this.currentDueAmount = currentDueAmount;
    }

    public double getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(double bookingAmount) {
        this.bookingAmount = bookingAmount;
    }
}
