package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.payments.vo.DeliveredProductDetail;
import com.affaince.subscription.payments.vo.SubscriptionwiseDeliveryId;
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
    private SubscriptionwiseDeliveryId subscriptionwiseDeliveryId;
    private List<DeliveredProductDetail> deliveredProductDetails;
    private LocalDate deliveryDate;

    public DeliveryDetailsView(SubscriptionwiseDeliveryId subscriptionwiseDeliveryId, List<DeliveredProductDetail> deliveredProductDetails, LocalDate deliveryDate) {
        this.subscriptionwiseDeliveryId = subscriptionwiseDeliveryId;
        this.deliveredProductDetails = deliveredProductDetails;
        this.deliveryDate = deliveryDate;
    }

    public SubscriptionwiseDeliveryId getSubscriptionwiseDeliveryId() {
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

}
