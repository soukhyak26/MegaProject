package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.subscriber.vo.OperatingExpense;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class AddOperatingExpenseCommand {

    private String expenseId;
    private String expenseHeader;
    private double amount;
    private Period period;
    private ExpenseType expenseType;

    public AddOperatingExpenseCommand(String expenseId, List<OperatingExpense> expenses) {
    }
}
