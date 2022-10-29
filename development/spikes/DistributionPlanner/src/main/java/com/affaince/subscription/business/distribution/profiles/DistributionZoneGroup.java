package com.affaince.subscription.business.distribution.profiles;

import java.util.ArrayList;
import java.util.List;

public class DistributionZoneGroup {
    private final String merchantId;
    private final String distributionZoneGroupIdentifier;
    private final List<DistributionZone> distributionZonesUnderTheGroup;

    public DistributionZoneGroup(String merchantId, String distributionZoneGroupIdentifier) {
        this.merchantId = merchantId;
        this.distributionZoneGroupIdentifier = distributionZoneGroupIdentifier;
        this.distributionZonesUnderTheGroup = new ArrayList<>();
    }

    public String getDistributionZoneGroupIdentifier() {
        return distributionZoneGroupIdentifier;
    }

    public List<DistributionZone> getDistributionZonesUnderTheGroup() {
        return distributionZonesUnderTheGroup;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void addToDistributionZone(DistributionZone distributionZone) {
        this.distributionZonesUnderTheGroup.add(distributionZone);
    }

    @Override
    public String toString() {
        return "DistributionZoneGroup{" +
                "merchantId='" + merchantId + '\'' +
                ", distributionZoneGroupIdentifier='" + distributionZoneGroupIdentifier + '\'' +
                ", distributionZonesUnderTheGroup=" + distributionZonesUnderTheGroup +
                '}';
    }
}
