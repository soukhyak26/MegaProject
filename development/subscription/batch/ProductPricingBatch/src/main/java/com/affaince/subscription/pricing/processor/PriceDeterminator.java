package com.affaince.subscription.pricing.processor;


import com.affaince.subscription.pricing.vo.Product;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface PriceDeterminator {
    public void calculateOfferedPrice(Product product);
}
