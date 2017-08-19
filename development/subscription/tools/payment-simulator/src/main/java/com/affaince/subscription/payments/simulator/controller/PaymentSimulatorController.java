package com.affaince.subscription.payments.simulator.controller;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.compiler.paymentscheme.PaymentSchemeCompiler;
import com.affaince.subscription.payments.simulator.Delivery.Delivery;
import com.affaince.subscription.payments.simulator.Delivery.DeliveryCreator;
import com.affaince.subscription.payments.simulator.Delivery.Subscription;
import com.affaince.subscription.payments.simulator.Delivery.SubscriptionItem;
import com.affaince.subscription.payments.simulator.calculator.InstalmentPaymentTracker;
import com.affaince.subscription.payments.simulator.calculator.PaymentCalculatorChain;
import com.affaince.subscription.payments.simulator.request.PaymentSimulatorRequest;
import com.affaince.subscription.pojos.PaymentExpression;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "payment")
public class PaymentSimulatorController {

    @Autowired
    private PaymentCalculatorChain paymentCalculatorChain;

    @RequestMapping(method = RequestMethod.POST, value = "/simulate")
    public String simulatePaymentInstallments (@RequestBody PaymentSimulatorRequest request) throws JsonProcessingException {
        DeliveryCreator deliveryCreator = new DeliveryCreator(new Subscription(getSubscriptionItems(request)));
        Map<Integer, Delivery> deliveryMap = deliveryCreator.makeDeliveriesReady();
        Map <LocalDate, Double> deliveryDatePriceMap = new TreeMap<>();
        deliveryMap.forEach((integer, delivery) -> {
            delivery.calculateTotalDeliveryPrice ();
            deliveryDatePriceMap.put(delivery.getDeliveryDate(), delivery.getTotalDeliveryPrice());
        });
        PaymentExpression paymentExpression = getPaymentExpression(request, deliveryCreator);
        List<InstalmentPaymentTracker> paymentTrackers = paymentCalculatorChain.calculate(deliveryDatePriceMap, paymentExpression);
        return new ObjectMapper().writeValueAsString(paymentTrackers);
    }

    private PaymentExpression getPaymentExpression(@RequestBody PaymentSimulatorRequest request, DeliveryCreator deliveryCreator) {
        PaymentSchemeCompiler paymentSchemeCompiler = new PaymentSchemeCompiler();

        Map<Integer, Delivery> deliveryMap = deliveryCreator.makeDeliveriesReady();
        return paymentSchemeCompiler.compile
                (request.getPaymentScheme().getPaymentSchemeRule());
    }

    private List<SubscriptionItem> getSubscriptionItems(@RequestBody PaymentSimulatorRequest request) {
        List<SubscriptionItem> subscriptionItems = new ArrayList<>(request.getSubscription().getBasketItems().length);
        Arrays.asList(request.getSubscription().getBasketItems()).forEach(basketItem -> {
            SubscriptionItem subscriptionItem = new SubscriptionItem(
                    basketItem.getProductId(),
                    100,
                    basketItem.getCountPerPeriod(),
                    basketItem.getPeriod(),
                    basketItem.getOfferedPriceWithBasketLevelDiscount(),
                    basketItem.getDiscountedOfferedPrice(),
                    basketItem.getNoOfCycles(),
                    ProductPricingCategory.PRICE_COMMITMENT
            );
            subscriptionItems.add(subscriptionItem);
        });
        return subscriptionItems;
    }
}