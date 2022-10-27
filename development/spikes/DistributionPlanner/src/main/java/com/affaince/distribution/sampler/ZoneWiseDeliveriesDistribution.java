package com.affaince.distribution.sampler;


import com.affaince.distribution.db.DeliveryForecastView;
import com.affaince.distribution.profiles.DefaultShippingProfile;
import com.affaince.distribution.profiles.DistributionZone;
import com.affaince.distribution.profiles.RatePerUnitWeight;

import java.util.*;

public class ZoneWiseDeliveriesDistribution {
    private final String merchantId;
    private final String zoneId;
    private final Map<WeightIndicator, List<DeliveryForecastView>> weightWiseDeliveriesDistributionMap;
    private final Map<WeightIndicator, Double> totalDistributionExpensePerWeightRange;
    private double totalDistributionExpenseInAZone;


    public ZoneWiseDeliveriesDistribution(String merchantId, String zoneId) {
        this.merchantId = merchantId;
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

    public Map<WeightIndicator, List<DeliveryForecastView>> getWeightWiseDeliveriesDistributionMap() {
        return weightWiseDeliveriesDistributionMap;
    }

    public void addWeightWiseDelivery(WeightIndicator weightIndicator, DeliveryForecastView deliveryForecastView) {
        if (!weightWiseDeliveriesDistributionMap.containsKey(weightIndicator)) {
            List<DeliveryForecastView> deliveries = new ArrayList<>();
            weightWiseDeliveriesDistributionMap.put(weightIndicator, deliveries);
        }
        weightWiseDeliveriesDistributionMap.get(weightIndicator).add(deliveryForecastView);
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
            List<DeliveryForecastView> deliveriesInAWeightRange = weightWiseDeliveriesDistributionMap.get(weight);
            RatePerUnitWeight ratePerUnitWeight = zone.getRatesInTheZone().stream().filter(rIz -> rIz.getMaxWeight() == weight.getNetQuantity()).findFirst().get();
            double totalWeightInAWeightCategoryTobeDistributed = deliveriesInAWeightRange.stream().mapToDouble(dfv -> dfv.getDeliveryForecastVersionId().getWeightRangeMax()).sum();
            double totalDistributionExpenseForAWeighRange = totalWeightInAWeightCategoryTobeDistributed * ratePerUnitWeight.getRateInCurrency();
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
                ", weightWiseDeliveriesDistributionMap=" + weightWiseDeliveriesDistributionMap +
                ", totalDistributionExpensePerWeightRange=" + totalDistributionExpensePerWeightRange +
                ", totalDistributionExpenseInAZone=" + totalDistributionExpenseInAZone +
                '}';
    }
}
