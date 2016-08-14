package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.product.vo.DemandWiseProfitSharingRule;

import java.util.List;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductConfigurationSetEvent {
    private String productId;
    private short revenueChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules;

    public ProductConfigurationSetEvent(String productId, Period demandCurvePeriod, short revenueChangeThresholdForPriceChange, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered, List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules) {
        this.productId = productId;
        this.revenueChangeThresholdForPriceChange = revenueChangeThresholdForPriceChange;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
        this.demandWiseProfitSharingRules = demandWiseProfitSharingRules;
    }

    public ProductConfigurationSetEvent() {
    }

    public String getProductId() {
        return productId;
    }


    public short getRevenueChangeThresholdForPriceChange() {
        return revenueChangeThresholdForPriceChange;
    }


    public boolean isCrossPriceElasticityConsidered() {
        return isCrossPriceElasticityConsidered;
    }

    public boolean isAdvertisingExpensesConsidered() {
        return isAdvertisingExpensesConsidered;
    }

    public List<DemandWiseProfitSharingRule> getDemandWiseProfitSharingRules() {
        return demandWiseProfitSharingRules;
    }
}
