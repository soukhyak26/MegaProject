package com.verifier.domains.payments.view;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.verifier.domains.payments.vo.DeliverableProductDetail;
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
    private List<DeliverableProductDetail> deliverableProductDetails;
    private LocalDate deliveryDate;
    private DeliveryStatus deliveryStatus;

    public DeliveryDetailsView(DeliveryId subscriptionwiseDeliveryId, List<DeliverableProductDetail> deliverableProductDetails, LocalDate deliveryDate) {
        this.subscriptionwiseDeliveryId = subscriptionwiseDeliveryId;
        this.deliverableProductDetails = deliverableProductDetails;
        this.deliveryDate = deliveryDate;
        deliveryStatus= DeliveryStatus.CREATED;
    }

    public DeliveryId getSubscriptionwiseDeliveryId() {
        return subscriptionwiseDeliveryId;
    }

    public List<DeliverableProductDetail> getDeliverableProductDetails() {
        return deliverableProductDetails;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliverableProductDetails(List<DeliverableProductDetail> deliverableProductDetails) {
        this.deliverableProductDetails = deliverableProductDetails;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
