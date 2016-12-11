package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.date.SysDate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 04-09-2016.
 */
public class SubscriberTest {

    @Test
    public void testMakeDeliveries() {
        Subscription1 subscription = new Subscription1();
        SubscriptionItem subscriptionItem1 = new SubscriptionItem(
                "1", 100, 2, new Period(1, PeriodUnit.MONTH), 200, 180, 10
        );
        SubscriptionItem subscriptionItem2 = new SubscriptionItem(
                "2", 100, 2, new Period(2, PeriodUnit.WEEK), 200, 170, 10
        );
        SubscriptionItem subscriptionItem3 = new SubscriptionItem(
                "3", 100, 2, new Period(1, PeriodUnit.WEEK), 200, 170, 10
        );

        List<SubscriptionItem> subscriptionItems = new ArrayList<>();
        subscriptionItems.add(subscriptionItem1);
        subscriptionItems.add(subscriptionItem2);
        subscriptionItems.add(subscriptionItem3);
        subscription.setSubscriptionItems(subscriptionItems);

        Subscriber1 subscriber1 = new Subscriber1();
        Map<Integer, Delivery> deliveryMap = subscriber1.makeDeliveriesReady(subscription);
        int total = 0;
        for (Delivery delivery : deliveryMap.values()) {
            System.out.println(delivery.getDeliveryDate() + " " + delivery.getDeliveryItems().size());
            total += delivery.getDeliveryItems().size();
        }
        assertThat(total, is(60));
        assertThat(deliveryMap.values().size(), is(20));
    }
}

class Subscription1 {
    public List<SubscriptionItem> subscriptionItems;

    public List<SubscriptionItem> getSubscriptionItems() {
        return subscriptionItems;
    }

    public void setSubscriptionItems(List<SubscriptionItem> subscriptionItems) {
        this.subscriptionItems = subscriptionItems;
    }
}

class Subscriber1 {
    public Map<Integer, Delivery> makeDeliveriesReady(Subscription1 subscription) {
        final List<SubscriptionItem> subscriptionItems = subscription.getSubscriptionItems();
        final Map<Integer, Delivery> deliveries = new HashMap<>();
        int weekOfYear = SysDate.now().getWeekOfWeekyear();
        for (SubscriptionItem subscriptionItem : subscriptionItems) {
            int nextDeliveryWeek = weekOfYear;
            for (int i = 0; i < subscriptionItem.getNoOfCycles(); i++) {
                if (subscriptionItem.getPeriod().getUnit() == PeriodUnit.MONTH) {
                    nextDeliveryWeek = nextDeliveryWeek + subscriptionItem.getPeriod().getValue() * 4;
                } else {
                    nextDeliveryWeek = nextDeliveryWeek + subscriptionItem.getPeriod().getValue();
                }
                Delivery weeklyDelivery = deliveries.get(nextDeliveryWeek);
                if (weeklyDelivery == null) {
                    weeklyDelivery = new Delivery();
                    weeklyDelivery.setDeliveryId(nextDeliveryWeek + SysDate.now().getYear() + "");
                    weeklyDelivery.setDeliveryDate(SysDate.now().plusWeeks(nextDeliveryWeek - weekOfYear));
                    weeklyDelivery.setStatus(DeliveryStatus.CREATED);
                    deliveries.put(nextDeliveryWeek, weeklyDelivery);
                }
                for (int j = 0; j < subscriptionItem.getCountPerPeriod(); j++) {
                    DeliveryItem deliveryItem = new DeliveryItem();
                    deliveryItem.setDeliveryItemId(subscriptionItem.getProductId());
                    deliveryItem.setWeightInGrms(subscriptionItem.getWeightInGrms());
                    deliveryItem.setOfferedPricePerUnit(subscriptionItem.getOfferedPriceWithBasketLevelDiscount());
                    deliveryItem.setDeliveryStatus(DeliveryStatus.CREATED);
                    weeklyDelivery.getDeliveryItems().add(deliveryItem);
                }
            }
        }
        return deliveries;
    }
}