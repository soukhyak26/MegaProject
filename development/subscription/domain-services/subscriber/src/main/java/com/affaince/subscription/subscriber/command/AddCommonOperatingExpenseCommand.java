package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.subscriber.vo.OperatingExpense;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class AddCommonOperatingExpenseCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final OperatingExpense expense;

    public AddCommonOperatingExpenseCommand(String id, OperatingExpense expense) {
        this.id = id;
        this.expense = expense;
    }

    public String getId() {
        return id;
    }

    public OperatingExpense getExpense() {
        return expense;
    }
}
