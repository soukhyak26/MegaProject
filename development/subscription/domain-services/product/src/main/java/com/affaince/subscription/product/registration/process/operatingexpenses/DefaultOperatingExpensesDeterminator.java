package com.affaince.subscription.product.registration.process.operatingexpenses;

import com.affaince.subscription.product.registration.command.domain.Product;

/**
 * Created by mandark on 02-01-2016.
 */
public class DefaultOperatingExpensesDeterminator implements OperatingExpensesDeterminator {

    @Override
    public double calculateOperatingExpensesPerProduct(Product product, OperatingExpensesDeterminator operatingExpensesDeterminator) {
        return 0;
    }
}
