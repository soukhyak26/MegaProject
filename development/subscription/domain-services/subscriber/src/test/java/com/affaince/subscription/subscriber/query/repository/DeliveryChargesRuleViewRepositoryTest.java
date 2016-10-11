package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.Application;
import com.affaince.subscription.subscriber.query.view.DeliveryChargesRuleView;
import com.affaince.subscription.subscriber.vo.RangeRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 25-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class DeliveryChargesRuleViewRepositoryTest {

    @Autowired
    private DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;

    @Before
    public void init () {
        deliveryChargesRuleViewRepository.deleteAll();
        final List<RangeRule> rangeRules = new ArrayList<>(40);
        for (int i = 0; i < 40; i++) {
            RangeRule rangeRule = new RangeRule();
            rangeRule.setRuleHeader("CHARGES_TILL_" + i + QuantityUnit.KG.toString());
            rangeRule.setRuleMinimum(i);
            rangeRule.setRuleMaximum(i+1);
            rangeRule.setApplicableValue(rangeRule.getRuleMaximum()*10-i);
            rangeRule.setRuleUnit(QuantityUnit.KG);
            rangeRules.add(rangeRule);
        }

        deliveryChargesRuleViewRepository.save(new DeliveryChargesRuleView(
                DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT, rangeRules, SysDate.now()));
    }

    @Test
    public void testTotalRangeRules () {
        final DeliveryChargesRuleView deliveryChargesRuleView = deliveryChargesRuleViewRepository.findOne(DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT);
        assertThat(deliveryChargesRuleView.getRangeRules().size(), is(40));
    }
}
