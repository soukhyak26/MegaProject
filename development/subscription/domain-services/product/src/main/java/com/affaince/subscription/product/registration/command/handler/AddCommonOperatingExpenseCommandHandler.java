package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.AddCommonOperatingExpenseCommand;
import com.affaince.subscription.product.registration.command.domain.CommonOperatingExpense;
import com.affaince.subscription.product.registration.vo.OperatingExpense;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class AddCommonOperatingExpenseCommandHandler {

    private final Repository<CommonOperatingExpense> repository;

    @Autowired
    public AddCommonOperatingExpenseCommandHandler(Repository<CommonOperatingExpense> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddCommonOperatingExpenseCommand command) {
        final OperatingExpense operatingExpense = command.getExpense();
        final CommonOperatingExpense commonOperatingExpense = new CommonOperatingExpense(command.getId(),
                operatingExpense.getExpenseHeader(), operatingExpense.getAmount(), operatingExpense.getPeriod());
        repository.add(commonOperatingExpense);
    }
}
