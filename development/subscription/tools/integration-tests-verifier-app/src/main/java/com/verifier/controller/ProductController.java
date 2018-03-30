package com.verifier.controller;

import com.affaince.subscription.date.SysDate;
import com.verifier.domains.product.repository.*;
import com.verifier.domains.product.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {
    private final ProductBusinessAccountViewRepository productBusinessAccountViewRepository;
    private final CategoryDetailsViewRepository categoryDetailsViewRepository;
    private final FixedExpensePerProductViewRepository fixedExpensePerProductViewRepository;
    private final PriceBucketTransactionViewRepository priceBucketTransactionViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;
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
    public ProductController(ProductBusinessAccountViewRepository productBusinessAccountViewRepository, CategoryDetailsViewRepository categoryDetailsViewRepository, FixedExpensePerProductViewRepository fixedExpensePerProductViewRepository, PriceBucketTransactionViewRepository priceBucketTransactionViewRepository, PriceBucketViewRepository priceBucketViewRepository, ProductActivationStatusViewRepository productActivationStatusViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository, ProductActualsViewRepository productActualsViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository, ProductForecastTrendViewRepository productForecastTrendViewRepository, ProductForecastViewRepository productForecastViewRepository, ProductPseudoActualsViewRepository productPseudoActualsViewRepository, ProductViewPagingRepository productViewPagingRepository, ProductViewRepository productViewRepository, RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository, TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository, TargetSettingViewRepository targetSettingViewRepository, VariableExpensePerProductViewRepository variableExpensePerProductViewRepository) {
        this.productBusinessAccountViewRepository = productBusinessAccountViewRepository;
        this.categoryDetailsViewRepository = categoryDetailsViewRepository;
        this.fixedExpensePerProductViewRepository = fixedExpensePerProductViewRepository;
        this.priceBucketTransactionViewRepository = priceBucketTransactionViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
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

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount")
    public ResponseEntity<BusinessAccountView> getBusinessAccount() {
        List<BusinessAccountView> views = productBusinessAccountViewRepository.findByEndDateAfter(SysDate.now());
        return new ResponseEntity<>(views.get(0), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "categories")
    public ResponseEntity<List<CategoryDetailsView>> getAllCategories() {
        List<CategoryDetailsView> views = categoryDetailsViewRepository.findAll();
        return new ResponseEntity<>(views, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "fixedexpense/{productId}")
    public ResponseEntity<FixedExpensePerProductView> getFixedExpenseperProduct(@PathVariable String productId) {
        List<FixedExpensePerProductView> fixedExpensePerProductViews = fixedExpensePerProductViewRepository.findFirstByProductwiseFixedExpenseId_ProductIdOrderByEndDateDesc(productId);
        return new ResponseEntity<>(fixedExpensePerProductViews.get(0), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "pricebuckettransactions/{productId}")
    public ResponseEntity<List<PriceBucketTransactionView>> getPriceBucketTransactions(@PathVariable String productId) {
        List<PriceBucketTransactionView> transactions = priceBucketTransactionViewRepository.findByPriceBucketTransactionId_ProductId(productId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "pricebuckets/{productId}")
    public ResponseEntity<List<PriceBucketView>> getPriceBuckets(@PathVariable String productId) {
        List<PriceBucketView> priceBucketViews = priceBucketViewRepository.findByProductwisePriceBucketId_ProductId(productId);
        return new ResponseEntity<>(priceBucketViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "activationstatus/{productId}")
    public ResponseEntity<ProductActivationStatusView> getProductActivationStatus(@PathVariable String productId) {
        ProductActivationStatusView activation = productActivationStatusViewRepository.findOne(productId);
        return new ResponseEntity<>(activation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "actuals/{productId}")
    public ResponseEntity<List<ProductActualsView>> getProductActuals(@PathVariable String productId) {
        List<ProductActualsView> actuals = productActualsViewRepository.findByProductVersionId_ProductId(productId);
        return new ResponseEntity<>(actuals, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "configuration/{productId}")
    public ResponseEntity<ProductConfigurationView> getProductConfiguration(@PathVariable String productId) {
        ProductConfigurationView configuration = productConfigurationViewRepository.findOne(productId);
        return new ResponseEntity<>(configuration, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "trend/{productId}")
    public ResponseEntity<List<ProductForecastTrendView>> getProductTrend(@PathVariable String productId) {
        List<ProductForecastTrendView> trends = productForecastTrendViewRepository.findByForecastVersionId_ProductId(productId);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "forecast/{productId}")
    public ResponseEntity<List<ProductForecastView>> getForecast(@PathVariable String productId){
        List<ProductForecastView> forecasts=productForecastViewRepository.findByForecastVersionId_ProductId(productId);
        return new ResponseEntity<>(forecasts,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "pseudoactuals/{productId}")
    public ResponseEntity<List<ProductPseudoActualsView>> getPseudoActuals(@PathVariable String productId){
        List<ProductPseudoActualsView> forecasts=productPseudoActualsViewRepository.findByForecastVersionId_ProductId(productId);
        return new ResponseEntity<>(forecasts,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public ResponseEntity<ProductView> getProduct(@PathVariable String productId){
        ProductView productView=productViewRepository.findOne(productId);
        return new ResponseEntity<>(productView,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "recommendations/{productId}")
    public ResponseEntity<List<RecommendedPriceBucketView>> getPriceRecommendations(@PathVariable String productId){
        List<RecommendedPriceBucketView> recommendations=recommendedPriceBucketViewRepository.findByProductwisePriceBucketId_ProductId(productId);
        return new ResponseEntity<>(recommendations,HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "taggedrice/{productId}")
    public ResponseEntity<List<TaggedPriceVersionsView>> getTaggedPriceVersion(@PathVariable String productId){
        List<TaggedPriceVersionsView> taggedPrices = taggedPriceVersionsViewRepository.findByProductwiseTaggedPriceVersionId_ProductId(productId);
        return new ResponseEntity<>(taggedPrices,HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "varexp/{productId}")
    public ResponseEntity<List<VariableExpensePerProductView>> getVariableExpenses(@PathVariable String productId){
        List<VariableExpensePerProductView> varExps = variableExpensePerProductViewRepository.findFirstByProductwiseVariableExpenseId_ProductIdOrderByEndDateDesc(productId);
        return  new ResponseEntity<>(varExps,HttpStatus.OK);
    }
}