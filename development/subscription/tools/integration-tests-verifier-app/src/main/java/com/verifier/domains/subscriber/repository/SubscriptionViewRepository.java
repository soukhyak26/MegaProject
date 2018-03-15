package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.verifier.domains.subscriber.view.SubscriptionView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public interface SubscriptionViewRepository extends CrudRepository<SubscriptionView, String> {
    List<SubscriptionView> findBySubscriberId(String subscriberId);
    List<SubscriptionView> findBySubscriberIdAndExpirationDateAfter(String subscriberId, LocalDate currentDate);
    SubscriptionView findBySubscriberIdAndConsumerBasketActivationStatus(String subscriberId, ConsumerBasketActivationStatus consumerBasketActivationStatus);
}
