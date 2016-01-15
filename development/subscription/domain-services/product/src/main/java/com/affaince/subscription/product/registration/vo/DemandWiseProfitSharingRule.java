package com.affaince.subscription.product.registration.vo;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class DemandWiseProfitSharingRule {

    private double demandDensityPercentage;
    private double sharedProfitPercentage;

    public double getDemandDensityPercentage() {
        return demandDensityPercentage;
    }

    public void setDemandDensityPercentage(double demandDensityPercentage) {
        this.demandDensityPercentage = demandDensityPercentage;
    }

    public double getSharedProfitPercentage() {
        return sharedProfitPercentage;
    }

    public void setSharedProfitPercentage(double sharedProfitPercentage) {
        this.sharedProfitPercentage = sharedProfitPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemandWiseProfitSharingRule)) return false;

        DemandWiseProfitSharingRule that = (DemandWiseProfitSharingRule) o;

        return Double.compare(that.demandDensityPercentage, demandDensityPercentage) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(demandDensityPercentage);
        return (int) (temp ^ (temp >>> 32));
    }
}
