package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.vo.RegressionResult;
import com.affaince.subscription.pricing.vo.PriceBucket;
import com.affaince.subscription.pricing.vo.Product;

import java.util.List;

/**
 * Created by mandark on 13-02-2016.
 */
public class DemandBasedPriceDeterminator implements PriceDeterminator {
    public double calculateOfferedPrice(Product product) {

        //cost function= Cost=intercept+variable*quantity
        //Retrieve the price buckets whose purchase price is same.
        //retreive the qunatity to be sold in coming month with extrapolation
        //Find out price elasticity
        //Find out marginal price and marginal cost
        //profit is max where mp=mc
        //take that price as the new offered price
        //extraploate quantity so as to understand how much is the target according to trend
        /*List<PriceBucket> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(product);
*//*
        List<Double> quantitySubscribedPerPriceWithSamePurchasePrice = new ArrayList<>();
        bucketsWithSamePurchasePrice.forEach(priceBucket -> quantitySubscribedPerPriceWithSamePurchasePrice.add(priceBucket.getPurchasePricePerUnit()));
*//*
        //Is this correct/required?
        //double newlyDemandedQuantity = extrapolateDemand(quantitySubscribedPerPriceWithSamePurchasePrice, bucketsWithSamePurchasePrice.size());
        //Find the demand function using regression
        RegressionResult regressionResult = proccessPriceDataRegression(bucketsWithSamePurchasePrice);
        double demandFunctionIntercept = regressionResult.getRegressionParameters()[0];
        double demandFunctionSlope = regressionResult.getRegressionParameters()[1];*/
//        double costFunctonSlope=product.getProductAccount().getVariableExpenseSlope();
  //      double demandedQuantity=demandFunctionIntercept/(2+(demandFunctionSlope*costFunctonSlope));
        /**Explaintation of below formula****************
        costOfAProduct=fixdCost + CostFunctionSlope*VariableCost


        *************************************************/
        //find price elasticity for the desired quantity
        //price elasiticty of demand = (-1/slope)*(intercept -slope*quantity/qunatity);
        //for profit making price elasticity has to be <-1
/*
        double priceElasticity= (-1/slope)*((intercept-(slope*newlyDemandedQuantity))/newlyDemandedQuantity);
        double marginalRevenue=intercept - 2*slope*newlyDemandedQuantity;
        double newPrice=marginalRevenue/(1+(1/priceElasticity));
*/

        //find the cost function
        //total cost of a product for a quantity(to merchant)= (purchase price for that quantity + (fixed operating expense for that quantity) + (deivery charge per unit)*quantity
/*
        double totalCostOfProduct=bucketsWithSamePurchasePrice.get(0).getPurchasePricePerUnit()+product.getProductAccount().getLatestPerformanceTracker().getFixedOperatingExpensePerUnit() + costFunctionSlope*product.getProductAccount().getLatestPerformanceTracker().getFixedOperatingExpensePerUnit();
*/
    return 0.0;
    }


    private double extrapolateDemand(List<Double> totalQuantitySubscribedWithSamePurchasePrice, int periodPerYear) {
        MathsProcessingService mathService = new MathsProcessingService();
        double[] forecastedQuantities = mathService.processForecastUsingTripleExponentialTimeSeries(totalQuantitySubscribedWithSamePurchasePrice.stream().mapToDouble(d -> d).toArray(), periodPerYear);
        return forecastedQuantities[0];
    }

    private RegressionResult proccessPriceDataRegression(List<PriceBucket> activePriceBuckets) {
        double[] priceVsQuantityArray = new double[activePriceBuckets.size() * 2];
        int i = 0;
        for (PriceBucket activePriceBucket : activePriceBuckets) {
            priceVsQuantityArray[i++] = activePriceBucket.getOfferedPricePerUnit();
            priceVsQuantityArray[i++] = activePriceBucket.getTotalQuantitySusbcribed();
        }
        MathsProcessingService mathService = new MathsProcessingService();
        return MathsProcessingService.processMultipleLinearRegression(priceVsQuantityArray, priceVsQuantityArray.length / 2, 1);
    }
}
