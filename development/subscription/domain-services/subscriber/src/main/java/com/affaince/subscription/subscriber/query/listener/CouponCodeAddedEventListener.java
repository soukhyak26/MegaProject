package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.CouponCodeAddedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriberViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
@Component
public class CouponCodeAddedEventListener {

    private final SubscriberViewRepository subscriberViewRepository;

    @Autowired
    public CouponCodeAddedEventListener(SubscriberViewRepository subscriberViewRepository) {
        this.subscriberViewRepository = subscriberViewRepository;
    }

    @EventHandler
    public void on(CouponCodeAddedEvent event) {
        final SubscriberView subscriberView = subscriberViewRepository.findOne(event.getSubscriberId());
        List<String> couponCodes = subscriberView.getCouponCodes();
        if (couponCodes == null) {
            couponCodes = new ArrayList<>();
        }
        couponCodes.add(event.getCouponCode());
        subscriberViewRepository.save(subscriberView);
    }
}
