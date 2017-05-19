package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.TotalReceivedCostAccountView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/18/2017.
 */
public interface TotalReceivedCostAccountViewRepository extends CrudRepository<TotalReceivedCostAccountView, String> {
    List<TotalReceivedCostAccountView> findBySubscriptionId(String subscriptionId);
}
