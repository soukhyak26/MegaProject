package com.affaince.subscription.product.registration.process.price;

import com.affaince.subscription.product.registration.command.domain.Product;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class DefaultPriceDeterminator implements PriceDeterminator {

    private final PriceDeterminator priceDeterminator;

    public DefaultPriceDeterminator(PriceDeterminator priceDeterminator) {
        this.priceDeterminator = priceDeterminator;
    }

    @Override
    public void calculateOfferedPrice(Product product) {
        final double purchasePrice = product.getLatestPurchasePriceActuals();
        final double operatingExpensesPerProductPerUnit = product.getLatestOperatingExpensesPerUnitActuals();
        final double MRP = product.getLatestMRPActuals();
        final double expectedMerchantProfit = product.getLatestMerchantProfitActuals();
        final double breakevenPrice = purchasePrice + operatingExpensesPerProductPerUnit;
        final double netProfit = MRP - breakevenPrice;
        final double priceAfterMerchantProfit = MRP - (netProfit - expectedMerchantProfit * breakevenPrice);
        //Offered price without basket discount= Price after merchant profit +(1-(percetnage margin to be given*product demand density))
        final double latestDemandDensityActuals = product.getLatestDemandDensityActuals();
        final double profitSharingPercentageAtZeroDemand = product.findProfitSharingRuleByDemandDensity(0.0).getSharedProfitPercentage();
        final double profitSharingPercentageForADemand = product.findProfitSharingRuleByDemandDensity(latestDemandDensityActuals)
                .getSharedProfitPercentage();
        final double slopeOfProfitShareForADemand = (profitSharingPercentageAtZeroDemand - latestDemandDensityActuals)
                / profitSharingPercentageForADemand;
        final double defaultOfferedPrice = priceAfterMerchantProfit + (1 - (profitSharingPercentageAtZeroDemand - latestDemandDensityActuals)
                / slopeOfProfitShareForADemand) * netProfit;
        product.setLatestOfferedPriceActuals(defaultOfferedPrice);
        if (null != priceDeterminator) {
            priceDeterminator.calculateOfferedPrice(product);
        }
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
