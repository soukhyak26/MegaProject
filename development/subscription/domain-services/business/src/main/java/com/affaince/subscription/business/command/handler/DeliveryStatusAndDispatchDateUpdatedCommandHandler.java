package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import com.affaince.subscription.date.SysDate;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 17/5/16.
 */
@Component
public class DeliveryStatusAndDispatchDateUpdatedCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public DeliveryStatusAndDispatchDateUpdatedCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DeliveryStatusAndDispatchDateUpdatedCommand command) {
        BusinessAccount businessAccount = repository.load(Integer.valueOf(SysDate.now().getYear()));
        businessAccount.adjustBasketAndDeliveryAmount(command.getTotalDeliveryPrice(), command.getDeliveryCharges());
    }
}
