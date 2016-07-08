package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.expensedistribution.client.ExpenseDistributionClient;
import com.affaince.subscription.expensedistribution.query.view.*;
import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by rsavaliya on 31/3/16.
 */
public class ForecastBasedOperatingExpenseDistributionDeterminator implements OperatingExpenseDistribution {

    @Autowired
    private ExpenseDistributionClient expenseDistributionClient;

    public ForecastBasedOperatingExpenseDistributionDeterminator() {
    }

    @Override
    public Map<String, Double> distributeDeliveryExpensesToProduct() {
        final Map<String, ProductWiseDeliveryStats> productWiseYearlyDeliveryStats = createYearlyProductWiseDeliveryStats();
        final List<DeliveryView> forecastDeliveries = createDeliveriesFromForecastData(productWiseYearlyDeliveryStats);
        double totalDeliveryExpenses = calculateTotalDeliveryCharges(forecastDeliveries);
        final Map<String, Double> productWisePerUnitDeliveryExpenseMap
                = calculateProductWisePerUnitDeliveryExpenses(totalDeliveryExpenses, productWiseYearlyDeliveryStats);
        return productWisePerUnitDeliveryExpenseMap;
    }

    private Map<String, Double> calculateProductWisePerUnitDeliveryExpenses(double totalDeliveryExpenses, Map<String, ProductWiseDeliveryStats> productWiseYearlyDeliveryStats) {
        double totalMRPOfAllSubscriptions = calculateTotalMRPOfAllSubscriptions(productWiseYearlyDeliveryStats);
        final Map<String, Double> productWisePerUnitDeliveryExpenseMap = new HashMap<>(productWiseYearlyDeliveryStats.size());
        for (ProductWiseDeliveryStats productWiseDeliveryStats : productWiseYearlyDeliveryStats.values()) {
            productWisePerUnitDeliveryExpenseMap.put(productWiseDeliveryStats.getProductId(),
                    //(totalMRPOfProduct*totalDeliveryCost/totalMRPOfAllSubscription)/number of units sold
                    ((productWiseDeliveryStats.getTotalMRP() * totalDeliveryExpenses) / totalMRPOfAllSubscriptions)
                            / productWiseDeliveryStats.getTotalUnitsSold());
        }
        return productWisePerUnitDeliveryExpenseMap;
    }

    private double calculateTotalMRPOfAllSubscriptions(Map<String, ProductWiseDeliveryStats> productWiseYearlyDeliveryStats) {
        double totalMRPOfAllSubscriptions = 0;
        for (ProductWiseDeliveryStats productWiseDeliveryStats : productWiseYearlyDeliveryStats.values()) {
            totalMRPOfAllSubscriptions += productWiseDeliveryStats.getTotalMRP();
        }
        return totalMRPOfAllSubscriptions;
    }

    private double calculateTotalDeliveryCharges(List<DeliveryView> forecastDeliveries) {
        double totalDeliveryExpenses = 0;
        final Iterable<DeliveryChargesRuleView> deliveryChargesRuleViews = expenseDistributionClient.fetchAllDeliveryChargesRules();
        final List<RangeRule> rangeRules = deliveryChargesRuleViews.iterator().next().getRangeRules();
        for (DeliveryView deliveryView : forecastDeliveries) {
            double totalDeliveryWeight = calculateTotalWeightOfDelivery(deliveryView.getDeliveryItems());
            double deliveryCharges = calculateDeliveryCharges(totalDeliveryWeight, rangeRules);
            totalDeliveryExpenses += deliveryCharges;
        }
        return totalDeliveryExpenses;
    }

    private double calculateDeliveryCharges(double totalDeliveryWeight, List<RangeRule> rangeRules) {
        for (RangeRule rangeRule : rangeRules) {
            if (totalDeliveryWeight <= rangeRule.getRuleMaximum() && totalDeliveryWeight >= rangeRule.getRuleMinimum()) {
                return rangeRule.getApplicableValue();
            }
        }
        return 0;
    }

