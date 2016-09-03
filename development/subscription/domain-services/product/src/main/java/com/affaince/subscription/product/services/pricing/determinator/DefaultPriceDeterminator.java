package com.affaince.subscription.product.services.pricing.determinator;


import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.pricing.calculator.CalculatorChain;
import com.affaince.subscription.product.vo.PriceCalculationParameters;
import com.affaince.subscription.product.vo.PricingStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.Collection;


public class DefaultPriceDeterminator implements PriceDeterminator {
    @Autowired
    private PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;
    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;
    @Autowired
    private CalculatorChain calculatorChain;

    public DefaultPriceDeterminator() {

    }

    @Override
    public PriceBucket calculateOfferedPrice(final Product product, ProductDemandTrend productDemandTrend) {

        //List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(product.getProductId());
        Collection<PriceBucket> activePriceBuckets = product.getProductAccount().getActivePriceBuckets().values();
        ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(product.getProductId());
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductActualsView productActualsView = productActualsViewRepository.findByProductVersionId_ProductId(product.getProductId(), sort).get(0);
        ProductForecastView productForecastView = productForecastViewRepository.findByProductVersionId_ProductId(product.getProductId(), sort).get(0);
        PricingStrategyType pricingStrategyType = productConfigurationView.getPricingStrategyType();
        PriceCalculationParameters priceCalculationParameters = new PriceCalculationParameters(product, productActualsView, productDemandTrend, productConfigurationView.getChangeThresholdForPriceChange(), pricingStrategyType);
        PriceBucket latestPriceBucket = calculatorChain.calculatePrice(priceCalculationParameters);
        // priceBucketViewRepository.save(latestPriceBucket);
        return latestPriceBucket;

    }
}
