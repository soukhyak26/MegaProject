package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.pricing.query.view.ProductActualsView;
import com.affaince.subscription.pricing.query.view.ProductPseudoActualsView;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 16-08-2016.
 */
public class ProductPricingTrigger {
    public static final double SIGNIFICANCE_LEVEL = 0.05;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;
    @Autowired
    private ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;

    public ProductDemandTrend triggerProductPricing(String productId, double[] interpolatedForecastOnTotalSubscriptions) {
        boolean doTriggerPrice = false;
        final LocalDateTime currentDate = LocalDateTime.now();
        final ProductVersionId productVersionId = new ProductVersionId(productId, currentDate);
        //find the threshold percentage chane between interpolated forecast and actuals that should trigger price
        final double changeThresholdForPriceChange =
                productConfigurationViewRepository.findOne(productId).getTargetChangeThresholdForPriceChange();

        //get the actuals data from first to last in ascending order
        final Sort sort1 = new Sort(Sort.Direction.ASC, "productVersionId.fromDate");
        final List<ProductActualsView> productActualsViewList = productActualsViewRepository.findByProductVersionId_ProductId(productId, sort1);

        //Get latest value of Pseudo actuals( forecast for tomorrow) so as to add it to list of actuals.
        final Sort sort2 = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductPseudoActualsView latestProductPseudoActualsView = productPseudoActualsViewRepository.findByProductVersionId_ProductId(productId, sort2).get(0);

        //Find list of total subscriptions from actuals.Add total subscription count from latest pseudoactuals in the array
        final double[] totalSubscriptionsList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getTotalNumberOfExistingSubscriptions()).doubleValue()).mapToDouble(Double::doubleValue).toArray();
        double[] newTotalsubscriptionList = new double[totalSubscriptionsList.length + 1];
        //add all elements of historical actual subscription count,then add latest forecasted pseudoactuals count.
        System.arraycopy(totalSubscriptionsList, 0, newTotalsubscriptionList, 0, totalSubscriptionsList.length);
        newTotalsubscriptionList[newTotalsubscriptionList.length - 1] = latestProductPseudoActualsView.getTotalNumberOfExistingSubscriptions();

        //Create similar array of interpolated total subscriptioncount per day/half day from the weekly/monthly/quarterly forecast value
        double[] balancedInterpolatedForecastedTotalSubscriptionCount = new double[newTotalsubscriptionList.length];
        System.arraycopy(interpolatedForecastOnTotalSubscriptions, 0, balancedInterpolatedForecastedTotalSubscriptionCount, 0, newTotalsubscriptionList.length);
        double[][] observations = {balancedInterpolatedForecastedTotalSubscriptionCount, newTotalsubscriptionList};

        final List<double[]> classes = new ArrayList<double[]>();
        for (int i = 0; i < observations.length; i++) {
            classes.add(observations[i]);
        }
        //Run one way Anova test to see the probability of actuals and interpolated forecast being overlapping(same)/non-overlapping(different)
        OneWayAnova anova = new OneWayAnova();
        double fStatistic = anova.anovaFValue(classes); // F-value
        double pValue = anova.anovaPValue(classes);     // P-value
        System.out.println("f-value:" + fStatistic);
        System.out.println("p-value:" + pValue);
        boolean rejectNullHypothesis = anova.anovaTest(classes, SIGNIFICANCE_LEVEL);
        System.out.println("reject null hipothesis " + (100 - SIGNIFICANCE_LEVEL * 100) + "% = " + rejectNullHypothesis);

        //If null hipothesis is rejected it means that target is different than actual.. so need to trigger pricing
        if (rejectNullHypothesis) {
            double interpolatedForecastMean = findMean(balancedInterpolatedForecastedTotalSubscriptionCount);
            double actualsMean = findMean(newTotalsubscriptionList);
            double percentageMeanVariation = ((actualsMean - interpolatedForecastMean) / interpolatedForecastMean) * 100;
            //if difference is the mean values of actual subscription counts and interpolated+forecasted subscription counts is beyong the threshold value
            // then the pricing should be triggered for the product.
            if (interpolatedForecastMean > actualsMean && Math.abs(percentageMeanVariation) > changeThresholdForPriceChange) {
                return ProductDemandTrend.DOWNWARD;
            } else if (actualsMean > interpolatedForecastMean && Math.abs(percentageMeanVariation) > changeThresholdForPriceChange) {
                return ProductDemandTrend.UPWARD;
            } else {
                return ProductDemandTrend.NOCHANGE;
            }
        } else {
            return ProductDemandTrend.NOCHANGE;
        }
    }

    private double findMean(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }
}
