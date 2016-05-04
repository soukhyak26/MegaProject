package com.affaince.subscription.business.provision;

/**
 * Created by anayonkar on 1/5/16.
 */
public enum ProvisionIndex {
    PURCHASE_COST(0),
    LOSSES(1),
    BENEFITS(2),
    TAXES(3),
    OTHERS(4),
    COMMON_EXPENSES(5),
    NODAL_ACCOUNT(6),
    REVENUE(7),
    BOOKING_AMOUNT(8),
    SUBSCRIPTION_SPECIFIC_EXPENSES(9),
    MAX_CAPACITY(10);
    private int index;

    ProvisionIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
