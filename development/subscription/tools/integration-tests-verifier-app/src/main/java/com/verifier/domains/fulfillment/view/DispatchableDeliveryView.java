package com.verifier.domains.fulfillment.view;

import com.verifier.domains.fulfillment.vo.DispatchableDeliveryId;
import com.verifier.domains.fulfillment.vo.DispatchableDeliveryItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandar on 2/10/2018.
 */
@Document
public class DispatchableDeliveryView {
    private int deliveryStatus;
    @Id
    private DispatchableDeliveryId dispatchableDeliveryId;
    private List<DispatchableDeliveryItem> dispatchableDeliveryItems;

    public DispatchableDeliveryId getDispatchableDeliveryId() {
        return dispatchableDeliveryId;
    }

    public void setDispatchableDeliveryId(DispatchableDeliveryId dispatchableDeliveryId) {
        this.dispatchableDeliveryId = dispatchableDeliveryId;
    }

    public int getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<DispatchableDeliveryItem> getDispatchableDeliveryItems() {
        return dispatchableDeliveryItems;
    }

    public void setDispatchableDeliveryItems(List<DispatchableDeliveryItem> dispatchableDeliveryItems) {
        this.dispatchableDeliveryItems = dispatchableDeliveryItems;
    }
}
