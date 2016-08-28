package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.payments.command.accounting.TotalBalanceAccount;
import com.affaince.subscription.payments.command.accounting.TotalReceivableCostAccount;
import com.affaince.subscription.payments.command.accounting.TotalReceivedCostAccount;
import com.affaince.subscription.payments.command.accounting.TotalSubscriptionCostAccount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by anayonkar on 21/8/16.
 */
@Document(collection = "SubscriptionPaymentView")
public class SubscriptionPaymentView {
    @Id
    private String subscriptionId;
    private TotalBalanceAccount totalBalanceAccount;
    private TotalSubscriptionCostAccount totalSubscriptionCostAccount;
    private TotalReceivableCostAccount totalReceivableCostAccount;
    private TotalReceivedCostAccount totalReceivedCostAccount;

    public SubscriptionPaymentView() {
        this.totalSubscriptionCostAccount = new TotalSubscriptionCostAccount(0);
        this.totalReceivableCostAccount = new TotalReceivableCostAccount(0);
    }

    public SubscriptionPaymentView(String subscriptionId, Double totalBalance) {
        this();
        this.subscriptionId = subscriptionId;
        this.totalBalanceAccount = new TotalBalanceAccount(totalBalance);
        this.totalReceivedCostAccount = new TotalReceivedCostAccount(totalBalance);
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public TotalBalanceAccount getTotalBalanceAccount() {
        return totalBalanceAccount;
    }

    public TotalSubscriptionCostAccount getTotalSubscriptionCostAccount() {
        return totalSubscriptionCostAccount;
    }

    public TotalReceivableCostAccount getTotalReceivableCostAccount() {
        return totalReceivableCostAccount;
    }

    public TotalReceivedCostAccount getTotalReceivedCostAccount() {
        return totalReceivedCostAccount;
    }
}
