package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.SubscriptionActivatedCommand;
import com.affaince.subscription.business.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionActivatedCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public SubscriptionActivatedCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(SubscriptionActivatedCommand command) {
        //TODO: THIS command coming from Subscriber domain may be useless as at the time of subscription exact benefits amount may not be calculated.
        //TODO: THis command may be useful as it will add expected payment in the provisional revenue???
    }
}
