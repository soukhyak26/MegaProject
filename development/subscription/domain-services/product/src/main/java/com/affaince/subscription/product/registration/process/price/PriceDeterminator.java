package com.affaince.subscription.product.registration.process.price;

import com.affaince.subscription.product.registration.command.domain.Product;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface PriceDeterminator {
    public void calculateOfferedPrice(Product product);
}
