package com.affaince.subscription.business.command.event;

import org.apache.tomcat.jni.Local;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 30-01-2017.
 */
//domain inner event
public class ExcessProfitAddedToNodalAccountEvent {
    private Integer businessAccountId;
    private String productId;
    private double excessProfit;
    private LocalDate dateOfTransaction;
    public ExcessProfitAddedToNodalAccountEvent(Integer businessAccountId, String productId, double excessProfit, LocalDate dateOfTransaction) {
        this.businessAccountId=businessAccountId;
        this.productId=productId;
        this.excessProfit=excessProfit;
        this.dateOfTransaction=dateOfTransaction;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public String getProductId() {
        return productId;
    }

    public double getExcessProfit() {
        return excessProfit;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }
}
