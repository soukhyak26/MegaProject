package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.DeleteDeliveryCommand;
import com.affaince.subscription.subscriber.domain.Subscriber;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 24-10-2016.
 */
@Component
public class DeleteDeliveryCommandHandler {

    private final Repository<Subscriber> repository;
    private final BenefitExecutionContext benefitExecutionContext;

    @Autowired
    public DeleteDeliveryCommandHandler(Repository<Subscriber> repository, BenefitExecutionContext benefitExecutionContext) {
        this.repository = repository;
        this.benefitExecutionContext = benefitExecutionContext;
    }

    @CommandHandler
    public void handle(DeleteDeliveryCommand command) {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        subscriber.deleteDelivery(command.getDeliveryId(), command.getSubscriberId(), benefitExecutionContext);
    }
}
