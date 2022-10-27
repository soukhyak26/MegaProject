package com.affaince.distribution.sampler;

import com.affaince.distribution.profiles.DefaultShippingProfile;
import com.affaince.distribution.profiles.DistributionZoneGroup;

import java.util.HashMap;
import java.util.Map;

public class DeliveriesDistributionProfile {
    private final Period period;
    private final Map<DistributionZoneGroup, Double> zoneGroupWiseDistributionExpensesPerPeriod;
    private Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> zoneGroupWiseDeliveriesDistributionsPerPeriod;
    private double totalDistributionExpensePerPeriod;

    public DeliveriesDistributionProfile(Period period) {
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
}
