package com.affaince.subscription.delivery.build;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.delivery.client.DeliveryClient;
import com.affaince.subscription.delivery.query.repository.DeliveryViewRepository;
import com.affaince.subscription.delivery.query.repository.SubscriptionRuleViewRepository;
import com.affaince.subscription.delivery.query.view.DeliveryView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by rbsavaliya on 25-11-2016.
 */
public class DeliveryRetriever {

    @Autowired
    private DeliveryViewRepository deliveryViewRepository;
    @Autowired
    private SubscriptionRuleViewRepository subscriptionRuleViewRepository;
    @Autowired
    private DeliveryClient deliveryClient;

    public void retrieveDeliveryWhichAreCreated() {
        final List<DeliveryView> deliveryViews = deliveryViewRepository.findByStatusAndDeliveryDateBetween(
                DeliveryStatus.CREATED, SysDate.now(), SysDate.now().plusDays(
                        subscriptionRuleViewRepository.findAll().iterator().next().getDiffBetweenDeliveryPreparationAndDispatchDate()
                )
        );
        deliveryViews.forEach(deliveryView -> deliveryClient.prepareDeliveryForDispatch(
                deliveryView.getSubscriberId()
                , deliveryView.getDeliveryId()
        ));
    }
}
