package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.event.SubscriptionSpecificOperatingExpenseCalculatedEvent;
import com.affaince.subscription.expensedistribution.vo.Delivery;
import com.affaince.subscription.expensedistribution.vo.DeliveryChargesRule;
import com.affaince.subscription.expensedistribution.vo.Product;

import java.util.List;
import java.util.Set;

/**
 * Created by rsavaliya on 13/2/16.
 */
public class SubscriptionSpecificOperatingExpenseDistributionProcessor {
    private List <Delivery> deliveryItems;
    private List <DeliveryChargesRule> deliveryChargesRules;
    private Set<Product> deliveredProducts;
    public void distribute () {
        createDeliveredProductListFromDelivery ();
        final double totalDeliveryChargesOfAllBaskets = calculateTotalDeliveryChargesOfAllBaskets();
        final double totalPurchasePriceOfAllProducts = calculateTotalPurchasePriceOfAllProducts();
        distributeToProduct (totalDeliveryChargesOfAllBaskets, totalPurchasePriceOfAllProducts);
    }

    private void distributeToProduct(double totalDeliveryChargesOfAllBaskets, double totalPurchasePriceOfAllProducts) {
        for (Product product: deliveredProducts) {
            double operatingExpensePerUnit = (totalDeliveryChargesOfAllBaskets*product.getPurchasePrice())
                    /totalPurchasePriceOfAllProducts;
            SubscriptionSpecificOperatingExpenseCalculatedEvent expenseCalculatedEvent =
                    new SubscriptionSpecificOperatingExpenseCalculatedEvent(product.getProductId(), operatingExpensePerUnit);
        }
    }


    private void createDeliveredProductListFromDelivery() {
        for (Delivery delivery: deliveryItems) {
            deliveredProducts.addAll(delivery.getProducts());
        }
    }

    private double calculateTotalPurchasePriceOfAllProducts() {
        double totalPurchasePrice = 0;
        for (Delivery delivery: deliveryItems) {
            for (Product product: delivery.getProducts()) {
                totalPurchasePrice = totalPurchasePrice + product.getPurchasePrice();
            }
        }
        return totalPurchasePrice;
    }

    private double calculateTotalDeliveryChargesOfAllBaskets() {
        double totalDeliveryCharges = 0;
        for (Delivery delivery: deliveryItems) {
            totalDeliveryCharges = totalDeliveryCharges + delivery.getBasketDeliveryCharges();
        }
        return totalDeliveryCharges;
    }
}
