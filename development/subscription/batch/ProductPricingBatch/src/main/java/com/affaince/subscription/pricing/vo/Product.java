package com.affaince.subscription.pricing.vo;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mandark on 20-02-2016.
 */
public class Product {
    private double latestPurchasePrice;
    private double latestMerchantProfitExpectation;
    private double latestMRP;
    private Map<LocalDate, PriceBucket> activePriceBuckets;
    private double fixedOperatingExpensePerUnit;
    private double variableOperatingExpensePerUnit;
    private double demandDensity;
    private List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules;

    public double getLatestPurchasePrice() {
        return this.latestPurchasePrice;
    }

    public void setLatestPurchasePrice(double latestPurchasePrice) {
        this.latestPurchasePrice = latestPurchasePrice;
    }

    public double getLatestMerchantProfitExpectation() {
        return this.latestMerchantProfitExpectation;
    }

    public void setLatestMerchantProfitExpectation(double latestMerchantProfitExpectation) {
        this.latestMerchantProfitExpectation = latestMerchantProfitExpectation;
    }

    public double getLatestMRP() {
        return this.latestMRP;
    }

    public void setLatestMRP(double latestMRP) {
        this.latestMRP = latestMRP;
    }

    public double getFixedOperatingExpensePerUnit() {
        return this.fixedOperatingExpensePerUnit;
    }

    public void setFixedOperatingExpensePerUnit(double fixedOperatingExpensePerUnit) {
        this.fixedOperatingExpensePerUnit = fixedOperatingExpensePerUnit;
    }

    public double getVariableOperatingExpensePerUnit() {
        return this.variableOperatingExpensePerUnit;
    }

    public void setVariableOperatingExpensePerUnit(double variableOperatingExpensePerUnit) {
        this.variableOperatingExpensePerUnit = variableOperatingExpensePerUnit;
    }

    public double getDemandDensity() {
        return this.demandDensity;
    }

    public void setDemandDensity(double demandDensity) {
        this.demandDensity = demandDensity;
    }

    public List<DemandWiseProfitSharingRule> getDemandWiseProfitSharingRules() {
        return this.demandWiseProfitSharingRules;
    }

    public void setDemandWiseProfitSharingRules(List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules) {
        this.demandWiseProfitSharingRules = demandWiseProfitSharingRules;
    }


    public Map<LocalDate, PriceBucket> getActivePriceBuckets() {
        return this.activePriceBuckets;
    }

    public void setActivePriceBuckets(Map<LocalDate, PriceBucket> activePriceBuckets) {
        this.activePriceBuckets = activePriceBuckets;
    }

    public DemandWiseProfitSharingRule findProfitSharingRuleByDemandDensity(double demandDensity) {
        DemandWiseProfitSharingRule rule = new DemandWiseProfitSharingRule();
        rule.setDemandDensityPercentage(demandDensity);
        if (demandWiseProfitSharingRules.contains(rule)) {
            return demandWiseProfitSharingRules.get(demandWiseProfitSharingRules.indexOf(rule));
        }
        return null;
    }

    public PriceBucket findActivePriceBucketByDate(LocalDate dateIdentifier) {
        return this.activePriceBuckets.get(dateIdentifier);
    }

    public PriceBucket getLatestPriceBucket() {
        Set<LocalDate> timeBasedKeys = activePriceBuckets.keySet();
        LocalDate max = null;
        for (LocalDate time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return activePriceBuckets.get(max);
    }

}
