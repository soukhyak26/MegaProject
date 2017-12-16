package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.subscriber.event.RewardPointsAddedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriberViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
@Component
public class RewardPointsAddedEventListener {

    private final SubscriberViewRepository subscriberViewRepository;

    @Autowired
    public RewardPointsAddedEventListener(SubscriberViewRepository subscriberViewRepository) {
        this.subscriberViewRepository = subscriberViewRepository;
    }

    @EventHandler
    public void on(RewardPointsAddedEvent event) {
        final SubscriberView subscriberView = subscriberViewRepository.findOne(event.getSubscriberId());
        subscriberView.setRewardPoints(subscriberView.getRewardPoints() + event.getRewardPoints());
        subscriberViewRepository.save(subscriberView);
    }
}
