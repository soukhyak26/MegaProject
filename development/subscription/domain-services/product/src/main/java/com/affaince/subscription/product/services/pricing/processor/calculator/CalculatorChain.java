package com.affaince.subscription.product.services.pricing.processor.calculator;

import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.services.pricing.processor.calculator.classic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mandark on 27-03-2016.
 */
@Component
public class CalculatorChain {
    private AbstractPriceCalculator initialCalculator;

    @Value("${pricing.calculator.chain.list}")
    private String pricingCalculatorChainElements;
    @Autowired
    private OpeningPriceCalculator openingPriceCalculator;
    @Autowired
    private SingleHistoryPriceCalculator singleHistoryPriceCalculator;
    @Autowired
    private ProfitGrowthBasedOnDemandGrowthPriceCalculator profitGrowthBasedOnDemandGrowthPriceCalculator;
    @Autowired
    private ProfitGrowthDueToPriceGrowthBasedPriceCalculator profitGrowthDueToPriceGrowthBasedPriceCalculator;
    @Autowired
    private ProfitReductionAfterDemandGrowthPriceCalculator profitReductionAfterDemandGrowthPriceCalculator;
    @Autowired
    private ProfitReductionDueToDemandPriceCalculator profitReductionDueToDemandPriceCalculator;

    @PostConstruct
    public void init() {
        List<String> calculatorPrefixes = Arrays.asList(pricingCalculatorChainElements.split(","));
        for (String prefix : calculatorPrefixes) {
            if (prefix.equals("Opening")) {
                this.addCalculator(openingPriceCalculator);
            } else if (prefix.equals("SingleHistory")) {
                this.addCalculator(singleHistoryPriceCalculator);
            } else if (prefix.equals("ProfitGrowthBasedOnDemandGrowth")) {
                this.addCalculator(profitGrowthBasedOnDemandGrowthPriceCalculator);
            } else if (prefix.equals("ProfitGrowthDueToPriceGrowth")) {
                this.addCalculator(profitReductionDueToDemandPriceCalculator);
            } else if (prefix.equals("ProfitReductionAfterDemandGrowth")) {
                this.addCalculator(profitReductionAfterDemandGrowthPriceCalculator);
            } else if (prefix.equals("ProfitReductionDueToDemand")) {
                this.addCalculator(profitReductionDueToDemandPriceCalculator);
            }
        }
    }

    public void addCalculator(AbstractPriceCalculator calculator) {
        if (initialCalculator != null) {
            initialCalculator.setNextCalculator(calculator);
        }else {
            initialCalculator = calculator;
        }
    }

    public PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductActualMetricsView productActualMetricsView, ProductForecastMetricsView productForecastMetricsView) {
        return initialCalculator.calculatePrice(activePriceBuckets, productActualMetricsView,productForecastMetricsView);
    }
}
