package com.affaince.subscription.payments.calculator;

import com.affaince.subscription.common.type.PaymentEvent;
import com.affaince.subscription.common.type.PaymentSource;
import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;
import com.affaince.subscription.pojos.AdvancePaymentParameters;
import com.affaince.subscription.pojos.DeliveryExpression;
import com.affaince.subscription.pojos.PaymentExpression;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rahul on 17/7/17.
 */
public class AdvancePaymentCalculator implements PaymentCalculator {
    private PaymentCalculator nextCalculator;

    @Override
    public void setNextCalculator(PaymentCalculator nextCalculator) {
        this.nextCalculator = nextCalculator;
    }

    @Override
    public List<InstalmentPaymentTracker> calculate(Map<LocalDate, Double> deliveryPrices, PaymentExpression paymentExpression) {
        AdvancePaymentParameters advancePaymentParameters = paymentExpression.getAdvancePaymentParameters();
        InstalmentPaymentTracker tracker = null;
        final List <InstalmentPaymentTracker> paymentTrackers = new ArrayList<>();
        if (advancePaymentParameters.getPaymentEvent().equals(PaymentEvent.SUBSCRIPTION_CONFIRMATION)) {
            tracker = new InstalmentPaymentTracker(1);
        }

        if(advancePaymentParameters.getPaymentSource().equals(PaymentSource.CURRENT_SUBSCRIPTION_AMOUNT)) {
            tracker.addToPaymentReceived(
                    deliveryPrices.values().stream().mapToDouble(Double::doubleValue).sum()
                    * (advancePaymentParameters.getPercentValue()/100)
            );
        }

        if(advancePaymentParameters.getPaymentSource().equals(PaymentSource.DELIVERY)) {
            final DeliveryExpression deliveryExpression = advancePaymentParameters.getDeliveryExpression();
            int baseDeliveryCountForAdvancePayment = (deliveryExpression.getDividend() / deliveryExpression.getDivisor())
                    * deliveryPrices.size();
            double baseTotalAmount = 0;
            for (int i=0;i<baseDeliveryCountForAdvancePayment;i++) {
                baseTotalAmount += deliveryPrices.get(i).doubleValue();
            }
            tracker.addToPaymentReceived(
                    baseTotalAmount * (advancePaymentParameters.getPercentValue()/100)
            );
        }
        paymentTrackers.add(tracker);
        if (this.nextCalculator != null) {
            paymentTrackers.addAll(nextCalculator.calculate(deliveryPrices, paymentExpression));
        }
        return paymentTrackers;
    }
}
