package com.affaince.subscription.payments.calculator;

import com.affaince.subscription.common.type.TotalDeliveryBase;
import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;
import com.affaince.subscription.pojos.DeliveryExpression;
import com.affaince.subscription.pojos.PaymentExpression;
import com.affaince.subscription.pojos.ResidualDuePaymentParameters;
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
    public void calculate(PaymentInstallmentCalculationRequest request) {
        final double remainingPayment = request.getDeliveryPrices().values().stream().
                mapToDouble(Double::doubleValue).sum()
                -
                request.getPaymentInstallments().get(1).getPaymentExpected();
        int size = request.getDeliveryPrices().size();
        final int remainingDelivery = size -
                request.getPaymentInstallments().get(1).getDeliverySequence();
        final List<InstalmentPaymentTracker> paymentTrackers = new ArrayList<>();

        final ResidualDuePaymentParameters residualDuePaymentParameters = request.getPaymentExpression().
                getResidualDuePaymentParameters();

        final List <DeliveryExpression> deliveryExpressions = residualDuePaymentParameters.getDeliveries();
        final List <Integer> proportionValues = residualDuePaymentParameters.getProportionValues();

        for (int i=0; i<proportionValues.size();i++) {
            DeliveryExpression deliveryExpression = deliveryExpressions.get(0);
            int deliveryCount = (deliveryExpression.getDividend() / deliveryExpression.getDivisor())
                * (deliveryExpression.getTotalDeliveryBase().equals(TotalDeliveryBase.N)?size:remainingDelivery);
            InstalmentPaymentTracker tracker = new InstalmentPaymentTracker(
                    residualDuePaymentParameters.isBefore()?deliveryCount:deliveryCount+1
            );
            tracker.setPaymentExpected((remainingPayment*proportionValues.get(0))/10);
            paymentTrackers.add(tracker);
        }
        request.addAllPaymentInstallments(paymentTrackers);
    }
}
