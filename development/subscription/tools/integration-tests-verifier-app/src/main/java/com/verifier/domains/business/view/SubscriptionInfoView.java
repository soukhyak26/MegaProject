package com.verifier.domains.business.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SubscriptionInfoView")
public class SubscriptionInfoView {
    @Id
    private String subscriptionId;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private double totalSubscriptionAmount;
    private double bookingAmount;
    private double currentDueAmount;

    public SubscriptionInfoView(){}
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
