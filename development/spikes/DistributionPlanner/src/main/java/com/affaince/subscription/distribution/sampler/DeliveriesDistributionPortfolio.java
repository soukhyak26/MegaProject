package com.affaince.subscription.distribution.sampler;

import com.affaince.subscription.distribution.categories.ZoneGroupWiseDeliveriesDistribution;
import com.affaince.subscription.distribution.profiles.DefaultShippingProfile;
import com.affaince.subscription.distribution.profiles.DistributionZoneGroup;

import java.util.HashMap;
import java.util.Map;

public class DeliveriesDistributionPortfolio {
    private final Period period;
    private final Map<DistributionZoneGroup, Double> zoneGroupWiseDistributionExpensesPerPeriod;
    private Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> zoneGroupWiseDeliveriesDistributionsPerPeriod;
    private double totalDistributionExpensePerPeriod;

    public DeliveriesDistributionPortfolio(Period period) {
        this.period = period;
        this.zoneGroupWiseDeliveriesDistributionsPerPeriod = new HashMap<>();
        this.zoneGroupWiseDistributionExpensesPerPeriod = new HashMap<>();
    }

    public Period getPeriod() {
        return period;
    }

    public Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> getZoneGroupWiseDeliveriesDistributionsPerPeriod() {
        return zoneGroupWiseDeliveriesDistributionsPerPeriod;
    }


    public void setZoneGroupWiseDeliveriesDistributionsPerPeriod(Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> zoneGroupWiseDeliveriesDistributionsPerPeriod) {
        this.zoneGroupWiseDeliveriesDistributionsPerPeriod = zoneGroupWiseDeliveriesDistributionsPerPeriod;
    }

    public double computeTotalDistributionExpense(DefaultShippingProfile profile) {
        for (DistributionZoneGroup zoneGroup : this.zoneGroupWiseDeliveriesDistributionsPerPeriod.keySet()) {
            ZoneGroupWiseDeliveriesDistribution distribution = this.zoneGroupWiseDeliveriesDistributionsPerPeriod.get(zoneGroup);
            double zoneGroupWiseDistributionExpenses = distribution.computeTotalDistributionExpensePerZoneGroup(profile);
            this.totalDistributionExpensePerPeriod += zoneGroupWiseDistributionExpenses;
            this.zoneGroupWiseDistributionExpensesPerPeriod.put(zoneGroup, zoneGroupWiseDistributionExpenses);
        }
        return this.totalDistributionExpensePerPeriod;
    }

    @Override
    public String toString() {
        return "DeliveriesDistributionProfile{" +
                "period=" + period +
                ", zoneGroupWiseDistributionExpensesPerPeriod=" + zoneGroupWiseDistributionExpensesPerPeriod +
                ", zoneGroupWiseDeliveriesDistributionsPerPeriod=" + zoneGroupWiseDeliveriesDistributionsPerPeriod +
                ", totalDistributionExpensePerPeriod=" + totalDistributionExpensePerPeriod +
                '}';
    }
}
