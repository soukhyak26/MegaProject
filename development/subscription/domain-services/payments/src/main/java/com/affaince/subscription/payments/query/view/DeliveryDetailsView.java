package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.payments.vo.DeliveredProductDetail;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandar on 5/28/2017.
 */
@Document(collection="DeliveryDetailsView")
public class DeliveryDetailsView {
    @Id
    private DeliveryId subscriptionwiseDeliveryId;
    private List<DeliveredProductDetail> deliveredProductDetails;
    private LocalDate deliveryDate;
    private DeliveryStatus deliveryStatus;

    public DeliveryDetailsView(DeliveryId subscriptionwiseDeliveryId, List<DeliveredProductDetail> deliveredProductDetails, LocalDate deliveryDate) {
        this.subscriptionwiseDeliveryId = subscriptionwiseDeliveryId;
        this.deliveredProductDetails = deliveredProductDetails;
        this.deliveryDate = deliveryDate;
        deliveryStatus=DeliveryStatus.CREATED;
    }

    public DeliveryId getSubscriptionwiseDeliveryId() {
        return subscriptionwiseDeliveryId;
    }

    public List<DeliveredProductDetail> getDeliveredProductDetails() {
        return deliveredProductDetails;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveredProductDetails(List<DeliveredProductDetail> deliveredProductDetails) {
        this.deliveredProductDetails = deliveredProductDetails;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
