package com.affaince.subscription.product.services.pricing.calculator;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.factory.PriceBucketFactory;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mandark on 27-03-2016.
 */
public abstract class AbstractPriceCalculator {
    @Value("${pricing.calculator.pricehistorycount.max.fordefaultpricing}")
    protected int maxHistoryCountforDefaultPricing;
    private AbstractPriceCalculator nextCalculator;

    public AbstractPriceCalculator getNextCalculator() {
        return this.nextCalculator;
    }

    public void setNextCalculator(AbstractPriceCalculator nextCalculator) {
        if (null == this.nextCalculator) {
            this.nextCalculator = nextCalculator;
        } else {
            this.nextCalculator.setNextCalculator(nextCalculator);
        }
    }


    protected double calculateSlopeOfDemandCurve(double latestDemand, double demandAssociatedWithEarlierPrice, double latestPrice, double earlierPrice) {
        return ((latestPrice - earlierPrice) / (latestDemand - demandAssociatedWithEarlierPrice));
    }


    protected List<Double> extrapolateDemand(List<Double> totalQuantitySubscribedWithSamePurchasePrice, int periodPerYear) {
        MathsProcessingService mathService = new MathsProcessingService();
        double[] forecastedQuantities = MathsProcessingService.processForecastUsingTripleExponentialTimeSeries(totalQuantitySubscribedWithSamePurchasePrice.stream().mapToDouble(d -> d).toArray(), periodPerYear);
        ArrayList<Double> forecastedQuantitiesList = new ArrayList<Double>(forecastedQuantities.length);
        return Arrays.asList(ArrayUtils.toObject(forecastedQuantities));
    }


    protected double calculateOfferedPrice(double intercept, double slope, double quantity) {
        return intercept + (slope * quantity);
    }

    protected double calculateExpectedDemand(ProductForecastView productForecastView, ProductActualsView productActualsView) {
        return productForecastView.getTotalNumberOfExistingSubscriptions() - productActualsView.getTotalNumberOfExistingSubscriptions();
    }

    //This is just a utility method being used by Price Calculators for creating a price bucket.. Hence it should not emit events.
    public PriceBucket createNewPriceBucket(String productId,String latestPriceBucketId, PriceTaggedWithProduct taggedPriceVersion, double offeredPrice, ProductPricingCategory productPricingCategory) {
         //&& findLatestActivePriceBucket().getFixedOfferedPriceOrPercentDiscountPerUnit() != offeredPriceOrPercent
        LocalDateTime fromDate = SysDateTime.now();
        if (productPricingCategory == ProductPricingCategory.PRICE_COMMITMENT || productPricingCategory == ProductPricingCategory.NO_COMMITMENT){
            return PriceBucketFactory.createPriceBucket(productId, latestPriceBucketId, productPricingCategory, taggedPriceVersion, offeredPrice, EntityStatus.CREATED, fromDate);
        }else if(productPricingCategory == ProductPricingCategory.DISCOUNT_COMMITMENT){
            double percentDiscount= (taggedPriceVersion.getMRP()-offeredPrice)/taggedPriceVersion.getMRP();
            return PriceBucketFactory.createPriceBucket(productId, latestPriceBucketId, productPricingCategory, taggedPriceVersion, percentDiscount, EntityStatus.CREATED, fromDate);
        }
        return null;
    }

    public abstract PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend);

}
