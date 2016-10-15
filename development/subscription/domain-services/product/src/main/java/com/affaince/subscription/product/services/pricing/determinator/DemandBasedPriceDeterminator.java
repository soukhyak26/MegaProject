package com.affaince.subscription.product.services.pricing.determinator;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.*;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mandark on 13-02-2016.
 */
@Deprecated
public class DemandBasedPriceDeterminator {// implements PriceDeterminator {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    ProductActualMetricsViewRepository productActualMetricsViewRepository;

    public PriceBucketView calculateOfferedPrice(PriceDeterminationCriteria criteria) {
        //cost function: cost=yInterceptCost+slopeCost*Quantity
        //demand function: subscriptionCount=yInterceptDemand + slopeDemand*quantity
        //price=(yInterceptDemand-subscriptionCount)/(slopeDemand)
        //List<CrudRepository> repositories = criteria.getDataRepositories();
        List<FunctionCoefficients> demandAndCostFunctionCoefficients = criteria.getListOfCriteriaElements();

/*
        PriceBucketViewRepository priceBucketViewRepository = (PriceBucketViewRepository) repositories.stream().filter(repository -> repository.getClass().isAssignableFrom(PriceBucketViewRepository.class)).findFirst().get();
        ProductMonthlyStatisticsViewRepository productActualMetricsViewRepository = (ProductMonthlyStatisticsViewRepository) repositories.stream().filter(repository -> repository.getClass().isAssignableFrom(ProductMonthlyStatisticsViewRepository.class)).findFirst().get();
*/

        FunctionCoefficients demandFunctionCoeffiecients = demandAndCostFunctionCoefficients.stream().filter(coefficient -> coefficient.getType().equals(CoefficientsType.DEMAND_FUNCTION_COEFFICIENT)).findFirst().get();
        FunctionCoefficients costFunctionCoefficients = demandAndCostFunctionCoefficients.stream().filter(coefficient -> coefficient.getType().equals(CoefficientsType.COST_FUNCTION_COEFFICIENT)).findFirst().get();

        final String productId = demandFunctionCoeffiecients.getProductId();
        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(productId);
        PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);

        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, activePriceBuckets);
        List<Double> totalQuantitySubscribedWithSamePurchasePrice = bucketsWithSamePurchasePrice.stream().map(priceBucketView -> new Long(priceBucketView.getNumberOfExistingCustomersAssociatedWithAPrice()).doubleValue()).collect(Collectors.toList());
        //first and foremost : find if the demand is going up or it is going down for a product using extrapolation
        List<Double> forecastedDemands = extrapolateDemand(totalQuantitySubscribedWithSamePurchasePrice, 12);
        double offeredPrice= determinePriceBasedOnCurrentAndForecastedDemand(demandFunctionCoeffiecients, costFunctionCoefficients, latestPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice(), forecastedDemands);
        LocalDateTime currentDate = SysDateTime.now();
        PriceBucketView newPriceBucket = new PriceBucketView(new ProductVersionId(latestPriceBucket.getProductVersionId().getProductId(), currentDate));
        DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");

        final String taggedPriceVersionId = productId + currentDate.toString(format);

        PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMRP(), currentDate);
        newPriceBucket.setTaggedPriceVersion(taggedPriceVersion);
        //newPriceBucket.setSlope(0.0);//slope to be calculated WIP
        newPriceBucket.setEntityStatus(EntityStatus.ACTIVE);
        newPriceBucket.setOfferedPriceOrPercentDiscountPerUnit(offeredPrice);
        return newPriceBucket;

    }


    private double determinePriceBasedOnCurrentAndForecastedDemand(FunctionCoefficients demandFunctionCoeffiecients, FunctionCoefficients costFunctionCoefficients, long currentDemand, List<Double> forecastedDemands) {
        Map<Long, QuantityBasedProductFinancialResult> quantityBasedResults = obtainQuantityBasedFinancialResultsForProduct(demandFunctionCoeffiecients, costFunctionCoefficients);
        //find max profit and quantity at which we have max profit
        final long keyForMaxProfit = quantityBasedResults.entrySet().stream().max((entry1, entry2) -> entry1.getValue().getProfitAtQuantity() > entry2.getValue().getProfitAtQuantity() ? 1 : -1).get().getKey();
        final double maximumProfit = quantityBasedResults.get(keyForMaxProfit).getProfitAtQuantity();
        final long quantityAtMaximumProfit = quantityBasedResults.get(keyForMaxProfit).getQuantity();
        final double priceAtMaximumProfit = quantityBasedResults.get(keyForMaxProfit).getPriceAtQuantity();
        final long immediateForecastedDemand = forecastedDemands.get(0).longValue();
        if (currentDemand <= immediateForecastedDemand) {// forecasted demand is more than current demand : product is doing good
            if (immediateForecastedDemand < quantityAtMaximumProfit) { //here demand should be price elastic ..return price for this forecasted qunatity from quantityBasedResults map
                QuantityBasedProductFinancialResult resultForForecastedQuantity = quantityBasedResults.get(immediateForecastedDemand);
                if (resultForForecastedQuantity.getPriceElasticityOfDemand() < -1) {//is demand price elastic?
                    return resultForForecastedQuantity.getPriceAtQuantity();
                }

            } else if (immediateForecastedDemand == quantityAtMaximumProfit) {//demand unitary elastic
                return priceAtMaximumProfit;
            } else if (immediateForecastedDemand > quantityAtMaximumProfit) {//demand is price elastic??

            }
        } else if (currentDemand > immediateForecastedDemand) { //forecast is lesser than current demand:may need to reduce price

        }
        return 0.0;
    }

    private Map<Long, QuantityBasedProductFinancialResult> obtainQuantityBasedFinancialResultsForProduct(FunctionCoefficients demandFunctionCoefficients, FunctionCoefficients costFunctionCoefficients) {
        //first find out quantity at which price will be zero
        final long quantityAtZeroPrice = Math.round(demandFunctionCoefficients.getIntercept() / (demandFunctionCoefficients.getSlope()));
        //iterate from 0 quantity to quantityAtZeroPrice and find price,cost,revenue ,profit
        Map<Long, QuantityBasedProductFinancialResult> quantityBasedResults = new TreeMap<Long, QuantityBasedProductFinancialResult>();
        for (long tempQuantity = 0; tempQuantity <= quantityAtZeroPrice; tempQuantity++) {
            double priceAtQuantity = demandFunctionCoefficients.getIntercept() + (demandFunctionCoefficients.getSlope() * tempQuantity);
            double priceElasticityOfDemand = findPriceElasticityOfDemand(demandFunctionCoefficients, tempQuantity);
            double costAtQuantity = costFunctionCoefficients.getIntercept() + (costFunctionCoefficients.getSlope() * tempQuantity);
            double revenueAtQuantity = priceAtQuantity * tempQuantity;
            double profitAtQuantity = revenueAtQuantity - costAtQuantity;
            QuantityBasedProductFinancialResult result = new QuantityBasedProductFinancialResult(tempQuantity, priceAtQuantity, costAtQuantity, revenueAtQuantity, profitAtQuantity, priceElasticityOfDemand);
            quantityBasedResults.put(tempQuantity, result);
        }
        return quantityBasedResults;
    }

    private double findPriceElasticityOfDemand(FunctionCoefficients demandFunctionCoeffiecients, long quantity) {
        final double intercept = demandFunctionCoeffiecients.getIntercept();
        final double slope = demandFunctionCoeffiecients.getSlope();
        final double priceElasticityOfDemand = (-1 / slope) * ((intercept - (slope * quantity)) / quantity);
        return priceElasticityOfDemand;
    }

    private List<Double> extrapolateDemand(List<Double> totalQuantitySubscribedWithSamePurchasePrice, int periodPerYear) {
        MathsProcessingService mathService = new MathsProcessingService();
        double[] forecastedQuantities = MathsProcessingService.processForecastUsingTripleExponentialTimeSeries(totalQuantitySubscribedWithSamePurchasePrice.stream().mapToDouble(d -> d).toArray(), periodPerYear);
        ArrayList<Double> forecastedQuantitiesList = new ArrayList<Double>(forecastedQuantities.length);
        return Arrays.asList(ArrayUtils.toObject(forecastedQuantities));
    }

    private List<PriceBucketView> findBucketsWithSamePurchasePrice(String productId, List<PriceBucketView> activePriceBuckets) {
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);
        List<PriceBucketView> bucketsWithSamePurchasePrice = new ArrayList<PriceBucketView>();

        for (PriceBucketView activeBucket : activePriceBuckets) {
            if (activeBucket.getTaggedPriceVersion().getPurchasePricePerUnit() == latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit()) {
                bucketsWithSamePurchasePrice.add(activeBucket);
            }
        }
        return bucketsWithSamePurchasePrice;
    }

    private PriceBucketView getLatestPriceBucket(List<PriceBucketView> activePriceBuckets) {
        PriceBucketView latestPriceBucketView = null;
        LocalDateTime max = activePriceBuckets.get(0).getFromDate();
        for (PriceBucketView priceBucketView : activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(max) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }


}
