package com.affaince.subscription.pricing.processor;


import com.affaince.subscription.pricing.vo.PriceDeterminationCriteria;

public class DefaultPriceDeterminator implements PriceDeterminator {


    public DefaultPriceDeterminator() {

    }

    @Override
    public double calculateOfferedPrice(PriceDeterminationCriteria priceDeterminationCriteria) {
        //temporirily commenting until Product is resolved into different repositories;
/*
        final double purchasePrice = product.getLatestPurchasePrice();
        final double operatingExpensesPerProductPerUnit = product.getFixedOperatingExpensePerUnit()+ product.getVariableOperatingExpensePerUnit();
        final double MRP = product.getLatestMRP();
        final double expectedMerchantProfit = product.getLatestMerchantProfitExpectation();
        final double breakevenPrice = purchasePrice + operatingExpensesPerProductPerUnit;
        final double netProfit = MRP - breakevenPrice;
        final double priceAfterMerchantProfit = MRP - (netProfit - expectedMerchantProfit * breakevenPrice);
        //Offered price without basket discount= Price after merchant profit +(1-(percetnage margin to be given*product demand density))
        final double latestDemandDensityActuals = product.getDemandDensity();
        final double profitSharingPercentageAtZeroDemand = product.findProfitSharingRuleByDemandDensity(0.0).getSharedProfitPercentage();
        final double profitSharingPercentageForADemand = product.findProfitSharingRuleByDemandDensity(latestDemandDensityActuals)
                .getSharedProfitPercentage();
        final double slopeOfProfitShareForADemand = (profitSharingPercentageAtZeroDemand - latestDemandDensityActuals)
                / profitSharingPercentageForADemand;
        final double defaultOfferedPrice = priceAfterMerchantProfit + (1 - (profitSharingPercentageAtZeroDemand - latestDemandDensityActuals)
                / slopeOfProfitShareForADemand) * netProfit;
        return defaultOfferedPrice;
*/
        return 0.0;
    }


   /* public static void main (String [] args) {
        final double purchasePrice = 18;
        final double operatingExpensesPerProductPerUnit = 3;
        final double MRP = 27;
        final double expectedMerchantProfit = 0.1;
        final double breakevenPrice = purchasePrice + operatingExpensesPerProductPerUnit;
        final double netProfit = MRP - breakevenPrice;
        final double priceAfterMerchantProfit = MRP - (netProfit - expectedMerchantProfit * breakevenPrice);
        //Offered price without basket discount= Price after merchant profit +(1-(percetnage margin to be given*product demand density))
        final double latestDemandDensityActuals = 0.518161018;
        final double profitSharingPercentageAtZeroDemand= 0.8;
        final double profitSharingPercentageForADemand = 0.55;
        final double slopeOfProfitShareForADemand= (profitSharingPercentageAtZeroDemand- latestDemandDensityActuals)
                /profitSharingPercentageForADemand;
        final double defaultOfferedPrice=priceAfterMerchantProfit + (1-(profitSharingPercentageAtZeroDemand - latestDemandDensityActuals)
                /slopeOfProfitShareForADemand)*netProfit;
        System.out.println(defaultOfferedPrice);
        //product.setLatestOfferedPriceActuals (defaultOfferedPrice);
    }*/
}
