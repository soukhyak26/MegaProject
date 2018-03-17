package com.verifier.controller;

import com.verifier.domains.product.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "product")
public class ProductController {
    private final BusinessAccountViewRepository businessAccountViewRepository;
    private final CategoryDetailsViewRepository categoryDetailsViewRepository;
    private final FixedExpensePerProductViewRepository fixedExpensePerProductViewRepository;
    private final PriceBucketTransactionViewRepository priceBucketTransactionViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;
    private final ProductActivationStatusViewPagingRepository productActivationStatusViewPagingRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;
    private final ProductActualsViewRepository productActualsViewRepository;
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final ProductForecastTrendViewRepository productForecastTrendViewRepository;
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    private final ProductViewPagingRepository productViewPagingRepository;
    private final ProductViewRepository productViewRepository;
    private final RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository;
    private final TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;
    private final TargetSettingViewRepository targetSettingViewRepository;
    private final VariableExpensePerProductViewRepository variableExpensePerProductViewRepository;

    @Autowired
    public ProductController(BusinessAccountViewRepository businessAccountViewRepository, CategoryDetailsViewRepository categoryDetailsViewRepository, FixedExpensePerProductViewRepository fixedExpensePerProductViewRepository, PriceBucketTransactionViewRepository priceBucketTransactionViewRepository, PriceBucketViewRepository priceBucketViewRepository, ProductActivationStatusViewPagingRepository productActivationStatusViewPagingRepository, ProductActivationStatusViewRepository productActivationStatusViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository, ProductActualsViewRepository productActualsViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository, ProductForecastTrendViewRepository productForecastTrendViewRepository, ProductForecastViewRepository productForecastViewRepository, ProductPseudoActualsViewRepository productPseudoActualsViewRepository, ProductViewPagingRepository productViewPagingRepository, ProductViewRepository productViewRepository, RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository, TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository, TargetSettingViewRepository targetSettingViewRepository, VariableExpensePerProductViewRepository variableExpensePerProductViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
        this.categoryDetailsViewRepository = categoryDetailsViewRepository;
        this.fixedExpensePerProductViewRepository = fixedExpensePerProductViewRepository;
        this.priceBucketTransactionViewRepository = priceBucketTransactionViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
        this.productActivationStatusViewPagingRepository = productActivationStatusViewPagingRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
        this.productActualsViewRepository = productActualsViewRepository;
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.productForecastTrendViewRepository = productForecastTrendViewRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.productViewPagingRepository = productViewPagingRepository;
        this.productViewRepository = productViewRepository;
        this.recommendedPriceBucketViewRepository = recommendedPriceBucketViewRepository;
        this.taggedPriceVersionsViewRepository = taggedPriceVersionsViewRepository;
        this.targetSettingViewRepository = targetSettingViewRepository;
        this.variableExpensePerProductViewRepository = variableExpensePerProductViewRepository;
    }



}
