package com.affaince.payments.dummy;

public class DummyEvent5 {
    private Integer numberOfRenewals;
    private Integer numberOfModificationsAllSubscriptions;
    private Integer totalDeliveries;

    public DummyEvent5(Integer numberOfRenewals, Integer numberOfModificationsAllSubscriptions,Integer totalDeliveries) {
        this.numberOfRenewals = numberOfRenewals;
        this.numberOfModificationsAllSubscriptions = numberOfModificationsAllSubscriptions;
        this.totalDeliveries = totalDeliveries;
    }

    public Integer getNumberOfRenewals() {
        return numberOfRenewals;
    }

    public Integer getNumberOfModificationsAllSubscriptions() {
        return numberOfModificationsAllSubscriptions;
    }

    public Integer getTotalDeliveries() {
        return totalDeliveries;
    }
}
