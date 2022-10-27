package com.affaince.distribution.sampler;

import com.affaince.distribution.db.DeliveryForecastView;
import com.affaince.distribution.profiles.DefaultShippingProfile;
import com.affaince.distribution.profiles.DistributionZone;
import com.affaince.distribution.profiles.DistributionZoneGroup;
import com.affaince.subscription.common.type.QuantityUnit;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveriesDistributionSampler {

    public Map<Period, DeliveriesDistributionProfile> distributeDeliveriesOfAPeriod(List<DeliveryForecastView> activeDeliveryForecasts, DefaultShippingProfile shippingProfile) {
        List<PeriodWiseAndWeightWiseDeliveriesDistribution> periodWiseAndWeightWiseDeliveriesDistributions = buildPeriodWiseDeliveriesBuckets(activeDeliveryForecasts);
        //distributes deliveries randomly across different regions/zones
        return distributeDeliveriesAcrossZones(shippingProfile, periodWiseAndWeightWiseDeliveriesDistributions);
    }

    private List<PeriodWiseAndWeightWiseDeliveriesDistribution> buildPeriodWiseDeliveriesBuckets(List<DeliveryForecastView> activeDeliveryForecasts) {
        List<PeriodWiseAndWeightWiseDeliveriesDistribution> periodWiseAndWeightWiseDeliveriesDistributions = new ArrayList<>();
        //group deliveries as per their start and end period
        Map<Period, List<DeliveryForecastView>> periodWiseDeliveriesMap;
        periodWiseDeliveriesMap = activeDeliveryForecasts.stream().collect(Collectors.groupingBy(element -> new Period(element.getDeliveryForecastVersionId().getDeliveryDate(), element.getEndDate())));
        //For each period group deliveries into weight wise buckets

        for (Period dt : periodWiseDeliveriesMap.keySet()) {
            List<DeliveryForecastView> periodWiseDeliveriesList = periodWiseDeliveriesMap.get(dt);
            Map<WeightIndicator, List<DeliveryForecastView>> periodWiseWeightWiseDeliveriesMap = periodWiseDeliveriesList.stream().collect(Collectors.groupingBy(element -> new WeightIndicator(element.getDeliveryForecastVersionId().getWeightRangeMax(), QuantityUnit.GM)));
            PeriodWiseAndWeightWiseDeliveriesDistribution periodWiseAndWeightWiseDeliveriesDistribution = new PeriodWiseAndWeightWiseDeliveriesDistribution(dt, periodWiseWeightWiseDeliveriesMap);
            periodWiseAndWeightWiseDeliveriesDistributions.add(periodWiseAndWeightWiseDeliveriesDistribution);
        }
        return periodWiseAndWeightWiseDeliveriesDistributions;
    }

    private Map<Period, DeliveriesDistributionProfile> distributeDeliveriesAcrossZones(DefaultShippingProfile shippingProfile, List<PeriodWiseAndWeightWiseDeliveriesDistribution> periodWiseAndWeightWiseDeliveriesDistributions) {
        Map<Period, DeliveriesDistributionProfile> deliveriesDistributionProfiles = new HashMap<>();
        for (PeriodWiseAndWeightWiseDeliveriesDistribution bucket : periodWiseAndWeightWiseDeliveriesDistributions) {
            //for the given period distribute the content of the bucket evenly across all distribution zone groups
            DeliveriesDistributionProfile deliveriesDistributionProfile = new DeliveriesDistributionProfile(bucket.getPeriod());
            Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> zoneGroupWiseDeliveriesDistributionForAPeriod = distributeDeliveriesOfAPeriodAcrossZones(shippingProfile, bucket);
            deliveriesDistributionProfile.setZoneGroupWiseDeliveriesDistributionsPerPeriod(zoneGroupWiseDeliveriesDistributionForAPeriod);
            deliveriesDistributionProfiles.put(bucket.getPeriod(), deliveriesDistributionProfile);
        }
        return deliveriesDistributionProfiles;
    }

    private Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> distributeDeliveriesOfAPeriodAcrossZones(DefaultShippingProfile shippingProfile, PeriodWiseAndWeightWiseDeliveriesDistribution bucket) {
        List<DistributionZoneGroup> distributionZoneGroups = shippingProfile.getDistributionZoneGroups();
        Map<WeightIndicator, List<DeliveryForecastView>> weightWiseDeliveryForecasts = bucket.getPeriodWiseWeightWiseDeliveriesMap();
        Map<DistributionZoneGroup, Stack<DeliveryForecastView>> zoneGroupWiseDeliveriesGroupings = new HashMap<>();
        Map<DistributionZoneGroup, ZoneGroupWiseDeliveriesDistribution> zoneGroupWiseDeliveriesDistributionMap = new HashMap<>();
        for (WeightIndicator weight : weightWiseDeliveryForecasts.keySet()) {
            Stack<DeliveryForecastView> stack = new Stack<>();
            stack.addAll(weightWiseDeliveryForecasts.get(weight));
            while (!stack.isEmpty()) {
                for (DistributionZoneGroup zoneGroup : distributionZoneGroups) {
                    if (null == zoneGroupWiseDeliveriesGroupings.get(zoneGroup)) {
                        zoneGroupWiseDeliveriesGroupings.put(zoneGroup, new Stack<>());
                    }
                    zoneGroupWiseDeliveriesGroupings.get(zoneGroup).push(stack.pop());
                }
                for (DistributionZoneGroup zoneGroup : distributionZoneGroups) {
                    Stack<DeliveryForecastView> zoneGroupWiseStack = zoneGroupWiseDeliveriesGroupings.get(zoneGroup);
                    ZoneGroupWiseDeliveriesDistribution zoneGroupWiseDeliveriesDistribution = new ZoneGroupWiseDeliveriesDistribution(zoneGroup.getMerchantId(), zoneGroup.getDistributionZoneGroupIdentifier());
                    while (!zoneGroupWiseStack.isEmpty()) {
                        for (DistributionZone zone : zoneGroup.getDistributionZonesUnderTheGroup()) {
                            if (!zoneGroupWiseDeliveriesDistribution.getZoneWiseDeliveriesDistributionMap().containsKey(zone)) {
                                zoneGroupWiseDeliveriesDistribution.addZoneWiseDelivery(zone, new ZoneWiseDeliveriesDistribution(shippingProfile.getMerchantId(), zone.getDistributionZoneIdentifier()));
                            }
                            ZoneWiseDeliveriesDistribution zoneWiseDeliveriesDistribution = zoneGroupWiseDeliveriesDistribution.getZoneWiseDeliveriesDistributionMap().get(zone);
                            zoneWiseDeliveriesDistribution.addWeightWiseDelivery(weight, zoneGroupWiseStack.pop());
                        }
                    }
                    zoneGroupWiseDeliveriesDistributionMap.put(zoneGroup, zoneGroupWiseDeliveriesDistribution);
                }
            }
        }
        return zoneGroupWiseDeliveriesDistributionMap;
    }


}
