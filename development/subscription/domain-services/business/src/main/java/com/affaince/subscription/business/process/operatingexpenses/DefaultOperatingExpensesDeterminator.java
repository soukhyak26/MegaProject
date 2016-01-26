package com.affaince.subscription.business.process.operatingexpenses;


import com.affaince.subscription.business.query.view.ProductView;

/**
 * Created by mandark on 02-01-2016.
 */
public class DefaultOperatingExpensesDeterminator implements OperatingExpensesDeterminator {

    @Override
    public double calculateOperatingExpensesPerProduct(ProductView product) {

        return 0;
    }
}
