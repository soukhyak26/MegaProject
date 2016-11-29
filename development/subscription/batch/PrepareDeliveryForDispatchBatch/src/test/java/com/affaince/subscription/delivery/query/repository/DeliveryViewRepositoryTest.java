package com.affaince.subscription.delivery.query.repository;

import com.affaince.subscription.delivery.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rbsavaliya on 25-11-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class DeliveryViewRepositoryTest {

    @Autowired
    private DeliveryViewRepository deliveryViewRepository;

    @Test
    public void testCustomDeliveryViewWithLessParameter() {
        System.out.println(deliveryViewRepository.findAll().iterator().next().getDeliveryDate());
    }
}
