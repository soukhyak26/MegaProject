package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductStatisticsViewRepository;
import com.affaince.subscription.pricing.vo.CoefficientsType;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import com.affaince.subscription.pricing.vo.PriceDeterminationCriteria;
import com.affaince.subscription.pricing.vo.QuantityBasedProductFinancialResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mandark on 13-02-2016.
 */
public class DemandBasedPriceDeterminator implements PriceDeterminator {
    public double calculateOfferedPrice(PriceDeterminationCriteria criteria) {
        //cost function: cost=yInterceptCost+slopeCost*Quantity
        //demand function: subscriptionCount=yInterceptDemand + slopeDemand*quantity
        //price=(yInterceptDemand-subscriptionCount)/(slopeDemand)
        List<CrudRepository> repositories = criteria.getDataRepositories();
        List<FunctionCoefficients> demandAndCostFunctionCoefficients = criteria.getListOfCriteriaElements();
        PriceBucketViewRepository priceBucketViewRepository = (PriceBucketViewRepository) repositories.get(0);
        ProductStatisticsViewRepository productStatisticsViewRepository = (ProductStatisticsViewRepository) repositories.get(1);
        FunctionCoefficients demandFunctionCoeffiecients = demandAndCostFunctionCoefficients.stream().filter(coeffieient -> coeffieient.getType().equals(CoefficientsType.DEMAND_FUNCTION_COEFFICIENT)).findFirst().get();
        FunctionCoefficients costFunctionCoefficients = demandAndCostFunctionCoefficients.stream().filter(coeffieient -> coeffieient.getType().equals(CoefficientsType.COST_FUNCTION_COEFFICIENT)).findFirst().get();
        Map<Long, QuantityBasedProductFinancialResult> quantityBasedResults = obtainQuantityBasedFinancialResultsForProduct(demandFunctionCoeffiecients, costFunctionCoefficients);
        //find max profit and quantity at which we have max profit
        final long keyForMaxProfit = quantityBasedResults.entrySet().stream().max((entry1, entry2) -> entry1.getValue().getProfitAtQuantity() > entry2.getValue().getProfitAtQuantity() ? 1 : -1).get().getKey();
        final double maximumProfit = quantityBasedResults.get(keyForMaxProfit).getProfitAtQuantity();
        final long quantityAtMaximumProfit = quantityBasedResults.get(keyForMaxProfit).getQuantity();
        final double priceAtMaximumProfit = quantityBasedResults.get(keyForMaxProfit).getPriceAtQuantity();
        return 0.0;
    }

    private Map<Long, QuantityBasedProductFinancialResult> obtainQuantityBasedFinancialResultsForProduct(FunctionCoefficients demandFunctionCoefficients, FunctionCoefficients costFunctionCoefficients) {
        //first find out quantity at which price will be zero
        final long quantityAtZeroPrice = Math.round(demandFunctionCoefficients.getIntercept() / (demandFunctionCoefficients.getSlope()));
        //iterate from 0 quantity to quantityAtZeroPrice and find price,cost,revenue ,profit
        Map<Long, QuantityBasedProductFinancialResult> quantityBasedResults = new TreeMap<Long, QuantityBasedProductFinancialResult>();
        for (long tempQuantity = 0; tempQuantity <= quantityAtZeroPrice; tempQuantity++) {
            double priceAtQuantity = demandFunctionCoefficients.getIntercept() + (demandFunctionCoefficients.getSlope() * tempQuantity);
            double costAtQuantity = costFunctionCoefficients.getIntercept() + (costFunctionCoefficients.getSlope() * tempQuantity);
            double revenueAtQuantity = priceAtQuantity * tempQuantity;
            double profitAtQuantity = revenueAtQuantity - costAtQuantity;
            QuantityBasedProductFinancialResult result = new QuantityBasedProductFinancialResult(tempQuantity, priceAtQuantity, costAtQuantity, revenueAtQuantity, profitAtQuantity);
            quantityBasedResults.put(tempQuantity, result);
        }
        return quantityBasedResults;
    }
    private double extrapolateDemand(List<Double> totalQuantitySubscribedWithSamePurchasePrice, int periodPerYear) {
        MathsProcessingService mathService = new MathsProcessingService();
        double[] forecastedQuantities = mathService.processForecastUsingTripleExponentialTimeSeries(totalQuantitySubscribedWithSamePurchasePrice.stream().mapToDouble(d -> d).toArray(), periodPerYear);
        return forecastedQuantities[0];
    }

}
