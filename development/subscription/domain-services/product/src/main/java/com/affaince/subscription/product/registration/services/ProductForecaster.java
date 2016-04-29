package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.command.domain.ProductAccount;

/**
 * Created by mandark on 29-04-2016.
 */
public interface ProductForecaster {
    public ProductAccount forecast(ProductAccount actualAccount);
}
