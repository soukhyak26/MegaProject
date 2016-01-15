package com.affaince.subscription.product.registration.process.price;

import com.affaince.subscription.product.registration.command.domain.Product;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class DefaultPriceDeterminator implements PriceDeterminator {

    @Override
    public void calculateOfferedPrice(Product product, PriceDeterminator priceDeterminator) {
        final double purchasePrice = product.getLatestPurchasePriceActuals();
        final double operatingExpensesPerProductPerUnit = product.getLatestOperatingExpensesPerUnitActuals();
        final double MRP = product.getLatestMRPActuals();
        final double expectedMerchantProfit = product.getLatestMerchantProfitActuals();
        final double breakevenPrice = purchasePrice + operatingExpensesPerProductPerUnit;
        final double netProfit = MRP - breakevenPrice;
        final double priceAfterMerchantProfit = MRP - (netProfit - expectedMerchantProfit * breakevenPrice);
    }
}
