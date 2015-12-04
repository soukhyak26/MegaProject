package com.affaince.subscription.product.registration.processs;

import com.affaince.subscription.product.registration.command.domain.ProductAccount;

/**
 * Created by mandark on 28-11-2015.
 */
public interface PriceMaker {
    public double determineOfferedPrice(ProductAccount productAccount);
}
