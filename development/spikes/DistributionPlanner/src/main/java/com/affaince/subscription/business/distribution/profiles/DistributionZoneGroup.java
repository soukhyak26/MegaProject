package com.affaince.subscription.business.distribution.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributionZoneGroup that = (DistributionZoneGroup) o;
        return merchantId.equals(that.merchantId) &&
                distributionZoneGroupIdentifier.equals(that.distributionZoneGroupIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantId, distributionZoneGroupIdentifier);
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
