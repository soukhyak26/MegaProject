package com.affaince.subscription.product.registration.process.operatingexpenses;

import com.affaince.subscription.product.registration.command.domain.Product;

/**
 * Created by mandark on 02-01-2016.
 */
public interface OperatingExpensesDeterminator {
    public double calculateCommonOperatingExpensesPerProduct(Product product);

    public double calculateSubscriptionSpecificOperatingExpensesPerProduct(Product product);
}
