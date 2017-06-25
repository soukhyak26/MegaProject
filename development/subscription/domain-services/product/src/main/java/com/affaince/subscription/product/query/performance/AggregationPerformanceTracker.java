package com.affaince.subscription.product.query.performance;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.repository.*;
import com.affaince.subscription.product.query.view.*;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 02-01-2016.
 */
public class AggregationPerformanceTracker {

    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;
    @Autowired
    private FixedExpensePerProductViewRepository fixedExpensePerProductViewRepository;
    @Autowired
    private VariableExpensePerProductViewRepository variableExpensePerProductViewRepository;
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    PriceBucketTransactionViewRepository priceBucketTransactionViewRepository;

    @Autowired
    TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;

    public void calculateMonthlyMetrics(String productId, LocalDate executionDate) {
        YearMonth earlierMonthOfYear = new YearMonth(executionDate.getYear(), executionDate.getMonthOfYear()).minusMonths(1);
        LocalDate dateEarlierMonth = new LocalDate(earlierMonthOfYear.get(DateTimeFieldType.year()), earlierMonthOfYear.get(DateTimeFieldType.monthOfYear()), 1);
        LocalDate firstDayOfEarlierMonth = dateEarlierMonth.dayOfMonth().withMinimumValue();
        LocalDate lastDayOfEarlierMonth = dateEarlierMonth.dayOfMonth().withMaximumValue();
        LocalDate firstDayOfCurrentMonth = lastDayOfEarlierMonth.plusDays(1);
        List<ProductActualsView> monthlyRangeOfActualViews = productActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, lastDayOfEarlierMonth, executionDate);

        long[] subscriptionCounts = calculateMonthlySubscriptionCounts(monthlyRangeOfActualViews, lastDayOfEarlierMonth, executionDate);
        long totalNewSubscriptionsInAMonth = subscriptionCounts[0];
        long totalChurnedSubscriptionsInAMonth = subscriptionCounts[1];
        long totalMonthlySubscriptions = subscriptionCounts[2];

        long netNewSubscriptions = totalNewSubscriptionsInAMonth - totalChurnedSubscriptionsInAMonth;

        ProductActualMetricsView productActualMetricsView = new ProductActualMetricsView(new ProductVersionId(productId, firstDayOfCurrentMonth), executionDate);
        productActualMetricsView.setNewSubscriptions(totalNewSubscriptionsInAMonth);
        productActualMetricsView.setChurnedSubscriptions(totalChurnedSubscriptionsInAMonth);
        productActualMetricsView.setTotalNumberOfExistingSubscriptions(totalMonthlySubscriptions);
        productActualMetricsView.setNetNewSubscriptions(netNewSubscriptions);

        double totalMonthlyFixedExpense = calculateTotalMonthlyFixedExpenses(productId, firstDayOfCurrentMonth, executionDate, monthlyRangeOfActualViews);
        double totalMonthlyVariableExpense = calculateMonthlyVariableExpenses(productId, firstDayOfCurrentMonth, executionDate, monthlyRangeOfActualViews);
        productActualMetricsView.setFixedOperatingExpense(totalMonthlyFixedExpense);
        productActualMetricsView.setVariableOperatingExpense(totalMonthlyVariableExpense);
        productActualMetricsView.setTotalOperationalExpenses(totalMonthlyFixedExpense+totalMonthlyVariableExpense);

        double percentageSubscriptionsChurn = calculatePercentageSubscriptionsChurn(productId, lastDayOfEarlierMonth, totalChurnedSubscriptionsInAMonth);
        productActualMetricsView.setPercentageSubscriptionChurn(percentageSubscriptionsChurn);

        List<ProductActualMetricsView> earlierMonthProductActualMetricsViews=productActualMetricsViewRepository.findByProductVersionId(new ProductVersionId(productId,firstDayOfEarlierMonth));
        ProductActualMetricsView earlierMonthProductActualMetricsView=null;
        double lastMonthEndingMRR=0;
        if (null != earlierMonthProductActualMetricsViews && earlierMonthProductActualMetricsViews.size() > 0) {
            earlierMonthProductActualMetricsView=earlierMonthProductActualMetricsViews.get(0);
            lastMonthEndingMRR = earlierMonthProductActualMetricsView.getEndingMRR();
        }
        productActualMetricsView.setStartingMRR(lastMonthEndingMRR);

