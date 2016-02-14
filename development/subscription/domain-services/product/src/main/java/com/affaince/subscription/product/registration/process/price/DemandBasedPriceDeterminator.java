package com.affaince.subscription.product.registration.process.price;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.product.registration.command.domain.PriceBucket;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mandark on 13-02-2016.
 */
public class DemandBasedPriceDeterminator implements PriceDeterminator {
    public void calculateOfferedPrice(Product product) {

        //demand function: Q=Intercept -(1/price elasticity of Demand)*P
        //cost function= Cost=intercept+variable*quantity
        //Retrieve the price buckets whose purchase price is same.
        //retreive the qunatity to be sold in coming month with extrapolation
        //Find out price elasticity
        //Find out marginal price and marginal cost
        //profit is max where mp=mc
        //take that price as the new offered price
        PriceBucket latestPriceBucket = product.getLatestPriceBucket();
        Map<LocalDate, PriceBucket> activePriceBuckets = product.getProductAccount().getActivePriceBuckets();
        List<PriceBucket> bucketsWithSamePurchasePrice = new ArrayList<PriceBucket>();
        List<Double> totalQuantitySubscribedWithSamePurchasePrice = new ArrayList<Double>();
        for (Map.Entry<LocalDate, PriceBucket> entry : activePriceBuckets.entrySet()) {
            PriceBucket activeBucket = entry.getValue();
            if (activeBucket.getPurchasePricePerUnit() == latestPriceBucket.getPurchasePricePerUnit()) {
                bucketsWithSamePurchasePrice.add(activeBucket);
                totalQuantitySubscribedWithSamePurchasePrice.add((double) activeBucket.getTotalQuantitySusbcribed());
            }
        }
        //extraploate quantity so as to understand how muchh is the target according to trend
        double demandedQuantity= extrapolateDemand(totalQuantitySubscribedWithSamePurchasePrice, bucketsWithSamePurchasePrice.size());

        //Find the demand function using regression
        double[] regressionParameters= proccessPriceDataRegression(bucketsWithSamePurchasePrice);
        double priceElasticityOfDemand=1/regressionParameters[1];

        //find the cost function
        //total price of a product for a quantity(to merchant)= (purchase price for that quantity + (fixed operating expense for that quantity) + (deivery charge per unit)*quantity
    }

    private double extrapolateDemand(List<Double> totalQuantitySubscribedWithSamePurchasePrice, int periodPerYear) {
        MathsProcessingService mathService = new MathsProcessingService();
        double[] forecastedQuantities = mathService.processForecastUsingTripleExponentialTimeSeries(totalQuantitySubscribedWithSamePurchasePrice.stream().mapToDouble(d -> d).toArray(), periodPerYear);
        return forecastedQuantities[0];
    }

    private double[] proccessPriceDataRegression(List<PriceBucket> activePriceBuckets){
        double[] priceVsQuantityArray = new double[activePriceBuckets.size()*2];
        int i=0;
        for(PriceBucket activePriceBucket: activePriceBuckets){
            priceVsQuantityArray[i++]=activePriceBucket.getOfferedPricePerUnit();
            priceVsQuantityArray[i++]=activePriceBucket.getTotalQuantitySusbcribed();
        }
        MathsProcessingService mathService = new MathsProcessingService();
        return mathService.processMultipleLinearRegression(priceVsQuantityArray,priceVsQuantityArray.length/2,1);
    }
}
