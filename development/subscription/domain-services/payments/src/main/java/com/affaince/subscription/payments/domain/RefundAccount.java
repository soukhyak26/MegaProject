package com.affaince.subscription.payments.domain;

import com.affaince.subscription.payments.domain.accounting.Account;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 6/10/2017.
 */
//TODO: Due correction engine is expected to provide information regarding refunds???
public class RefundAccount extends Account {
    private String subscriptionId;

    public RefundAccount(String subscriptionId,double totalReceivableCost,LocalDate costChangeDate) {
        super(totalReceivableCost,costChangeDate);
        this.subscriptionId=subscriptionId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

}