    private double calculateTotalWeightOfDelivery(List<DeliveryItem> deliveryItems) {
        return deliveryItems.stream().mapToDouble(DeliveryItem::getWeightInGrms).sum();
    }

    private List<DeliveryView> createDeliveriesFromForecastData(Map<String, ProductWiseDeliveryStats> productWiseYearlyDeliveryStats) {
        final Iterable<ProductView> productViews = expenseDistributionClient.fetchAllProducts();
        final List<DeliveryView> deliveryViews = new ArrayList<>();
        for (ProductView productView : productViews) {
            long totalYearlySubscriptions = productWiseYearlyDeliveryStats.get(productView.getProductId()).getTotalUnitsSold();
            int yearlyTargetConsumption = ((Double) (productView.getTargetMonthlyConsumption() * 12)).intValue();
            long totalDeliveries = yearlyTargetConsumption * totalYearlySubscriptions;
            List<String> substitutes = productView.getSubstitutes();
            long productQuantityInKG = productView.getQuantity();
            if (productView.getQuantityUnit() == QuantityUnit.ml || productView.getQuantityUnit() == QuantityUnit.GM) {
                productQuantityInKG = productQuantityInKG / 1000;
            }
            productView.setQuantity(productQuantityInKG);
            productView.setQuantityUnit(QuantityUnit.KG);
            for (DeliveryView deliveryView : deliveryViews) {
                boolean isDeliveryItemAdded = addDeliveryItemToDeliveryView(productView, deliveryView, substitutes);
                if (isDeliveryItemAdded) {
                    totalDeliveries -= 1;
                }
                if (totalDeliveries == 0) {
                    break;
                }
            }
            for (int i = 0; i < totalDeliveries; i++) {
                deliveryViews.add(createDeliveryView(productView));
            }
        }
        return deliveryViews;
    }

    private DeliveryView createDeliveryView(ProductView productView) {
        final DeliveryView deliveryView = new DeliveryView();
        DeliveryItem deliveryItem = new DeliveryItem();
        deliveryItem.setDeliveryItemId(productView.getProductId());
        deliveryItem.setWeightInGrms(productView.getQuantity());
        List<DeliveryItem> deliveryItems = new ArrayList<>();
        deliveryView.setDeliveryItems(deliveryItems);
        return deliveryView;
    }

    private boolean addDeliveryItemToDeliveryView(ProductView productView, DeliveryView deliveryView, List<String> substitutes) {
        final List<String> deliveriesItemsIds = deliveryView.getDeliveryItems().stream()
                .map(deliveryItem -> deliveryItem.getDeliveryItemId()).collect(Collectors.toList());
        for (String productId : substitutes) {
            if (deliveriesItemsIds.contains(productId)) {
                return false;
            }
        }
        DeliveryItem deliveryItem = new DeliveryItem();
        deliveryItem.setDeliveryItemId(productView.getProductId());
        deliveryItem.setWeightInGrms(productView.getQuantity());
        deliveryView.getDeliveryItems().add(deliveryItem);
        return true;
    }

    private Map<String, ProductWiseDeliveryStats> createYearlyProductWiseDeliveryStats() {
        final Map<String, ProductWiseDeliveryStats> productWiseYearlyDeliveryStats = new HashMap<>();
        for (ForecastPriceBucketsView forecastPriceBucketsView : expenseDistributionClient.fetchAllForecastedPriceBuckets()) {
            final String productId = forecastPriceBucketsView.getProductId();
            ProductWiseDeliveryStats productWiseDeliveryStats = productWiseYearlyDeliveryStats.get(productId);
            if (productWiseDeliveryStats == null) {
                productWiseDeliveryStats = new ProductWiseDeliveryStats(productId);
                productWiseYearlyDeliveryStats.put(productId, productWiseDeliveryStats);
            }
            productWiseDeliveryStats.addMRP(forecastPriceBucketsView.getMRP());
            productWiseDeliveryStats.addUnitSold(forecastPriceBucketsView.getNumberOfExistingCustomersAssociatedWithAPrice());
        }
        return productWiseYearlyDeliveryStats;
    }
}
