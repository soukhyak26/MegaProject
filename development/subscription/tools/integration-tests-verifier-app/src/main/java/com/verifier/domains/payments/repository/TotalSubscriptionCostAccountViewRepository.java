package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.TotalSubscriptionCostAccountView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/18/2017.
 */
public interface TotalSubscriptionCostAccountViewRepository extends CrudRepository<TotalSubscriptionCostAccountView, String> {
    List<TotalSubscriptionCostAccountView> findBySubscriptionId(String subscriptionId);
}
