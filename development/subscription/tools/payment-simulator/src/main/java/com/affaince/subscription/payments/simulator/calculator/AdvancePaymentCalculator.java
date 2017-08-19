package com.affaince.subscription.payments.simulator.calculator;

import com.affaince.subscription.common.type.PaymentEvent;
import com.affaince.subscription.common.type.PaymentSource;
import com.affaince.subscription.pojos.AdvancePaymentParameters;
import com.affaince.subscription.pojos.DeliveryExpression;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rahul on 17/7/17.
 */
@Component
public class AdvancePaymentCalculator implements PaymentCalculator {
    private PaymentCalculator nextCalculator;

    @Override
    public void setNextCalculator(PaymentCalculator nextCalculator) {
        this.nextCalculator = nextCalculator;
    }

    @Override
    public void calculate(PaymentInstallmentCalculationRequest request) {
        AdvancePaymentParameters advancePaymentParameters = request.getPaymentExpression().getAdvancePaymentParameters();
        InstalmentPaymentTracker tracker = null;
        final List<InstalmentPaymentTracker> paymentTrackers = new ArrayList<>();
        final Map<LocalDate, Double> deliveryPrices = request.getDeliveryPrices();
        if (advancePaymentParameters.getPaymentEvent().equals(PaymentEvent.SUBSCRIPTION_CONFIRMATION)) {
            tracker = new InstalmentPaymentTracker(1);
        }

        if (advancePaymentParameters.getPaymentSource().equals(PaymentSource.CURRENT_SUBSCRIPTION_AMOUNT)) {
            tracker.addToPaymentReceived(
                    deliveryPrices.values().stream().mapToDouble(Double::doubleValue).sum()
                            * (advancePaymentParameters.getPercentValue() / 100)
            );
        }

        if (advancePaymentParameters.getPaymentSource().equals(PaymentSource.DELIVERY)) {
            final DeliveryExpression deliveryExpression = advancePaymentParameters.getDeliveryExpression();
            int baseDeliveryCountForAdvancePayment = (deliveryExpression.getDividend() / deliveryExpression.getDivisor())
                    * deliveryPrices.size();
            double baseTotalAmount = 0;
            for (int i = 0; i < baseDeliveryCountForAdvancePayment; i++) {
                baseTotalAmount += deliveryPrices.get(i).doubleValue();
            }
            tracker.addToPaymentReceived(
                    baseTotalAmount * (advancePaymentParameters.getPercentValue() / 100)
            );
        }
        InstalmentPaymentTracker finalTracker = tracker;
        request.addAllPaymentInstallments(new ArrayList<InstalmentPaymentTracker>() {{
            add(finalTracker);
        }});
        if (this.nextCalculator != null) {
            this.nextCalculator.calculate(request);
        }
    }
}
