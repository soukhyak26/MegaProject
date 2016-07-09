package com.affaince.subscription.expensedistribution.client;

import com.affaince.subscription.expensedistribution.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by rbsavaliya on 09-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Ignore
public class ExpenseDistributionClientTest {

    @Autowired
    private ExpenseDistributionClient expenseDistributionClient;

    @Test
    public void fetchAllDeliveriesTest () {
        expenseDistributionClient.fetchAllDeliveries();
    }
}
