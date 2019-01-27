package com.verifier.domains.payments.view;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.CompositeDeliveryId;
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
    private CompositeDeliveryId compositeDeliveryId;
    private List<DeliverableProductDetail> deliverableProductDetails;
    private LocalDate deliveryDate;
    private DeliveryStatus deliveryStatus;

    public DeliveryDetailsView() {
    }

    public DeliveryDetailsView(CompositeDeliveryId compositeDeliveryId, List<DeliverableProductDetail> deliverableProductDetails, LocalDate deliveryDate) {
        this.compositeDeliveryId = compositeDeliveryId;
        this.deliverableProductDetails = deliverableProductDetails;
        this.deliveryDate = deliveryDate;
        deliveryStatus= DeliveryStatus.CREATED;
    }

    public CompositeDeliveryId getCompositeDeliveryId() {
        return compositeDeliveryId;
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
