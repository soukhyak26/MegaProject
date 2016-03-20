package com.affaince.subscription.pricing.processor;


import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.vo.ProductMonthlyVersionId;
import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductStatisticsViewRepository;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.vo.CoefficientsType;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import com.affaince.subscription.pricing.vo.PriceDeterminationCriteria;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultPriceDeterminator implements PriceDeterminator {


    public DefaultPriceDeterminator() {

    }

    @Override
    public double calculateOfferedPrice(PriceDeterminationCriteria priceDeterminationCriteria) {
        List<CrudRepository> repositories = priceDeterminationCriteria.getDataRepositories();
        List<FunctionCoefficients> demandAndCostFunctionCoefficients = priceDeterminationCriteria.getListOfCriteriaElements();

        PriceBucketViewRepository priceBucketViewRepository = (PriceBucketViewRepository) repositories.stream().filter(repository -> repository.getClass().isAssignableFrom(PriceBucketViewRepository.class)).findFirst().get();
        ProductStatisticsViewRepository productStatisticsViewRepository = (ProductStatisticsViewRepository) repositories.stream().filter(repository -> repository.getClass().isAssignableFrom(ProductStatisticsViewRepository.class)).findFirst().get();

        FunctionCoefficients demandFunctionCoeffiecients = demandAndCostFunctionCoefficients.stream().filter(coefficient -> coefficient.getType().equals(CoefficientsType.DEMAND_FUNCTION_COEFFICIENT)).findFirst().get();
        final String productId = demandFunctionCoeffiecients.getProductId();
        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(productId);
        ProductStatisticsView productStatisticsView = productStatisticsViewRepository.findOne(new ProductMonthlyVersionId(productId, YearMonth.now()));
        PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);

        final double MRP = latestPriceBucket.getMRP();
        final double breakEvenPrice = calculateBreakEvenPrice(latestPriceBucket.getPurchasePricePerUnit(), productStatisticsView.getFixedOperatingExpense(), productStatisticsView.getVariableOperatingExpense());

        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, activePriceBuckets);
        List<Double> totalQuantitySubscribedWithSamePurchasePrice = bucketsWithSamePurchasePrice.stream().map(priceBucketView -> new Long(priceBucketView.getNumberOfExistingCustomersAssociatedWithAPrice()).doubleValue()).collect(Collectors.toList());
        //if price is entered by merchant but there is no subscripton yet as the product is not active yet...
        if (activePriceBuckets.size() == 1 && totalQuantitySubscribedWithSamePurchasePrice.size() == 0) {
            return latestPriceBucket.getOfferedPricePerUnit();
        } else if ((activePriceBuckets.size() == 1 && totalQuantitySubscribedWithSamePurchasePrice.size() == 1 && totalQuantitySubscribedWithSamePurchasePrice.get(0) == activePriceBuckets.get(0).getNumberOfExistingCustomersAssociatedWithAPrice()) || (activePriceBuckets.size() > 1 && activePriceBuckets.size() <= 5 && totalQuantitySubscribedWithSamePurchasePrice.size() == activePriceBuckets.size())) {
            //subscription to single price,nothing to extrapolate so far
            //here I assume that forecast subscription count will give close to reality figure as the forecast has once been corrected based on first set of subscription
            final double expectedDemand = productStatisticsView.getForecastedProductSubscriptionCount();
            final double intercept = MRP;
            double earlierPrice = 0;
            double demandAssociatedWithEarlierPrice = 0;
            if (activePriceBuckets.size() == 1) {
                earlierPrice = MRP;
                demandAssociatedWithEarlierPrice = 0;
            } else {
                final PriceBucketView earlierPriceBucket = findEarlierPriceBucketTo(latestPriceBucket, activePriceBuckets);
                earlierPrice = earlierPriceBucket.getOfferedPricePerUnit();
                demandAssociatedWithEarlierPrice = earlierPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice();
            }
            final double slope = calculateSlopeOfDemandCurve(latestPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice(), demandAssociatedWithEarlierPrice, latestPriceBucket.getOfferedPricePerUnit(), earlierPrice);
            final double newOfferedPrice = calculatePriceBasedOnSlopeAndIntercept(slope, intercept, expectedDemand);
            return newOfferedPrice;
        } else {
            List<Double> extrapolatedDemands = extrapolateDemand(totalQuantitySubscribedWithSamePurchasePrice, 12);
            final double intercept = MRP;
            final double expectedDemand = extrapolatedDemands.get(0);
            //One known defect-while searching earlier price bucket,it should be having same purchase price-to be fixed
            final PriceBucketView earlierPriceBucket = findEarlierPriceBucketTo(latestPriceBucket, activePriceBuckets);
            final double earlierPrice = earlierPriceBucket.getOfferedPricePerUnit();
            final double demandAssociatedWithEarlierPrice = earlierPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice();
            final double slope = calculateSlopeOfDemandCurve(latestPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice(), demandAssociatedWithEarlierPrice, latestPriceBucket.getOfferedPricePerUnit(), earlierPrice);
            final double newOfferedPrice = calculatePriceBasedOnSlopeAndIntercept(slope, intercept, expectedDemand);
            return newOfferedPrice;
        }
    }

    private double calculateBreakEvenPrice(double purchasePrice, double fixedOperatingExpensePerUnit, double variableExpensePerUnit) {
        //We will include merchant's profit if required...not now
        final double breakEvenPrice = purchasePrice + fixedOperatingExpensePerUnit + variableExpensePerUnit;
        return breakEvenPrice;
    }

    private double calculateSlopeOfDemandCurve(double latestDemand, double demandAssociatedWithEarlierPrice, double latestPrice, double earlierPrice) {
        return ((latestPrice - earlierPrice) / (latestDemand - demandAssociatedWithEarlierPrice));
    }

    private double calculatePriceBasedOnSlopeAndIntercept(double slope, double intercept, double expectedDemand) {
        return (intercept + (slope * expectedDemand));
    }

    private PriceBucketView findEarlierPriceBucketTo(PriceBucketView priceBucket, List<PriceBucketView> activePriceBuckets) {
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

    private List<Double> extrapolateDemand(List<Double> totalQuantitySubscribedWithSamePurchasePrice, int periodPerYear) {
        MathsProcessingService mathService = new MathsProcessingService();
        double[] forecastedQuantities = mathService.processForecastUsingTripleExponentialTimeSeries(totalQuantitySubscribedWithSamePurchasePrice.stream().mapToDouble(d -> d).toArray(), periodPerYear);
        ArrayList<Double> forecastedQuantitiesList = new ArrayList<Double>(forecastedQuantities.length);
        return Arrays.asList(ArrayUtils.toObject(forecastedQuantities));
    }

    private List<PriceBucketView> findBucketsWithSamePurchasePrice(String productId, List<PriceBucketView> activePriceBuckets) {
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);
        List<PriceBucketView> bucketsWithSamePurchasePrice = new ArrayList<PriceBucketView>();

        for (PriceBucketView activeBucket : activePriceBuckets) {
            if (activeBucket.getPurchasePricePerUnit() == latestPriceBucket.getPurchasePricePerUnit()) {
                bucketsWithSamePurchasePrice.add(activeBucket);
            }
        }
        return bucketsWithSamePurchasePrice;
    }

    private PriceBucketView getLatestPriceBucket(List<PriceBucketView> activePriceBuckets) {
        PriceBucketView latestPriceBucketView = null;
        LocalDate max = activePriceBuckets.get(0).getFromDate();
        for (PriceBucketView priceBucketView : activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(max) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }

   /* public static void main (String [] args) {
        final double purchasePrice = 18;
        final double operatingExpensesPerProductPerUnit = 3;
        final double MRP = 27;
        final double expectedMerchantProfit = 0.1;
        final double breakevenPrice = purchasePrice + operatingExpensesPerProductPerUnit;
        final double netProfit = MRP - breakevenPrice;
        final double priceAfterMerchantProfit = MRP - (netProfit - expectedMerchantProfit * breakevenPrice);
        //Offered price without basket discount= Price after merchant profit +(1-(percetnage margin to be given*product demand density))
        final double latestDemandDensityActuals = 0.518161018;
        final double profitSharingPercentageAtZeroDemand= 0.8;
        final double profitSharingPercentageForADemand = 0.55;
        final double slopeOfProfitShareForADemand= (profitSharingPercentageAtZeroDemand- latestDemandDensityActuals)
                /profitSharingPercentageForADemand;
        final double defaultOfferedPrice=priceAfterMerchantProfit + (1-(profitSharingPercentageAtZeroDemand - latestDemandDensityActuals)
                /slopeOfProfitShareForADemand)*netProfit;
        System.out.println(defaultOfferedPrice);
        //product.setLatestOfferedPriceActuals (defaultOfferedPrice);
    }*/
}
