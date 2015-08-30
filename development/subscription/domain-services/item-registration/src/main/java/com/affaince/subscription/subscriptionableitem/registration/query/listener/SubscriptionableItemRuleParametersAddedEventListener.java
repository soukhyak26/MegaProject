package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.command.event.AddSubscriptionableItemRuleParametersEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.RuleParameters;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
@Component
public class SubscriptionableItemRuleParametersAddedEventListener {

    private final SubscriptionableItemRepository itemRepository;

    @Autowired
    public SubscriptionableItemRuleParametersAddedEventListener(SubscriptionableItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on(AddSubscriptionableItemRuleParametersEvent event) {
        SubscriptionableItemView subscriptionableItemView = itemRepository.findOneByItemId(event.getItemId());
        RuleParameters ruleParameters = new RuleParameters(
                event.getMinPermissibleDiscount(),
                event.getMaxPermissibleDiscount(),
                event.getMaxPermissibleUnits(),
                event.getMaxPermissibleSubscriptionPeriod()
        );
        subscriptionableItemView.setRuleParameters(ruleParameters);
        itemRepository.save(subscriptionableItemView);
    }
}
