package com.affaince.subscription.payments.simulator.Delivery;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.date.SysDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryCreator {
    private Subscription subscription;

    public DeliveryCreator(Subscription subscription) {
        this.subscription = subscription;
    }

    public Map<Integer, Delivery> makeDeliveriesReady() {
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
                    weeklyDelivery.setDeliveryId(SysDate.now().plusWeeks(nextDeliveryWeek - weekOfYear) + "");
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
