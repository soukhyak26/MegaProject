package com.affaince.subscription.pricing.processor;


import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.vo.Product;

import java.util.List;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface PriceDeterminator {
    public double calculateOfferedPrice(Product product);

}
