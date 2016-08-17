package com.affaince.subscription.product.services.pricing.processor.calculator;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.pricing.processor.calculator.breakevenprice.BreakEvenPriceCalculator;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.LocalDate;

import java.util.*;

/**
 * Created by mandark on 27-03-2016.
 */
public abstract class AbstractPriceCalculator {
    private AbstractPriceCalculator nextCalculator;
    private BreakEvenPriceCalculator breakEvenPriceCalculator;

    public AbstractPriceCalculator getNextCalculator() {
        return this.nextCalculator;
    }

    public void setNextCalculator(AbstractPriceCalculator nextCalculator) {
        this.nextCalculator = nextCalculator;
    }

    protected double calculateBreakEvenPrice(double purchasePrice, double fixedOperatingExpensePerUnit, double variableExpensePerUnit) {
        //We will include merchant's profit if required...not now
        Map<String, Double> bePriceContributors = new HashMap<>();
        bePriceContributors.put("purchasePrice", purchasePrice);
        bePriceContributors.put("fixedOperatingExpense", fixedOperatingExpensePerUnit);
        bePriceContributors.put("variableOperatingExpense", variableExpensePerUnit);
        final double breakEvenPrice = breakEvenPriceCalculator.calculateBreakEvenPrice(bePriceContributors);
        return breakEvenPrice;
    }

    protected double calculateSlopeOfDemandCurve(double latestDemand, double demandAssociatedWithEarlierPrice, double latestPrice, double earlierPrice) {
        return ((latestPrice - earlierPrice) / (latestDemand - demandAssociatedWithEarlierPrice));
    }


    protected PriceBucketView findEarlierPriceBucketTo(PriceBucketView priceBucket, List<PriceBucketView> activePriceBuckets) {
        PriceBucketView earlierPriceBucket = null;
        List<PriceBucketView> earlierPriceBuckets = new ArrayList<PriceBucketView>();
        LocalDate latestBucketDate = priceBucket.getFromDate();
        for (PriceBucketView tempPriceBucket : activePriceBuckets) {
            if (tempPriceBucket.getFromDate().isBefore(latestBucketDate)) {
                if (null != earlierPriceBucket) {
                    if (tempPriceBucket.getFromDate().isAfter(earlierPriceBucket.getFromDate())) {
                        earlierPriceBucket = tempPriceBucket;
                    }
                } else {
                    earlierPriceBucket = tempPriceBucket;
                }
            }
        }

        return earlierPriceBucket;
    }

    protected List<Double> extrapolateDemand(List<Double> totalQuantitySubscribedWithSamePurchasePrice, int periodPerYear) {
        MathsProcessingService mathService = new MathsProcessingService();
        double[] forecastedQuantities = mathService.processForecastUsingTripleExponentialTimeSeries(totalQuantitySubscribedWithSamePurchasePrice.stream().mapToDouble(d -> d).toArray(), periodPerYear);
        ArrayList<Double> forecastedQuantitiesList = new ArrayList<Double>(forecastedQuantities.length);
        return Arrays.asList(ArrayUtils.toObject(forecastedQuantities));
    }

    protected List<PriceBucketView> findBucketsWithSamePurchasePrice(String productId, List<PriceBucketView> activePriceBuckets) {
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);
        List<PriceBucketView> bucketsWithSamePurchasePrice = new ArrayList<PriceBucketView>();

        for (PriceBucketView activeBucket : activePriceBuckets) {
            if (activeBucket.getTaggedPriceVersion().getPurchasePricePerUnit() == latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit()) {
                bucketsWithSamePurchasePrice.add(activeBucket);
            }
        }
        return bucketsWithSamePurchasePrice;
    }

    protected PriceBucketView getLatestPriceBucket(List<PriceBucketView> activePriceBuckets) {
        PriceBucketView latestPriceBucketView = activePriceBuckets.get(0);
        for (PriceBucketView priceBucketView : activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(latestPriceBucketView.getFromDate()) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }

    protected double calculateOfferedPrice(double intercept, double slope, double quantity) {
        return intercept + (slope * quantity);
    }

    protected double calculateExpectedDemand(ProductForecastView productForecastView, ProductActualsView productActualsView) {
        return productForecastView.getTotalNumberOfExistingSubscriptions() - productActualsView.getTotalNumberOfExistingSubscriptions();
    }

    public abstract PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductActualsView productActualsView, ProductForecastView productForecastView);

}
