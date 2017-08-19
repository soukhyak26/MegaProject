package com.affaince.subscription.payments.simulator.calculator;

import com.affaince.subscription.pojos.PaymentExpression;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Created by rahul on 17/7/17.
 */
@Component
public class PaymentCalculatorChain {

    @Autowired
    private AdvancePaymentCalculator advancePaymentCalculator;
    @Autowired
    private ResidualDuePaymentCalculator residualDuePaymentCalculator;

    @PostConstruct
    public void init () {
        advancePaymentCalculator.setNextCalculator(residualDuePaymentCalculator);
    }

    public List<InstalmentPaymentTracker> calculate(Map<LocalDate, Double> deliveryWisePriceMap, PaymentExpression paymentExpression) {
        PaymentInstallmentCalculationRequest request = new PaymentInstallmentCalculationRequest();
        request.setDeliveryPrices(deliveryWisePriceMap);
        request.setPaymentExpression(paymentExpression);
        advancePaymentCalculator.calculate(request);
        return request.getPaymentInstallments();
    }
}
