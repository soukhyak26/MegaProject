package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.SubscriptionRuleView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
public interface SubscriptionRuleViewRepository extends CrudRepository<SubscriptionRuleView, String> {
    public List<SubscriptionRuleView> findAll();
}
