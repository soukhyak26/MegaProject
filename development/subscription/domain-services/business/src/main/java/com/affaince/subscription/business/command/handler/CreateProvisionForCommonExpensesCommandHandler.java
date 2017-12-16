package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.CreateProvisionForCommonExpensesCommand;
import com.affaince.subscription.business.domain.BusinessAccount;
import com.affaince.subscription.business.process.operatingexpenses.DefaultOperatingExpensesDeterminator;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class CreateProvisionForCommonExpensesCommandHandler {

    private final Repository<BusinessAccount> repository;
    private final DefaultOperatingExpensesDeterminator defaultOperatingExpensesDeterminator;

    @Autowired
    public CreateProvisionForCommonExpensesCommandHandler(Repository<BusinessAccount> repository,DefaultOperatingExpensesDeterminator defaultOperatingExpensesDeterminator) {
        this.repository = repository;
        this.defaultOperatingExpensesDeterminator=defaultOperatingExpensesDeterminator;
    }

    @CommandHandler
    public void handle(CreateProvisionForCommonExpensesCommand command) {
        BusinessAccount businessAccount = repository.load(command.getId());
        LocalDate startDate=LocalDate.now();
        LocalDate endDate=startDate.year().withMaximumValue();
        businessAccount.registerProvisionForCommonExpenses(command.getId(),command.getStartDate(),command.getEndDate(),command.getExpenses(),defaultOperatingExpensesDeterminator);
    }
}
