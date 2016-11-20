package com.affaince.subscription.forecast.batch;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 19-11-2016.
 */
@Ignore
public class ProductRegistrationTest {

    @Autowired
    ProductViewBuilder productViewBuilder;

    @Test
    public void testProductRegsitration() throws Exception{
        productViewBuilder.buildProductView();
    }
}
