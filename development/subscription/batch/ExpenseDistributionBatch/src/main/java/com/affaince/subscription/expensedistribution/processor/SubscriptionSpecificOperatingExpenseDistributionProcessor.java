package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.event.SubscriptionSpecificOperatingExpenseCalculatedEvent;
import com.affaince.subscription.expensedistribution.vo.InputDataToCalculatePerProductOpEx;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;

/**
 * Created by rsavaliya on 13/2/16.
 */
public class SubscriptionSpecificOperatingExpenseDistributionProcessor implements Processor{
    @Override
    public void process(Exchange exchange) throws Exception {
        InputDataToCalculatePerProductOpEx inputDataToCalculatePerProductOpEx
                = exchange.getIn().getBody(InputDataToCalculatePerProductOpEx.class);
        final double totalDeliveryChargesOfAllBaskets = calculateTotalDeliveryChargesOfAllBaskets(
                inputDataToCalculatePerProductOpEx.getTotalDeliveriesPerWeightCategory(),
                inputDataToCalculatePerProductOpEx.getDeliveryChargesPerWeightRange()
        );
        final double totalPurchasePriceOfAllProducts = calculateTotalPurchasePriceOfAllProducts(
                inputDataToCalculatePerProductOpEx.getTotalProductsSubscriptions(),
                inputDataToCalculatePerProductOpEx.getProductsPurchasePrice()
        );
        distributeToProduct (totalDeliveryChargesOfAllBaskets, totalPurchasePriceOfAllProducts,
                inputDataToCalculatePerProductOpEx.getProductsPurchasePrice());
    }

    private double calculateTotalPurchasePriceOfAllProducts(
            Map<String, Integer> totalProductsSubscriptions,
            Map<String, Double> productsPurchasePrice) {

        double totalPurchasePrice = 0;
        for (String productId: totalProductsSubscriptions.keySet()) {
            totalPurchasePrice = totalPurchasePrice +
                    totalProductsSubscriptions.get(productId)*productsPurchasePrice.get(productId);
        }
        return totalPurchasePrice;
    }

    private double calculateTotalDeliveryChargesOfAllBaskets(
            Map<Integer, Integer> totalDeliveriesPerWeightCategory,
            Map<Integer, Integer> deliveryChargesPerWeightRange) {

        double totalDeliveryCharges = 0;
        for (Integer weightRange : totalDeliveriesPerWeightCategory.keySet()) {
            totalDeliveryCharges = totalDeliveryCharges +
                    totalDeliveriesPerWeightCategory.get(weightRange)*deliveryChargesPerWeightRange.get(weightRange);
        }
        return totalDeliveryCharges;
    }

    private void distributeToProduct(double totalDeliveryChargesOfAllBaskets, double totalPurchasePriceOfAllProducts,
                                     Map <String, Double> productsPurchasePrice ) {
        for (String productId: productsPurchasePrice.keySet()) {
            double operatingExpensePerUnit = (totalDeliveryChargesOfAllBaskets*productsPurchasePrice.get(productId))
                    /totalPurchasePriceOfAllProducts;
            SubscriptionSpecificOperatingExpenseCalculatedEvent expenseCalculatedEvent =
                    new SubscriptionSpecificOperatingExpenseCalculatedEvent(productId, operatingExpensePerUnit);
        }
    }
}
