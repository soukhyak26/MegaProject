package com.affaince.subscription.product.registration.command;

import com.affaince.subscription.product.registration.vo.OperatingExpenseVO;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class AddCommonOperatingExpenseCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final OperatingExpenseVO expense;

    public AddCommonOperatingExpenseCommand(String id, OperatingExpenseVO expense) {
        this.id = id;
        this.expense = expense;
    }

    public String getId() {
        return id;
    }

    public OperatingExpenseVO getExpense() {
        return expense;
    }
}
