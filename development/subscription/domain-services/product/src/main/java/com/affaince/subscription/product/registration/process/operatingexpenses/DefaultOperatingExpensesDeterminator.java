package com.affaince.subscription.product.registration.process.operatingexpenses;

import com.affaince.subscription.product.registration.command.domain.Product;

/**
 * Created by mandark on 02-01-2016.
 */
public class DefaultOperatingExpensesDeterminator implements OperatingExpensesDeterminator {
    public double calculateCommonOperatingExpensesPerProduct(Product product) {
        return 0.0;
    }

    public double calculateSubscriptionSpecificOperatingExpensesPerProduct(Product product) {
        return 0.0;
    }

}
