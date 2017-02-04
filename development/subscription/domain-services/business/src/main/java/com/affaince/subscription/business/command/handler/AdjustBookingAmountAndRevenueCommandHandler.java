package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.AdjustBookingAmountAndRevenueCommand;
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
public class AdjustBookingAmountAndRevenueCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public AdjustBookingAmountAndRevenueCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AdjustBookingAmountAndRevenueCommand command) {
        BusinessAccount businessAccount = repository.load(Integer.valueOf(SysDate.now().getYear()));
        businessAccount.transferFromBookingAmountToRevenue(command.getSubscriptionId(),command.getTotalDeliveryPrice(), command.getDeliveryCharges());
    }
}