        double[] newAndChurnedMRR = calculateNewAndChunredMRR(productId, firstDayOfCurrentMonth, executionDate);
        productActualMetricsView.setTotalNewMRR(newAndChurnedMRR[0]);
        productActualMetricsView.setTotalChurnedMRR(newAndChurnedMRR[1]);

        double netNewMRR = newAndChurnedMRR[0] - newAndChurnedMRR[1];
        productActualMetricsView.setNetNewMRR(netNewMRR);

        double currentMonthEndingMRR = earlierMonthProductActualMetricsView.getEndingMRR() + netNewMRR;
        productActualMetricsView.setEndingMRR(currentMonthEndingMRR);
        productActualMetricsView.setArr(currentMonthEndingMRR * 12);
        if (lastMonthEndingMRR != 0) {
            productActualMetricsView.setPercentageMRRChurn(newAndChurnedMRR[1] / lastMonthEndingMRR);
        } else {
            productActualMetricsView.setPercentageMRRChurn(0);
        }

        double arpsNew = (newAndChurnedMRR[0] / totalNewSubscriptionsInAMonth) * 1000;
        double arps = (currentMonthEndingMRR / totalMonthlySubscriptions) * 1000;
        productActualMetricsView.setAverageRevenuePerNewSubscriber(arpsNew);
        productActualMetricsView.setAverageRevenuePerSubscriber(arps);
        productActualMetricsView.setRevenue(currentMonthEndingMRR);

        double costOfGoodsSold=calculateCostOfGoodsSold(productId, firstDayOfCurrentMonth, executionDate, monthlyRangeOfActualViews);
        productActualMetricsView.setPurchaseCost(costOfGoodsSold);
        productActualMetricsView.setGrossMargin(currentMonthEndingMRR-costOfGoodsSold);
        productActualMetricsView.setPercentageGrossMargin((currentMonthEndingMRR-costOfGoodsSold)/currentMonthEndingMRR);

        double totalOperatingProfitOrLoss=productActualMetricsView.getRevenue()*productActualMetricsView.getPercentageGrossMargin()-productActualMetricsView.getTotalOperationalExpenses();
        productActualMetricsView.setOperatingProfit(totalOperatingProfitOrLoss);
        productActualMetricsView.setPercentageOperatingProfit(totalOperatingProfitOrLoss/costOfGoodsSold);

        double ARPS=(productActualMetricsView.getEndingMRR()/productActualMetricsView.getTotalNumberOfExistingSubscriptions())*1000;
        double ARPSNew=(productActualMetricsView.getTotalNewMRR()/productActualMetricsView.getNewSubscriptions())*1000;

        productActualMetricsView.setAverageRevenuePerSubscriber(ARPS);
        productActualMetricsView.setAverageRevenuePerNewSubscriber(ARPSNew);

