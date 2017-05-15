package com.affaince.subscription.product.configuration;

import com.affaince.subscription.product.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

/**
 * Created by mandar on 5/14/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CalculatorConfigurationTest {
    @Autowired
    private CalculatorConfiguration calculatorConfiguration;

    @Test
    public void testConfig(){
        List<CalculatorConfiguration.CalculatorChainConfig> calculatorChainConfigList=calculatorConfiguration.getCalculatorchain();
        for(CalculatorConfiguration.CalculatorChainConfig config: calculatorChainConfigList)
        System.out.println("$$$$$$****" + config.toString());
        assertThat(calculatorConfiguration.getCalculatorchain().size(),equalTo(7) );
    }
}
