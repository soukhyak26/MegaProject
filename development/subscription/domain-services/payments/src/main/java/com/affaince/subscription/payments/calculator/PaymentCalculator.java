package com.affaince.subscription.payments.calculator;

import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;
import com.affaince.subscription.pojos.PaymentExpression;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

/**
 * Created by rahul on 17/7/17.
 */
public interface PaymentCalculator {

    void setNextCalculator(PaymentCalculator nextCalculator);

    List<InstalmentPaymentTracker> calculate(Map<LocalDate, Double> deliveryPrices, PaymentExpression paymentExpression);
}
