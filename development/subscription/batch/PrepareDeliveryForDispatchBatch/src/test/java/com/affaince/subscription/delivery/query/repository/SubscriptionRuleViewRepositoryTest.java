package com.affaince.subscription.delivery.query.repository;

import com.affaince.subscription.delivery.Application;
import com.affaince.subscription.delivery.query.view.SubscriptionRuleView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by rbsavaliya on 25-11-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class SubscriptionRuleViewRepositoryTest {

    @Autowired
    private SubscriptionRuleViewRepository subscriptionRuleViewRepository;

    @Before
    public void setup() {
        SubscriptionRuleView subscriptionRuleView = new SubscriptionRuleView("1", 3);
        subscriptionRuleViewRepository.save(subscriptionRuleView);
    }

    @Test
    public void testGetDiffBetweenDeliveryPreparationAndDispatchDate() {
        assertThat(subscriptionRuleViewRepository.findAll().iterator().next().getDiffBetweenDeliveryPreparationAndDispatchDate(),
                is(3));
    }
}
