package com.affaince.subscription.consumerbasket.query.view;

import com.affaince.subscription.common.type.DeliveryStatus;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
@Document(collection = "BasketView")
public class BasketView {
    @Id
    private String basketId;
    private List<DeliveryItem> deliveryItems;
    private LocalDate deliveryDate;
    private LocalDate dispatchDate;
    private DeliveryStatus status;

    public BasketView(String basketId, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, LocalDate dispatchDate, DeliveryStatus status) {
        this.basketId = basketId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.dispatchDate = dispatchDate;
        this.status = status;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public List<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(List<DeliveryItem> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}