package com.affaince.subscription.business.process.operatingexpenses;


import com.affaince.subscription.business.command.domain.CommonOperatingExpense;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductView;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by mandark on 02-01-2016.
 */
public class DefaultOperatingExpensesDeterminator implements OperatingExpensesDeterminator {
    @Autowired
    ProductViewRepository productViewrepository;

    @Override
    public void calculateOperatingExpensesPerProduct(CommonOperatingExpense expense) {
        Iterable<ProductView> allProducts = productViewrepository.findAll();
        Map<YearMonth, Double> rollingExpenseForecast = expense.getRollingExpenseForecast();
        double currentOperatingExpense = rollingExpenseForecast.get(YearMonth.now());
        double totalMonthlySaleAmount = 0;
        for (ProductView tempProductView : allProducts) {
            totalMonthlySaleAmount = tempProductView.getTotalMonthlySaleAmount();
        }
        double contributorFactor = currentOperatingExpense / totalMonthlySaleAmount;
        for (ProductView productView : allProducts) {
            productView.setCurrentOperatingExpensePerUnit(productView.getSensitiveTo().entrySet().iterator().next().getValue() * contributorFactor * productView.getMRP());
        }
    }
}