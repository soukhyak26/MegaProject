package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.SubscriptionReceivedPaymentView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public interface SubscriptionReceivedPaymentViewRepository extends CrudRepository<SubscriptionReceivedPaymentView, String> {
    public List<SubscriptionReceivedPaymentView> findAll();
}