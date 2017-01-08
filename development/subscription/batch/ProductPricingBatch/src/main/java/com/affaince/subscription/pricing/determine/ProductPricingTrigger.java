package com.affaince.subscription.pricing.determine;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.pricing.build.interpolate.ForecastInterpolatedSubscriptionCountFinder;
import com.affaince.subscription.pricing.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.pricing.query.view.ProductActualsView;
import com.affaince.subscription.pricing.query.view.ProductPseudoActualsView;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.joda.time.LocalDate;
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

    @Autowired
    private ForecastInterpolatedSubscriptionCountFinder forecastInterpolatedSubscriptionCountFinder;

    public ProductDemandTrend triggerProductPricingNew(String productId){
        //get the actuals data from last to first in descending order
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        final ProductActualsView latestProductActualsView = productActualsViewRepository.findByProductVersionId_ProductId(productId, sort).get(0);
        final double totalActualSubscriptionCount=latestProductActualsView.getTotalNumberOfExistingSubscriptions();

        //Get latest value of interpolated forecast.
        final double totalInterpolatedForecastSubscriptionCountAsOfCurrentDate=forecastInterpolatedSubscriptionCountFinder.getDailyInterpolatedTotalSubscriptionCountAsOnDate(productId,SysDate.now());
        final double changeOfActualDemandAgainstForecast = (totalActualSubscriptionCount-totalInterpolatedForecastSubscriptionCountAsOfCurrentDate)/totalInterpolatedForecastSubscriptionCountAsOfCurrentDate;
        final double changeThresholdForPriceChange =productConfigurationViewRepository.findOne(productId).getTargetChangeThresholdForPriceChange();

        if(totalActualSubscriptionCount > totalInterpolatedForecastSubscriptionCountAsOfCurrentDate && Math.abs(changeOfActualDemandAgainstForecast) >changeThresholdForPriceChange){
            return ProductDemandTrend.UPWARD;
        }else if(totalActualSubscriptionCount < totalInterpolatedForecastSubscriptionCountAsOfCurrentDate && Math.abs(changeOfActualDemandAgainstForecast) >changeThresholdForPriceChange){
            return ProductDemandTrend.DOWNWARD;
        }else{
            return ProductDemandTrend.NOCHANGE;
        }
    }

    public ProductDemandTrend triggerProductPricing2(String productId){
        //get the actuals data from last to first in descending order
        LocalDate currentDay = SysDate.now();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        final ProductActualsView latestProductActualsView = productActualsViewRepository.findByProductVersionId_ProductId( productId,sort).get(0);
        final double totalActualSubscriptionCount=latestProductActualsView.getTotalNumberOfExistingSubscriptions();

        //find total subscription count for the current day(day of execution)
        //List<ProductPseudoActualsView> activePseudoActualsViews= productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc(productId, ProductForecastStatus.ACTIVE);
        ProductPseudoActualsView activePseudoActualsView=productPseudoActualsViewRepository.findByProductVersionId_ProductId(productId,sort).get(0);

        //find value of total subscriptions in PseudoActuals on date of execution.
        double  totalPseudoActualSubsctiptionCount=activePseudoActualsView.getTotalNumberOfExistingSubscriptions();

        final double changeOfActualDemandAgainstForecast = (totalActualSubscriptionCount-totalPseudoActualSubsctiptionCount)/totalPseudoActualSubsctiptionCount;
        final double changeThresholdForPriceChange =productConfigurationViewRepository.findOne(productId).getTargetChangeThresholdForPriceChange();

        if(totalActualSubscriptionCount > totalPseudoActualSubsctiptionCount && Math.abs(changeOfActualDemandAgainstForecast) >changeThresholdForPriceChange){
            return ProductDemandTrend.UPWARD;
        }else if(totalActualSubscriptionCount < totalPseudoActualSubsctiptionCount && Math.abs(changeOfActualDemandAgainstForecast) >changeThresholdForPriceChange){
            return ProductDemandTrend.DOWNWARD;
        }else{
            return ProductDemandTrend.NOCHANGE;
        }
    }

    //THIS METHOD MAY GET DEPRECATED -BUT FOR NOW KEEPING IT BECAUSE I AM NOT SURE IF ABOVE METHOD IS YET PROVEN
    //*****DO NOT REMOVE ANYTHING AS OF NOW*************************///
/*
    public ProductDemandTrend triggerProductPricing(String productId, double[] interpolatedForecastOnTotalSubscriptions) {
        boolean doTriggerPrice = false;
        final LocalDateTime currentDate = SysDateTime.now();
        final ProductVersionId productVersionId = new ProductVersionId(productId, currentDate);

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

        //Create similar array of interpolated total subscription count per day/half day from the weekly/monthly/quarterly build value
        double[] balancedInterpolatedForecastedTotalSubscriptionCount = new double[newTotalsubscriptionList.length];
        System.arraycopy(interpolatedForecastOnTotalSubscriptions, 0, balancedInterpolatedForecastedTotalSubscriptionCount, 0, newTotalsubscriptionList.length);
        double[][] observations = {balancedInterpolatedForecastedTotalSubscriptionCount, newTotalsubscriptionList};

        final boolean rejectNullHypothesis=executeAnnovaTest(observations) ;
        return determineDemandTrend(productId,rejectNullHypothesis,balancedInterpolatedForecastedTotalSubscriptionCount,newTotalsubscriptionList);
    }

    private double findMean(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }
    private boolean executeAnnovaTest(double[][] observations){
        final List<double[]> classes = new ArrayList<double[]>();
        for (int i = 0; i < observations.length; i++) {
            classes.add(observations[i]);
        }
        //Run one way Anova test to see the probability of actuals and interpolated build being overlapping(same)/non-overlapping(different)
        OneWayAnova anova = new OneWayAnova();
        double fStatistic = anova.anovaFValue(classes); // F-value
        double pValue = anova.anovaPValue(classes);     // P-value
        System.out.println("f-value:" + fStatistic);
        System.out.println("p-value:" + pValue);
        boolean rejectNullHypothesis = anova.anovaTest(classes, SIGNIFICANCE_LEVEL);
        System.out.println("reject null hipothesis " + (100 - SIGNIFICANCE_LEVEL * 100) + "% = " + rejectNullHypothesis);
        return rejectNullHypothesis;
    }

    private ProductDemandTrend determineDemandTrend(String productId,boolean rejectNullHypothesis,double[] interpolatedDailyDemand, double[] actualDailyDemand){
        //find the threshold percentage change between interpolated forecast and actuals that should trigger price
        final double changeThresholdForPriceChange =
                productConfigurationViewRepository.findOne(productId).getTargetChangeThresholdForPriceChange();

        //If null hypothesis is rejected it means that target is different than actual.. so need to trigger build
        if (rejectNullHypothesis) {
            double interpolatedForecastMean = findMean(interpolatedDailyDemand);
            double actualsMean = findMean(actualDailyDemand);
            double percentageMeanVariation = ((actualsMean - interpolatedForecastMean) / interpolatedForecastMean) * 100;
            //if difference is the mean values of actual subscription counts and interpolated+forecasted subscription counts is beyond the threshold value
            // then the build should be triggered for the product.
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
*/
}
