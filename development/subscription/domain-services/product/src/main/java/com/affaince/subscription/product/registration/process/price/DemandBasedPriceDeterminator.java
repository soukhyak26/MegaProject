package com.affaince.subscription.product.registration.process.price;

import com.affaince.subscription.product.registration.command.domain.Product;

/**
 * Created by mandark on 13-02-2016.
 */
public class DemandBasedPriceDeterminator implements PriceDeterminator {
    public void calculateOfferedPrice(Product product) {
        //demand function: Q=Intercept -(1/price elasticity of Demand)*P
        //cost function= Cost=intercept+variable*quantity
        //Retrieve the price buckets whose purchase price is same.
/*
        List<PriceBucket> activePriceBuckets=product.getProductAccount().getActivePriceBuckets().entrySet();
*/


    }
}
