package com.affaince.subscription.forecast.batch;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 19-11-2016.
 */
@Ignore
public class ProductConfigurationTest {
    @Autowired
    ProductConfigurationViewBuilder productConfigurationViewBuilder;
    @Test
    public void configureProduct() throws Exception{
        productConfigurationViewBuilder.buildProductConfigurationView();
    }
}
