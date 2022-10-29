package com.affaince.subscription.business.distribution.profiles;

import java.util.ArrayList;
import java.util.List;

public class DefaultShippingProfile {
    private final String merchantId;
    private final String distributionProfileId;
    List<DistributionZoneGroup> distributionZoneGroups;

    public DefaultShippingProfile(String merchantId, String distributionProfileId) {
        this.merchantId = merchantId;
        this.distributionProfileId = distributionProfileId;
        this.distributionZoneGroups = new ArrayList<>();
    }

    public String getDistributionProfileId() {
        return distributionProfileId;
    }

    public List<DistributionZoneGroup> getDistributionZoneGroups() {
        return distributionZoneGroups;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void addToDistributionZoneGroup(DistributionZoneGroup distributionZoneGroup) {
        this.distributionZoneGroups.add(distributionZoneGroup);
    }

    public DistributionZoneGroup searchForDistributionZoneGroup(String zoneGroupId) {
        return this.distributionZoneGroups.stream().filter(dzg -> dzg.getDistributionZoneGroupIdentifier().equals(zoneGroupId)).findFirst().get();
    }

    public DistributionZone searchForDistributionZone(String distributionZoneId) {
        DistributionZoneGroup zoneGroup = this.distributionZoneGroups.
                stream().
                filter(dzg -> dzg.
                        getDistributionZonesUnderTheGroup().
                        stream().
                        anyMatch(dz -> dz.getDistributionZoneIdentifier().equals(distributionZoneId))).
                findFirst().get();
        return zoneGroup.getDistributionZonesUnderTheGroup().stream().filter(dz -> dz.getDistributionZoneIdentifier().equals(distributionZoneId)).findFirst().get();

    }
}
