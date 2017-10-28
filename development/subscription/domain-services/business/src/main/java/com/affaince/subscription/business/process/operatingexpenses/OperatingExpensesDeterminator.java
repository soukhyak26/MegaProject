package com.affaince.subscription.business.process.operatingexpenses;

import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandark on 02-01-2016.
 */
public interface OperatingExpensesDeterminator {
    public Map<String, Double> calculateOperatingExpensesPerProduct(double commonExpenseAccount, LocalDate startDate, LocalDate endDate);

}
