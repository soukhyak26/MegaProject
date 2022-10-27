package com.affaince.distribution.sampler;

import com.affaince.distribution.profiles.DefaultShippingProfile;
import com.affaince.distribution.profiles.DistributionZone;

import java.util.HashMap;
import java.util.Map;

public class ZoneGroupWiseDeliveriesDistribution {
    private final String merchantId;
    private final String zoneGroupId;
    private final Map<DistributionZone, ZoneWiseDeliveriesDistribution> zoneWiseDeliveriesDistributionMap;
    private final Map<DistributionZone, Double> zoneWiseDistributionExpense;
    private double totalDistributionExpensePerZoneGroup;


    public ZoneGroupWiseDeliveriesDistribution(String merchantId, String zoneGroupId) {
        this.merchantId = merchantId;
        this.zoneGroupId = zoneGroupId;
        this.zoneWiseDeliveriesDistributionMap = new HashMap<>();
        this.zoneWiseDistributionExpense = new HashMap<>();

    }

    public String getZoneGroupId() {
        return zoneGroupId;
    }

    public Map<DistributionZone, ZoneWiseDeliveriesDistribution> getZoneWiseDeliveriesDistributionMap() {
        return zoneWiseDeliveriesDistributionMap;
    }


    public void addZoneWiseDelivery(DistributionZone zone, ZoneWiseDeliveriesDistribution zoneWiseDeliveriesDistribution) {
        if (!zoneWiseDeliveriesDistributionMap.containsKey(zone)) {
            zoneWiseDeliveriesDistributionMap.put(zone, zoneWiseDeliveriesDistribution);
        }
    }

    public double computeTotalDistributionExpensePerZoneGroup(DefaultShippingProfile profile) {
        for (DistributionZone zone : this.zoneWiseDeliveriesDistributionMap.keySet()) {
            ZoneWiseDeliveriesDistribution zoneWiseDeliveriesDistribution = zoneWiseDeliveriesDistributionMap.get(zone);
            double zoneWiseDistributionExpenses = zoneWiseDeliveriesDistribution.computeTotalDistributionExpensePerZone(profile);
            this.totalDistributionExpensePerZoneGroup += zoneWiseDistributionExpenses;
            this.zoneWiseDistributionExpense.put(zone, zoneWiseDistributionExpenses);
        }
        return this.totalDistributionExpensePerZoneGroup;
    }

    @Override
    public String toString() {
        return "ZoneGroupWiseDeliveriesDistribution{" +
                "merchantId='" + merchantId + '\'' +
                ", zoneGroupId='" + zoneGroupId + '\'' +
                ", zoneWiseDeliveriesDistributionMap=" + zoneWiseDeliveriesDistributionMap +
                ", zoneWiseDistributionExpense=" + zoneWiseDistributionExpense +
                ", totalDistributionExpensePerZoneGroup=" + totalDistributionExpensePerZoneGroup +
                '}';
    }
}
