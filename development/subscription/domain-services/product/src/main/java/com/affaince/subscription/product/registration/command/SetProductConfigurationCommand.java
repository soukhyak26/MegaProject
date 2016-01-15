package com.affaince.subscription.product.registration.command;

import com.affaince.subscription.common.type.Period;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class SetProductConfigurationCommand {
    @TargetAggregateIdentifier
    private String productId;
    private Period demandCurvePeriod;
    private short revenueChangeThresholdForPriceChange;
    private double merchantExpectedProfitPercent;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;

    public SetProductConfigurationCommand(String productId, Period demandCurvePeriod, short revenueChangeThresholdForPriceChange, double merchantExpectedProfitPercent, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered) {
        this.productId = productId;
        this.demandCurvePeriod = demandCurvePeriod;
        this.revenueChangeThresholdForPriceChange = revenueChangeThresholdForPriceChange;
        this.merchantExpectedProfitPercent = merchantExpectedProfitPercent;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
    }

    public SetProductConfigurationCommand() {
    }

    public String getProductId() {
        return productId;
    }

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }

    public short getRevenueChangeThresholdForPriceChange() {
        return revenueChangeThresholdForPriceChange;
    }

    public double getMerchantExpectedProfitPercent() {
        return merchantExpectedProfitPercent;
    }

    public boolean isCrossPriceElasticityConsidered() {
        return isCrossPriceElasticityConsidered;
    }

    public boolean isAdvertisingExpensesConsidered() {
        return isAdvertisingExpensesConsidered;
    }
}