        double subscriptionLifetimeValue=productActualMetricsView.getAverageRevenuePerNewSubscriber()*productActualMetricsView.getPercentageGrossMargin()/productActualMetricsView.getPercentageMRRChurn();
        double subscriptionLifetimePeriod=1/productActualMetricsView.getPercentageSubscriptionChurn();
        productActualMetricsView.setSubscriptionLifetimeValue(subscriptionLifetimeValue);
        productActualMetricsView.setSubscriptionLifetimePeriod(subscriptionLifetimePeriod);
        productActualMetricsViewRepository.save(productActualMetricsView);

    }

    private long[] calculateMonthlySubscriptionCounts(List<ProductActualsView> monthlyRangeOfActualViews, LocalDate lastDayOfEarlierMonth, LocalDate executionDate) {
        long lastMonthTotalSubscriptions = 0;
        long totalMonthlySubscriptions = 0;

        long totalNewSubscriptionsInAMonth = 0;
        long totalChurnedSubscriptionsInAMonth = 0;
        for (ProductActualsView dailyActualsView : monthlyRangeOfActualViews) {
            if (dailyActualsView.getEndDate().equals(lastDayOfEarlierMonth)) {
                lastMonthTotalSubscriptions = dailyActualsView.getTotalNumberOfExistingSubscriptions();
            } else {
                if (dailyActualsView.getEndDate().equals(executionDate)) {
                    long currentTotalSubscriptions = dailyActualsView.getTotalNumberOfExistingSubscriptions();
                    totalMonthlySubscriptions = currentTotalSubscriptions - lastMonthTotalSubscriptions;
                }
                totalNewSubscriptionsInAMonth += dailyActualsView.getNewSubscriptions();
                totalChurnedSubscriptionsInAMonth += dailyActualsView.getChurnedSubscriptions();
            }
        }
        return new long[]{totalNewSubscriptionsInAMonth, totalChurnedSubscriptionsInAMonth, totalMonthlySubscriptions};
    }

    private double calculateTotalMonthlyFixedExpenses(String productId, LocalDate firstDayOfMonth, LocalDate executionDate, List<ProductActualsView> monthlyRangeOfActualViews) {
        //total fixed expenses
        List<FixedExpensePerProductView> fixedExpensePerProductVersions = fixedExpensePerProductViewRepository.findByProductwiseFixedExpenseId_ProductIdAndProductwiseFixedExpenseId_FromDateBetween(productId, firstDayOfMonth, executionDate);
        double totalMonthlyFixedExpenses = 0;
        for (FixedExpensePerProductView fixedExpenseVersion : fixedExpensePerProductVersions) {
            LocalDate endDateOfFixedExpense = fixedExpenseVersion.getEndDate();
            //find all actual subscriptions which are affiliated to a specific fixed expense  per unit
            List<ProductActualsView> productActualsViewSubset = findMonthlyActualsViewsInDatesBetween(monthlyRangeOfActualViews, firstDayOfMonth, endDateOfFixedExpense);
            //Find latest of the subset of actuals which are affiliated to a specific fixed expense
            ProductActualsView latestActualsView = findLatestProductActualsView(productActualsViewSubset);
            //sum of total subscriptions affiliated to each fixed expense
            totalMonthlyFixedExpenses += latestActualsView.getTotalNumberOfExistingSubscriptions() * fixedExpenseVersion.getFixedExpensePerProductPerUnit();
        }
        return totalMonthlyFixedExpenses;
    }

    private double calculateMonthlyVariableExpenses(String productId, LocalDate firstDayOfMonth, LocalDate executionDate, List<ProductActualsView> monthlyRangeOfActualViews) {
        //total variable expense
        List<VariableExpensePerProductView> variableExpensePerProductVersions = variableExpensePerProductViewRepository.findByProductwiseVariableExpenseId_ProductIdAndProductwiseVariableExpenseId_FromDateBetween(productId, firstDayOfMonth, executionDate);
        double totalMonthlyVariableExpenses = 0;
        for (VariableExpensePerProductView variableExpenseVersion : variableExpensePerProductVersions) {
            LocalDate endDateOfVariableExpense = variableExpenseVersion.getEndDate();
            List<ProductActualsView> productActualsViewSubset = findMonthlyActualsViewsInDatesBetween(monthlyRangeOfActualViews, firstDayOfMonth, endDateOfVariableExpense);
            ProductActualsView latestActualsView = findLatestProductActualsView(productActualsViewSubset);
            totalMonthlyVariableExpenses += latestActualsView.getTotalNumberOfExistingSubscriptions() * variableExpenseVersion.getVariableExpensePerProductPerUnit();
        }
        return totalMonthlyVariableExpenses;
    }

    private double calculateCostOfGoodsSold(String productId, LocalDate firstDayOfMonth, LocalDate executionDate, List<ProductActualsView> monthlyRangeOfActualViews) {
        //total variable expense
        List<TaggedPriceVersionsView> taggedPriceVersions = taggedPriceVersionsViewRepository.findByProductwiseTaggedPriceVersionId_ProductIdAndTaggedStartDateBetween(productId, firstDayOfMonth, executionDate);
        double monthlyCostOfGoodsSold = 0;
        for (TaggedPriceVersionsView taggedPriceVersion : taggedPriceVersions) {
            LocalDate endDateOfTaggedPriceVersion = taggedPriceVersion.getTaggedEndDate();
            List<ProductActualsView> productActualsViewSubset = findMonthlyActualsViewsInDatesBetween(monthlyRangeOfActualViews, firstDayOfMonth, endDateOfTaggedPriceVersion);
            ProductActualsView latestActualsView = findLatestProductActualsView(productActualsViewSubset);
            monthlyCostOfGoodsSold += latestActualsView.getTotalNumberOfExistingSubscriptions() * taggedPriceVersion.getPurchasePricePerUnit();
        }
        return monthlyCostOfGoodsSold;
    }

    private List<ProductActualsView> findMonthlyActualsViewsInDatesBetween(List<ProductActualsView> monthlyActualsViews, LocalDate startDate, LocalDate endDate) {
        List<ProductActualsView> monthlyActualsViewInDateRange = new ArrayList<>(30);
        for (ProductActualsView productActualsView : monthlyActualsViews) {
            LocalDate fromDate = productActualsView.getProductVersionId().getFromDate();
            LocalDate toDate = productActualsView.getEndDate();
            if ((fromDate.isEqual(startDate) || fromDate.isAfter(startDate)) && (toDate.isBefore(endDate) || toDate.isEqual(endDate))) {
                monthlyActualsViewInDateRange.add(productActualsView);
            }
        }
        return monthlyActualsViewInDateRange;
    }

    private ProductActualsView findLatestProductActualsView(List<ProductActualsView> productActualsViews) {
        ProductActualsView latestActualsView = productActualsViews.get(0);
        for (ProductActualsView actualsView : productActualsViews) {
            if (actualsView.getEndDate().isAfter(latestActualsView.getEndDate())) {
                latestActualsView = actualsView;
            }
        }
        return latestActualsView;
    }

    private double calculatePercentageSubscriptionsChurn(String productId, LocalDate lastDayOfEarlierMonth, long churnedSubscriptionsOfCurrentMonth) {
        LocalDate firstDayOfEarlierMonth = lastDayOfEarlierMonth.monthOfYear().withMinimumValue();
        List<ProductActualMetricsView> earlierMonthActualMetrics = productActualMetricsViewRepository.findByProductVersionId(new ProductVersionId(productId, firstDayOfEarlierMonth));
        double percentageSubscriptionsChurn = 0;
        if (null != earlierMonthActualMetrics && earlierMonthActualMetrics.size() > 0) {
            long totalSubscriptionsFromLastMonth = earlierMonthActualMetrics.get(0).getTotalNumberOfExistingSubscriptions();
            percentageSubscriptionsChurn = -(churnedSubscriptionsOfCurrentMonth / totalSubscriptionsFromLastMonth);

        }
        return percentageSubscriptionsChurn;
    }

    private double[] calculateNewAndChunredMRR(String productId, LocalDate firstDayOfCurrentMonth, LocalDate executionDate) {
        //get the list of price buckets in the date range of lastDayOfEarlierMonth and ExecutionDate
        List<PriceBucketView> priceBucketsActiveInCurrentMonth = priceBucketViewRepository.findByProductwisePriceBucketId_ProductIdAndToDateGreaterThan(productId, firstDayOfCurrentMonth);
        double newMRR = 0;
        double churnedMRR = 0;
        for (PriceBucketView activePriceBucket : priceBucketsActiveInCurrentMonth) {
            String priceBucketId = activePriceBucket.getProductwisePriceBucketId().getPriceBucketId();
            ProductPricingCategory productPricingCategory = activePriceBucket.getProductPricingCategory();
            List<PriceBucketTransactionView> priceBucketTransactionViews = priceBucketTransactionViewRepository.findByPriceBucketTransactionId_ProductIdAndPriceBucketTransactionId_PriceBucketIdAndPriceBucketTransactionId_TransactionDateBetween(productId, priceBucketId, firstDayOfCurrentMonth, executionDate);
            for (PriceBucketTransactionView singleTransactionView : priceBucketTransactionViews) {
                newMRR += singleTransactionView.getOfferedPrice() * singleTransactionView.getNumberOfNewSubscriptions();
                churnedMRR += singleTransactionView.getOfferedPrice() * singleTransactionView.getNumberOfChurnedSubscriptions();
            }
        }
        return new double[]{newMRR, churnedMRR};
    }



}
