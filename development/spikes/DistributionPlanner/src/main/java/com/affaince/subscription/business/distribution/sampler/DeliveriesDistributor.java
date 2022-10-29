package com.affaince.subscription.business.distribution.sampler;

import com.affaince.subscription.business.distribution.categories.PeriodWiseAndWeightWiseDeliveriesDistribution;
import com.affaince.subscription.business.distribution.categories.ZoneGroupWiseDeliveriesDistribution;
import com.affaince.subscription.business.distribution.db.DeliveryForecastView;
import com.affaince.subscription.business.distribution.profiles.DefaultShippingProfile;
import com.affaince.subscription.business.distribution.profiles.DistributionZoneGroup;
import com.affaince.subscription.common.type.QuantityUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveriesDistributor {

    public Map<Period, DeliveriesDistributionPortfolio> distributeDeliveriesOfAPeriodAcrossZones(List<DeliveryForecastView> activeDeliveryForecasts, DefaultShippingProfile shippingProfile) {
        List<PeriodWiseAndWeightWiseDeliveriesDistribution> periodWiseAndWeightWiseDeliveriesDistributions = buildPeriodWiseDeliveriesBuckets(activeDeliveryForecasts);
        //distributes deliveries randomly across different regions/zones
        return distributeDeliveries(shippingProfile, periodWiseAndWeightWiseDeliveriesDistributions);
    }

    private List<PeriodWiseAndWeightWiseDeliveriesDistribution> buildPeriodWiseDeliveriesBuckets(List<DeliveryForecastView> activeDeliveryForecasts) {
        List<PeriodWiseAndWeightWiseDeliveriesDistribution> periodWiseAndWeightWiseDeliveriesDistributions = new ArrayList<>();
        //group deliveries as per their start and end period
        Map<Period, List<DeliveryForecastView>> periodWiseDeliveriesMap = activeDeliveryForecasts.stream().collect(Collectors.groupingBy(element -> new Period(element.getDeliveryForecastVersionId().getDeliveryDate(), element.getEndDate())));
        //For each period group deliveries into weight wise buckets

        for (Period dt : periodWiseDeliveriesMap.keySet()) {
            List<DeliveryForecastView> periodWiseDeliveriesList = periodWiseDeliveriesMap.get(dt);
            Map<WeightIndicator, List<DeliveryForecastView>> periodWiseWeightWiseDeliveriesMap = periodWiseDeliveriesList.stream().collect(Collectors.groupingBy(element -> new WeightIndicator(element.getDeliveryForecastVersionId().getWeightRangeMax(), QuantityUnit.GM)));
            PeriodWiseAndWeightWiseDeliveriesDistribution periodWiseAndWeightWiseDeliveriesDistribution = new PeriodWiseAndWeightWiseDeliveriesDistribution(dt, periodWiseWeightWiseDeliveriesMap);
            periodWiseAndWeightWiseDeliveriesDistributions.add(periodWiseAndWeightWiseDeliveriesDistribution);
        }
        return periodWiseAndWeightWiseDeliveriesDistributions;
    }

    private Map<Period, DeliveriesDistributionPortfolio> distributeDeliveries(DefaultShippingProfile shippingProfile, List<PeriodWiseAndWeightWiseDeliveriesDistribution> periodWiseAndWeightWiseDeliveriesDistributions) {
        Map<Period, DeliveriesDistributionPortfolio> deliveriesDistributionProfiles = new HashMap<>();
        for (PeriodWiseAndWeightWiseDeliveriesDistribution bucket : periodWiseAndWeightWiseDeliveriesDistributions) {
            //for the given period distribute the content of the bucket evenly across all distribution zone groups
            DeliveriesDistributionPortfolio deliveriesDistributionPortfolio = new DeliveriesDistributionPortfolio(bucket.getPeriod());
            Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> zoneGroupWiseDeliveriesDistributionForAPeriod = distributeDeliveriesOfAPeriodAcrossZones(shippingProfile, bucket);
            deliveriesDistributionPortfolio.setZoneGroupWiseDeliveriesDistributionsPerPeriod(zoneGroupWiseDeliveriesDistributionForAPeriod);
            deliveriesDistributionProfiles.put(bucket.getPeriod(), deliveriesDistributionPortfolio);
        }
        return deliveriesDistributionProfiles;
    }

    private Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> distributeDeliveriesOfAPeriodAcrossZones(DefaultShippingProfile shippingProfile, PeriodWiseAndWeightWiseDeliveriesDistribution bucket) {
        List<DistributionZoneGroup> distributionZoneGroups = shippingProfile.getDistributionZoneGroups();
        Map<WeightIndicator, List<DeliveryForecastView>> weightWiseDeliveryForecasts = bucket.getPeriodWiseWeightWiseDeliveriesMap();

        Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> zoneGroupWiseDeliveriesDistributionMap = new HashMap<>();
        for (WeightIndicator weight : weightWiseDeliveryForecasts.keySet()) {
            List<DeliveryForecastView> deliveryForecastsForAWeight = weightWiseDeliveryForecasts.get(weight);
            for (DeliveryForecastView delivery : deliveryForecastsForAWeight) {
                long deliveryCount = delivery.getDeliveryCount();
                for (DistributionZoneGroup group : distributionZoneGroups) {
                    if (deliveryCount > 0) {
                        long deliveryCountShare = Math.abs(deliveryCount / distributionZoneGroups.size());
                        ZoneGroupWiseDeliveriesDistribution zoneGroupWiseDeliveriesDistribution = new ZoneGroupWiseDeliveriesDistribution(shippingProfile, group.getDistributionZoneGroupIdentifier());
                        zoneGroupWiseDeliveriesDistribution.addWeightWiseDelivery(weight, deliveryCountShare);
                        zoneGroupWiseDeliveriesDistributionMap.put(group, zoneGroupWiseDeliveriesDistribution);
                        deliveryCount -= deliveryCountShare;
                    }
                }
            }
        }
        return zoneGroupWiseDeliveriesDistributionMap;
    }
}
