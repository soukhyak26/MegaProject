package com.affaince.subscription.product.services.pricing.processor.function;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.vo.RegressionResult;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.services.pricing.processor.exception.InaccurateRegressionException;
import com.affaince.subscription.product.vo.CoefficientsType;
import com.affaince.subscription.product.vo.FunctionCoefficients;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by mandark on 28-02-2016.
 */
public class RegressionBasedDemandFunctionProcessor implements FunctionProcessor<ProductView> {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;

    @Override
    public FunctionCoefficients processFunction(ProductView productView) {
        List<PriceBucketView> priceBucketStats = priceBucketViewRepository.findByProductVersionId_ProductId(productView.getProductId());

        final List<PriceBucketView> priceBucketsWithSamePurchasePrice=findBucketsWithSamePurchasePrice(priceBucketStats);
        Collections.sort(priceBucketsWithSamePurchasePrice);
        final PriceBucketView priceBucketJustBeforePurchasePriceChange = getPriceBucketJustBeforePurchasePriceChange(priceBucketStats, priceBucketsWithSamePurchasePrice.get(0));
        final Map <Double, Double> offerPriceVersesSubscriptionsMapping =
                offerPriceVersesSubscriptionMapping(priceBucketsWithSamePurchasePrice, priceBucketJustBeforePurchasePriceChange);
        List<Double> parametersList = new ArrayList<Double>();
        for (Map.Entry<Double, Double> entry : offerPriceVersesSubscriptionsMapping.entrySet()) {
            double offeredPrice= entry.getKey();
            double subscriptionCount = entry.getValue();
            parametersList.add(offeredPrice);
            parametersList.add(subscriptionCount);
        }

        RegressionResult result = MathsProcessingService.processMultipleLinearRegression(parametersList.stream().mapToDouble(Double::doubleValue).toArray(), parametersList.size() / 2, 1);
        if (result.getAdjustedRSquaredValue() < 0.5) {
            double[] regressionParamters = result.getRegressionParameters();
            FunctionCoefficients functionCoefficients = new FunctionCoefficients(productView.getProductId(), regressionParamters[0], regressionParamters[1], CoefficientsType.DEMAND_FUNCTION_COEFFICIENT);
            return functionCoefficients;
        } else {
            throw new InaccurateRegressionException();
        }

    }

    private Map<Double, Double> offerPriceVersesSubscriptionMapping(List<PriceBucketView> priceBucketsWithSamePurchasePrice, PriceBucketView priceBucketJustBeforePurchasePriceChange) {
        Map <Double, Double> offerPriceVersesSubscriptionMapping = new HashMap<>(priceBucketsWithSamePurchasePrice.size() + 1);
        double totalExistingSubscriptions = new Double(priceBucketJustBeforePurchasePriceChange.getNumberOfExistingCustomersAssociatedWithAPrice());
        for (PriceBucketView priceBucketView: priceBucketsWithSamePurchasePrice) {
            totalExistingSubscriptions = totalExistingSubscriptions + priceBucketView.getNumberOfExistingCustomersAssociatedWithAPrice();
            offerPriceVersesSubscriptionMapping.put(priceBucketView.getOfferedPricePerUnit(), totalExistingSubscriptions);
        }
        return offerPriceVersesSubscriptionMapping;
    }

    private List<PriceBucketView> findBucketsWithSamePurchasePrice(List<PriceBucketView> priceBucketViews) {
        double latestPurchasePrice = getLatestPriceBucket(priceBucketViews).getTaggedPriceVersion().getPurchasePricePerUnit();
        List<PriceBucketView> bucketsWithSamePurchasePrice = new ArrayList<>();

        for (PriceBucketView priceBucketView : priceBucketViews) {
            if (priceBucketView.getTaggedPriceVersion().getPurchasePricePerUnit() == latestPurchasePrice) {
                bucketsWithSamePurchasePrice.add(priceBucketView);
                //totalQuantitySubscribedWithSamePurchasePrice.add((double) activeBucket.getTotalQuantitySusbcribed());
            }
        }
        return bucketsWithSamePurchasePrice;
    }

    private PriceBucketView getLatestPriceBucket(List<PriceBucketView> activePriceBuckets) {
        PriceBucketView latestPriceBucketView = null;
        LocalDate max = activePriceBuckets.get(0).getFromDate();
        for (PriceBucketView priceBucketView:activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(max) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }

    private PriceBucketView getPriceBucketJustBeforePurchasePriceChange(List<PriceBucketView> activePriceBuckets,
                                                                        PriceBucketView firstPriceBucketOfLatestPriceBucket) {
        Collections.sort(activePriceBuckets);
        return activePriceBuckets.get(activePriceBuckets.indexOf(firstPriceBucketOfLatestPriceBucket)-1);
    }
}
