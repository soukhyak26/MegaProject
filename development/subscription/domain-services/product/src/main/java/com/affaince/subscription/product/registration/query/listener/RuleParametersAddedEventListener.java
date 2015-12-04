package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.AddProductRuleParametersEvent;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.RuleParameters;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
@Component
public class RuleParametersAddedEventListener {

    private final ProductRepository itemRepository;

    @Autowired
    public RuleParametersAddedEventListener(ProductRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on(AddProductRuleParametersEvent event) {
        ProductView productView = itemRepository.findOneByItemId(event.getItemId());
        RuleParameters ruleParameters = new RuleParameters(
                event.getMinPermissibleDiscount(),
                event.getMaxPermissibleDiscount(),
                event.getMaxPermissibleUnits(),
                event.getMaxPermissibleSubscriptionPeriod()
        );
        productView.setRuleParameters(ruleParameters);
        itemRepository.save(productView);
    }
}
