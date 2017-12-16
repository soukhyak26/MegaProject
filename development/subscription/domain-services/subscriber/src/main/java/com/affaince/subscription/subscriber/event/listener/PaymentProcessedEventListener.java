package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.subscriber.event.PaymentProcessedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionReceivedPaymentViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionReceivedPaymentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class PaymentProcessedEventListener {

    private final SubscriptionReceivedPaymentViewRepository receivedPaymentViewRepository;

    @Autowired
    public PaymentProcessedEventListener(SubscriptionReceivedPaymentViewRepository receivedPaymentViewRepository) {
        this.receivedPaymentViewRepository = receivedPaymentViewRepository;
    }

    public void on(PaymentProcessedEvent event) {
        final SubscriptionReceivedPaymentView subscriptionReceivedPaymentView = new SubscriptionReceivedPaymentView(
                event.getSubscriptionId(), event.getSubscriberId(), event.getPaymentAmount(), event.getPaymentDate()
        );
        receivedPaymentViewRepository.save(subscriptionReceivedPaymentView);
    }
}
