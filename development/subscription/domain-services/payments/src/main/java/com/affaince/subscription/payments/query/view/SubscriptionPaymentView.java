package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.payments.command.accounting.TotalBalanceAccount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by anayonkar on 21/8/16.
 */
@Document(collection = "SubscriptionPaymentView")
public class SubscriptionPaymentView {
    @Id
    private String subscriptionId;
    private Double totalBalance;
    private TotalBalanceAccount totalBalanceAccount;

    public SubscriptionPaymentView() {
        this.totalBalanceAccount = new TotalBalanceAccount(0);
    }

    public SubscriptionPaymentView(String subscriptionId, Double totalBalance) {
        this();
        this.subscriptionId = subscriptionId;
        this.totalBalance = totalBalance;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public TotalBalanceAccount getTotalBalanceAccount() {
        return totalBalanceAccount;
    }
}
