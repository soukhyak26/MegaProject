package com.affaince.distribution.sampler;

import com.affaince.distribution.profiles.DefaultShippingProfile;
import com.affaince.distribution.profiles.DistributionZone;
import com.affaince.distribution.profiles.DistributionZoneGroup;

import java.util.HashMap;
import java.util.Map;

public class ZoneGroupWiseDeliveriesDistribution {
    private final String merchantId;
    private final String zoneGroupId;
    private final Map<WeightIndicator, Long> weightWiseDeliveriesDistributionMap;
    private final Map<DistributionZone, ZoneWiseDeliveriesDistribution> zoneWiseDeliveriesDistributionMap;
    private final Map<DistributionZone, Double> zoneWiseDistributionExpense;
    private double totalDistributionExpensePerZoneGroup;


    public ZoneGroupWiseDeliveriesDistribution(DefaultShippingProfile shippingProfile, String zoneGroupId) {
        this.merchantId = shippingProfile.getMerchantId();
        this.zoneGroupId = zoneGroupId;
        this.weightWiseDeliveriesDistributionMap = new HashMap<>();
        this.zoneWiseDeliveriesDistributionMap = new HashMap<>();
        this.zoneWiseDistributionExpense = new HashMap<>();
        DistributionZoneGroup zoneGroup = shippingProfile.getDistributionZoneGroups().stream().filter(dzg -> dzg.getDistributionZoneGroupIdentifier().equals(zoneGroupId)).findFirst().get();
        for (DistributionZone zone : zoneGroup.getDistributionZonesUnderTheGroup()) {
            this.zoneWiseDeliveriesDistributionMap.put(zone, new ZoneWiseDeliveriesDistribution(shippingProfile, zone.getDistributionZoneIdentifier()));
            this.zoneWiseDistributionExpense.put(zone, null);
        }
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

    public void addWeightWiseDelivery(WeightIndicator weightIndicator, Long deliveryCount) {
        if (!weightWiseDeliveriesDistributionMap.containsKey(weightIndicator)) {
            weightWiseDeliveriesDistributionMap.put(weightIndicator, deliveryCount);
        }
        long currentDeliveryCount = weightWiseDeliveriesDistributionMap.get(weightIndicator);
        weightWiseDeliveriesDistributionMap.put(weightIndicator, currentDeliveryCount + deliveryCount);
        distributeDeliveriesOfAPeriodAcrossZones(weightIndicator, deliveryCount);
    }

    public void distributeDeliveriesOfAPeriodAcrossZones(WeightIndicator weightIndicator, long deliveryCount) {
        for (DistributionZone zone : this.zoneWiseDeliveriesDistributionMap.keySet()) {
            if (deliveryCount > 0) {
                ZoneWiseDeliveriesDistribution zoneWiseDeliveriesDistribution = this.zoneWiseDeliveriesDistributionMap.get(zone);
                long deliveryCountShare = deliveryCount / this.zoneWiseDeliveriesDistributionMap.keySet().size();
                zoneWiseDeliveriesDistribution.addWeightWiseDelivery(weightIndicator, deliveryCountShare);
                deliveryCount -= deliveryCountShare;
            }
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
