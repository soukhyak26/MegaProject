package com.affaince.subscription.payments.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 5/28/2017.
 */
public class ModifiedDeliveryContent {
    private String deliveryId;
    private Map<String,Double> itemwiseModifiedOfferPrices;

    public ModifiedDeliveryContent(String deliveryId) {
        this.deliveryId = deliveryId;
        this.itemwiseModifiedOfferPrices=new HashMap<>();
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public Map<String, Double> getItemwiseModifiedOfferPrices() {
        return itemwiseModifiedOfferPrices;
    }
    public void addToItemwiseModifiedPrices(String deliveryItemId, double modifiedOfferPrice){
        this.itemwiseModifiedOfferPrices.put(deliveryItemId,modifiedOfferPrice);
    }
}
