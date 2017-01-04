package com.affaince.subscription.product.services.pricing.calculator;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.historybased.RegressionBasedPriceCalculator;
import com.affaince.subscription.product.services.pricing.calculator.instant.*;
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
    @Autowired
    private RegressionBasedPriceCalculator regressionBasedPriceCalculator;
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
                this.addCalculator(profitGrowthDueToPriceGrowthBasedPriceCalculator);
            } else if (prefix.equals("ProfitReductionAfterDemandGrowth")) {
                this.addCalculator(profitReductionAfterDemandGrowthPriceCalculator);
            } else if (prefix.equals("ProfitReductionDueToDemand")) {
                this.addCalculator(profitReductionDueToDemandPriceCalculator);
            } else if (prefix.equals("RegressionBased")) {
                this.addCalculator(regressionBasedPriceCalculator);
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

    public PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend) {
        return initialCalculator.calculatePrice(product, productDemandTrend);
    }
}
