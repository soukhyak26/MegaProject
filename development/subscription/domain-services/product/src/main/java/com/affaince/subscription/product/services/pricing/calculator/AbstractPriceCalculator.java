package com.affaince.subscription.product.services.pricing.calculator;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.pricing.calculator.breakevenprice.BreakEvenPriceCalculator;
import org.apache.commons.lang3.ArrayUtils;

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



    protected List<Double> extrapolateDemand(List<Double> totalQuantitySubscribedWithSamePurchasePrice, int periodPerYear) {
        MathsProcessingService mathService = new MathsProcessingService();
        double[] forecastedQuantities = mathService.processForecastUsingTripleExponentialTimeSeries(totalQuantitySubscribedWithSamePurchasePrice.stream().mapToDouble(d -> d).toArray(), periodPerYear);
        ArrayList<Double> forecastedQuantitiesList = new ArrayList<Double>(forecastedQuantities.length);
        return Arrays.asList(ArrayUtils.toObject(forecastedQuantities));
    }


    protected double calculateOfferedPrice(double intercept, double slope, double quantity) {
        return intercept + (slope * quantity);
    }

    protected double calculateExpectedDemand(ProductForecastView productForecastView, ProductActualsView productActualsView) {
        return productForecastView.getTotalNumberOfExistingSubscriptions() - productActualsView.getTotalNumberOfExistingSubscriptions();
    }

    public abstract PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend);

}
