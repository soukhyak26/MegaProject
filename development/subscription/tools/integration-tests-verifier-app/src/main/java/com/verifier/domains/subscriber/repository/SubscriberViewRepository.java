package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.vo.SubscriberName;
import com.verifier.domains.subscriber.view.SubscriberView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public interface SubscriberViewRepository extends CrudRepository<SubscriberView, String> {
    List<SubscriberView> findBySubscriberName(SubscriberName subscriberName);
}
