package com.affaince.subscription.expensedistribution.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rsavaliya on 14/2/16.
 */
public class InputDataToCalculatePerProductOpEx {
    private Map <String, Integer> totalProductsSubscriptions = new HashMap<>();
    private Map <String, Double> productsPurchasePrice;
    private Map <Integer, Integer> totalDeliveriesPerWeightCategory;
    private Map <Integer, Integer> deliveryChargesPerWeightRange;

    public void addProductSubscription (String productId, int totalNumOfSubscriptions) {
        totalProductsSubscriptions.put(productId, totalNumOfSubscriptions);
    }

    public void addNumberOfDeliveriesForWeightCategory (int weightCategory, int totalDeliveries) {
        totalDeliveriesPerWeightCategory.put(weightCategory, totalDeliveries);
    }

    public void addProductPurchasePrice (String productId, double purchasePrice) {
        productsPurchasePrice.put(productId, purchasePrice);
    }

    public Map<String, Integer> getTotalProductsSubscriptions() {
        return totalProductsSubscriptions;
    }

    public void setTotalProductsSubscriptions(Map<String, Integer> totalProductsSubscriptions) {
        this.totalProductsSubscriptions = totalProductsSubscriptions;
    }

    public Map<Integer, Integer> getTotalDeliveriesPerWeightCategory() {
        return totalDeliveriesPerWeightCategory;
    }

    public void setTotalDeliveriesPerWeightCategory(Map<Integer, Integer> totalDeliveriesPerWeightCategory) {
        this.totalDeliveriesPerWeightCategory = totalDeliveriesPerWeightCategory;
    }

    public Map<String, Double> getProductsPurchasePrice() {
        return productsPurchasePrice;
    }

    public void setProductsPurchasePrice(Map<String, Double> productsPurchasePrice) {
        this.productsPurchasePrice = productsPurchasePrice;
    }

    public Map<Integer, Integer> getDeliveryChargesPerWeightRange() {
        return deliveryChargesPerWeightRange;
    }

    public void setDeliveryChargesPerWeightRange(Map<Integer, Integer> deliveryChargesPerWeightRange) {
        this.deliveryChargesPerWeightRange = deliveryChargesPerWeightRange;
    }
}
