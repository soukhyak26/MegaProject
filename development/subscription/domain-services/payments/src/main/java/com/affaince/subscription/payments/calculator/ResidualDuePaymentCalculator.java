package com.affaince.subscription.payments.calculator;

import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;
import com.affaince.subscription.pojos.PaymentExpression;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rahul on 17/7/17.
 */
public class ResidualDuePaymentCalculator implements PaymentCalculator {

    private PaymentCalculator nextCalculator;

    @Override
    public void setNextCalculator(PaymentCalculator nextCalculator) {

        this.nextCalculator = nextCalculator;
    }

    @Override
    public List<InstalmentPaymentTracker> calculate(Map<LocalDate, Double> deliveryPrices, PaymentExpression paymentExpression) {

        List <InstalmentPaymentTracker> paymentTrackers = new ArrayList<>();
        return null;
    }
}
