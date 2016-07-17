package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Map;

/**
 * Created by rbsavaliya on 17-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Ignore
public class DefaultOperatingExpenseDistributionDeterminatorTest {

    @Autowired
    private DefaultOperatingExpenseDistributionDeterminator defaultOperatingExpenseDistributionDeterminator;

    @Test
    public void testDistributeDeliveryExpensesToProductTest () throws IOException {
        Map<String, Double> perUnitOperationExpense =
                defaultOperatingExpenseDistributionDeterminator.distributeDeliveryExpensesToProduct();
        perUnitOperationExpense.forEach((productId, perUnitExpense) -> System.out.println(productId + " " + perUnitExpense));
    }
}
