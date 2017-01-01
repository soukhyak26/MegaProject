package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProvisionCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 31-12-2016.
 */
@Component
public class ProvisionCreatedEventListener {

    @EventHandler
    public void on(ProvisionCreatedEvent event) {

    }
}