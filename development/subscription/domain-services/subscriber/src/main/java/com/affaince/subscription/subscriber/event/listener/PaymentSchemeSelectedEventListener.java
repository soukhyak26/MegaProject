package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.subscriber.event.PaymentSchemeSelectedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/6/2017.
 */
@Component
public class PaymentSchemeSelectedEventListener {
    private final SubscriptionViewRepository subscriptionViewRepository;

    @Autowired
    public PaymentSchemeSelectedEventListener(SubscriptionViewRepository subscriptionViewRepository) {
        this.subscriptionViewRepository = subscriptionViewRepository;
    }

    public void on (PaymentSchemeSelectedEvent event){
        SubscriptionView subscriptionView=subscriptionViewRepository.findOne(event.getSubscriptionId());
        subscriptionView.setPaymentSchemeId(event.getPaymentSchemeId());
        subscriptionViewRepository.save(subscriptionView);
    }
}
