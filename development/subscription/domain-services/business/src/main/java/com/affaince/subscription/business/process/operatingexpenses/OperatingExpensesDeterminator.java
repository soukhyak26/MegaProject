package com.affaince.subscription.business.process.operatingexpenses;

import com.affaince.subscription.business.command.domain.CommonOperatingExpense;

/**
 * Created by mandark on 02-01-2016.
 */
public interface OperatingExpensesDeterminator {
    public void calculateOperatingExpensesPerProduct(CommonOperatingExpense expense);

}
