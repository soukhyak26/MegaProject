package com.affaince.subscription;

import org.axonframework.commandhandling.gateway.CommandGateway;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
public interface SubscriptionCommandGateway extends CommandGateway {

    void executeAsync(Object command) throws Exception;
}
