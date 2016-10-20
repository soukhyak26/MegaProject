package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anayonkar on 21/8/16.
 */
public interface SubscriptionPaymentViewRepository extends CrudRepository<SubscriptionPaymentView, String> {
    SubscriptionPaymentView findBySubscriptionId(String subscriptionId);
}
