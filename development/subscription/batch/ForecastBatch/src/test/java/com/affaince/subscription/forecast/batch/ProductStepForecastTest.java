package com.affaince.subscription.forecast.batch;

import com.affaince.subscription.forecast.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by mandar on 19-11-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Ignore
public class ProductStepForecastTest {

    @Autowired
    ProductForecastViewBuilder productForecastViewBuilder ;

    @Test
    public void testProductForecast() throws Exception{
        productForecastViewBuilder.buildProductStepForecast();
    }
}
