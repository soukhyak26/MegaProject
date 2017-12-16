package com.affaince.subscription.product.services.pricing.determinator;


import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.domain.PriceBucket;
import com.affaince.subscription.product.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.CalculatorChain;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultPriceDeterminator implements PriceDeterminator {
    @Autowired
    private CalculatorChain calculatorChain;

    public DefaultPriceDeterminator() {

    }

    @Override
    public PriceBucket calculateOfferedPrice(final Product product, ProductDemandTrend productDemandTrend) {
        PriceBucket latestPriceBucket = calculatorChain.calculatePrice(product, productDemandTrend);
        return latestPriceBucket;

    }
}
