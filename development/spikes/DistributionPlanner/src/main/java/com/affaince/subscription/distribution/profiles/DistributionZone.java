package com.affaince.subscription.distribution.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DistributionZone {
    private final String merchantId;
    private final String distributionZoneIdentifier;
    List<RatePerUnitWeight> ratesInTheZone;

    public DistributionZone(String merchantId, String distributionZoneIdentifier) {
        this.merchantId = merchantId;
        this.distributionZoneIdentifier = distributionZoneIdentifier;
        this.ratesInTheZone = new ArrayList<>();
    }

    public String getDistributionZoneIdentifier() {
        return distributionZoneIdentifier;
    }

    public List<RatePerUnitWeight> getRatesInTheZone() {
        return ratesInTheZone;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void addToRates(RatePerUnitWeight ratePerUnitWeight) {
        this.ratesInTheZone.add(ratePerUnitWeight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributionZone that = (DistributionZone) o;
        return merchantId.equals(that.merchantId) &&
                distributionZoneIdentifier.equals(that.distributionZoneIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantId, distributionZoneIdentifier);
    }

    @Override
    public String toString() {
        return "DistributionZone{" +
                "merchantId='" + merchantId + '\'' +
                ", distributionZoneIdentifier='" + distributionZoneIdentifier + '\'' +
                //", ratesInTheZone=" + ratesInTheZone +
                '}';
    }
}
