package com.affaince.subscription.business.process.operatingexpenses;

import com.affaince.subscription.business.command.domain.CommonOperatingExpense;

import java.util.Map;

/**
 * Created by mandark on 02-01-2016.
 */
public interface OperatingExpensesDeterminator {
    public Map<String, Double> calculateOperatingExpensesPerProduct(CommonOperatingExpense expense);

}
