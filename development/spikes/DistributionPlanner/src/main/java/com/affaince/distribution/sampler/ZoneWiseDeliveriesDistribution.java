package com.affaince.distribution.sampler;


import com.affaince.distribution.profiles.DefaultShippingProfile;
import com.affaince.distribution.profiles.DistributionZone;
import com.affaince.distribution.profiles.RatePerUnitWeight;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ZoneWiseDeliveriesDistribution {
    private final String merchantId;
    private final String zoneId;
    private final Map<WeightIndicator, Long> weightWiseDeliveriesDistributionMap;
    private final Map<WeightIndicator, Double> totalDistributionExpensePerWeightRange;
    private double totalDistributionExpenseInAZone;


    public ZoneWiseDeliveriesDistribution(DefaultShippingProfile shippingProfile, String zoneId) {
        this.merchantId = shippingProfile.getMerchantId();
        this.zoneId = zoneId;
        this.weightWiseDeliveriesDistributionMap = new HashMap<>();
        this.totalDistributionExpensePerWeightRange = new HashMap<>();
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public Map<WeightIndicator, Long> getWeightWiseDeliveriesDistributionMap() {
        return weightWiseDeliveriesDistributionMap;
    }

    public void addWeightWiseDelivery(WeightIndicator weightIndicator, Long deliveryCount) {
        if (!weightWiseDeliveriesDistributionMap.containsKey(weightIndicator)) {
            weightWiseDeliveriesDistributionMap.put(weightIndicator, deliveryCount);
        }
        long currentDeliveryCount = weightWiseDeliveriesDistributionMap.get(weightIndicator);
        weightWiseDeliveriesDistributionMap.put(weightIndicator, currentDeliveryCount + deliveryCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneWiseDeliveriesDistribution that = (ZoneWiseDeliveriesDistribution) o;
        return merchantId.equals(that.merchantId) &&
                zoneId.equals(that.zoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantId, zoneId);
    }

    public double computeTotalDistributionExpensePerZone(DefaultShippingProfile defaultShippingProfile) {
        DistributionZone zone = defaultShippingProfile.searchForDistributionZone(this.zoneId);
        for (WeightIndicator weight : weightWiseDeliveriesDistributionMap.keySet()) {
            Long deliveryCount = weightWiseDeliveriesDistributionMap.get(weight);
            RatePerUnitWeight ratePerUnitWeight = zone.getRatesInTheZone().stream().filter(rIz -> rIz.getMaxWeight() == weight.getNetQuantity()).findFirst().get();
            //double totalWeightInAWeightCategoryTobeDistributed = deliveryCount * weight.getNetQuantity();
            double totalDistributionExpenseForAWeighRange = deliveryCount * ratePerUnitWeight.getRateInCurrency();
            this.totalDistributionExpenseInAZone += totalDistributionExpenseForAWeighRange;
            this.totalDistributionExpensePerWeightRange.put(weight, totalDistributionExpenseForAWeighRange);
        }
        return this.totalDistributionExpenseInAZone;
    }

    @Override
    public String toString() {
        return "ZoneWiseDeliveriesDistribution{" +
                "merchantId='" + merchantId + '\'' +
                ", zoneId='" + zoneId + '\'' +
                //", weightWiseDeliveriesDistributionMap=" + weightWiseDeliveriesDistributionMap +
                ", totalDistributionExpensePerWeightRange=" + totalDistributionExpensePerWeightRange +
                ", totalDistributionExpenseInAZone=" + totalDistributionExpenseInAZone +
                '}';
    }
}
