package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.TotalReceivableCostAccountView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/18/2017.
 */
public interface TotalReceivableCostAccountViewRepository extends CrudRepository<TotalReceivableCostAccountView, String> {
    List<TotalReceivableCostAccountView> findBySubscriptionId(String subscriptionId);
}
