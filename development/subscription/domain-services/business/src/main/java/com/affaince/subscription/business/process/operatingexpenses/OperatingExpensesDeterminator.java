package com.affaince.subscription.business.process.operatingexpenses;

import com.affaince.subscription.business.query.view.ProductView;

/**
 * Created by mandark on 02-01-2016.
 */
public interface OperatingExpensesDeterminator {
    public double calculateOperatingExpensesPerProduct(ProductView product);

}
