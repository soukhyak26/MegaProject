package com.affaince.subscription.subscriber.services.config;

import com.affaince.subscription.subscriber.query.repository.SubscriptionRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionRuleView;
import com.affaince.subscription.subscriber.vo.SubscriptionValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 10/2/2017.
 */
@Component
public class SubscriptionRuleService {
    private SubscriptionRuleViewRepository subscriptionRuleViewRepository;

    @Autowired
    public SubscriptionRuleService(SubscriptionRuleViewRepository subscriptionRuleViewRepository) {
        this.subscriptionRuleViewRepository = subscriptionRuleViewRepository;
    }

    public SubscriptionRuleView getSubscriptionConfig(){
        return subscriptionRuleViewRepository.findAll().iterator().next();
    }
}
