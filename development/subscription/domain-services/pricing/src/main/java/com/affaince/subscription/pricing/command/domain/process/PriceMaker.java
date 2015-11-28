package com.affaince.subscription.pricing.command.domain.process;

import com.affaince.subscription.pricing.command.domain.ProductAccount;

/**
 * Created by mandark on 28-11-2015.
 */
public interface PriceMaker {
    public double determineOfferedPrice(ProductAccount productAccount);
}
