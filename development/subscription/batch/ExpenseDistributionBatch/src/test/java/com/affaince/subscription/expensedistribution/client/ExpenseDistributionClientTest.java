package com.affaince.subscription.expensedistribution.client;

import com.affaince.subscription.expensedistribution.Application;
import com.affaince.subscription.expensedistribution.query.view.DeliveryView;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;

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
    public void fetchAllDeliveriesTest () throws IOException {
        final List<DeliveryView> deliveryViews = expenseDistributionClient.fetchAllDeliveries();
        //assertThat (deliveryViews.size(), is (300));
    }
}
