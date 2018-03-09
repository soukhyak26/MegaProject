package com.verifier.domains.business.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 12/4/2017.
 */
@Document(collection="SubscriptionReceivableAccountView")
public class SubscriptionReceivableAccountView {
    @Id
    private String businessAccountId;
    private double receivableAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubscriptionReceivableAccountView(String businessAccountId, double receivableAmount, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.receivableAmount = receivableAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public double getReceivableAmount() {
        return receivableAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void credit(double amountToBeAdded){
        this.receivableAmount +=amountToBeAdded;
    }

    public void debit(double amountTobeDebited){
        this.receivableAmount-=amountTobeDebited;
    }
}
