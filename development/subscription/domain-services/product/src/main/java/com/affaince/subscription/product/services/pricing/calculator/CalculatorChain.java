package com.affaince.subscription.product.services.pricing.calculator;

import com.affaince.subscription.common.type.PricingStrategyType;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.configuration.CalculatorConfiguration;
import com.affaince.subscription.product.services.pricing.calculator.historybased.RegressionBasedPriceCalculator;
import com.affaince.subscription.product.services.pricing.calculator.instant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by mandark on 27-03-2016.
 */
@Component
public class CalculatorChain {

    @Autowired
    private CalculatorConfiguration calculatorConfiguration;
    private List<AbstractPriceCalculator> initialCalculatorsForDifferentStrategies;
/*
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

    @Autowired
    private RegressionBasedPriceCalculator regressionBasedPriceCalculator;
*/

    @PostConstruct
    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        List<CalculatorConfiguration.CalculatorChainConfig> calculatorChainConfigs = calculatorConfiguration.getCalculatorchain();
        Map<String, AbstractPriceCalculator> calculatorsMapAgainstNextCalculatorName = new HashMap<>();
        Map<String, AbstractPriceCalculator> initialCalculatorsMap = new HashMap<>();
        for (int i = 0; i < calculatorChainConfigs.size(); i++) {
            CalculatorConfiguration.CalculatorChainConfig config = calculatorChainConfigs.get(i);
            String name = config.getName();
            AbstractPriceCalculator calculator = (AbstractPriceCalculator) Class.forName(config.getCls()).newInstance();
            String next = config.getNext();

            if (next.equals("NULL")) {
                calculator.setNextCalculator(null);
            } else {
                calculatorsMapAgainstNextCalculatorName.put(next, calculator);
            }
            AbstractPriceCalculator earlierCalculator = calculatorsMapAgainstNextCalculatorName.get(name);
            if (null != earlierCalculator) {
                earlierCalculator.setNextCalculator(calculator);
                calculatorsMapAgainstNextCalculatorName.remove(name);
                initialCalculatorsMap.remove(name);
            } else {
                initialCalculatorsMap.put(name, calculator);
            }
        }
        this.initialCalculatorsForDifferentStrategies=new ArrayList<>();
        this.initialCalculatorsForDifferentStrategies.addAll(initialCalculatorsMap.values());
    }

/*
    public void addCalculator(AbstractPriceCalculator calculator) {
        if (initialCalculator == null) {
            initialCalculator = calculator;
        }else {
            initialCalculator.setNextCalculator(calculator);

        }
    }
*/

    public PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend) {
        PriceBucket priceBucket=null;
        for (AbstractPriceCalculator calculator : this.initialCalculatorsForDifferentStrategies) {
            if (product.getProductConfiguration().getPricingStrategyType() == PricingStrategyType.DEFAULT_PRICING_STRATEGY) {
                if (calculator instanceof OpeningPriceCalculator) {
                    priceBucket= calculator.calculatePrice(product, productDemandTrend);
                    break;
                }
            } else if (product.getProductConfiguration().getPricingStrategyType() == PricingStrategyType.DEMAND_BASED_PRICING_STRATEGY) {
                if (calculator instanceof RegressionBasedPriceCalculator) {
                    priceBucket= calculator.calculatePrice(product, productDemandTrend);
                    break;
                }
            }
        }
        return priceBucket;
    }
}
