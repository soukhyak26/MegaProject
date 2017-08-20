package com.affaince.subscription.benefit.simulator.controller;

import com.affaince.subscription.benefit.simulator.Delivery.Delivery;
import com.affaince.subscription.benefit.simulator.Delivery.DeliveryCreator;
import com.affaince.subscription.benefit.simulator.Delivery.Subscription;
import com.affaince.subscription.benefit.simulator.Delivery.SubscriptionItem;
import com.affaince.subscription.benefit.simulator.benefit.context.BenefitCalculationRequest;
import com.affaince.subscription.benefit.simulator.benefit.context.BenefitExecutionContext;
import com.affaince.subscription.benefit.simulator.benefit.context.BenefitResult;
import com.affaince.subscription.benefit.simulator.request.BenefitSimulatorRequest;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "benefit")
public class BenefitSimulatorController {

    @Autowired
    private BenefitExecutionContext benefitExecutionContext;

    @RequestMapping(method = RequestMethod.POST, value = "/simulate")
    public String simulatePaymentInstallments (@RequestBody BenefitSimulatorRequest request) throws JsonProcessingException {
        DeliveryCreator deliveryCreator = new DeliveryCreator(new Subscription(getSubscriptionItems(request)));
        NavigableMap<Integer, Delivery> deliveryMap = deliveryCreator.makeDeliveriesReady();
        Map <LocalDate, Double> deliveryDatePriceMap = new TreeMap<>();
        deliveryMap.forEach((integer, delivery) -> {
            delivery.calculateTotalDeliveryPrice ();
            deliveryDatePriceMap.put(delivery.getDeliveryDate(), delivery.getTotalDeliveryPrice());
        });

        final BenefitResult benefitResult =
                calculateBenefits(deliveryMap, request);

        return new ObjectMapper().writeValueAsString(benefitResult);
    }

    private BenefitResult calculateBenefits(NavigableMap<Integer, Delivery> deliveries,
                                            BenefitSimulatorRequest request) {
        final BenefitCalculationRequest benefitCalculationRequest = new BenefitCalculationRequest();
        benefitCalculationRequest.setCurrentSubscriptionAmount(request.getTotalSubscription());
        benefitCalculationRequest.setDeliveryAmounts(deliveries.values().stream().filter(delivery ->
                !delivery.getStatus().equals(DeliveryStatus.DELIVERED)
                        && delivery.getDeliveryDate().isAfter(SysDate.now())).collect(Collectors.toMap(
                Delivery::getDeliveryId, Delivery::getTotalDeliveryPrice
        )));
        benefitCalculationRequest.setTotalLoyaltyPeriod(request.getDuration());
        benefitCalculationRequest.setTotalSubscriptionAmount(request.getTotalSubscription());
        benefitCalculationRequest.setCurrentSubscriptionPeriod(
                Days.daysBetween(deliveries.firstEntry().getValue().getDeliveryDate(),
                        deliveries.lastEntry().getValue().getDeliveryDate()).getDays()/30);
        benefitCalculationRequest.setBenefitEquation(request.getBenefit().getBenefitEquation());
        return benefitExecutionContext.calculateBenefit(benefitCalculationRequest);
    }

    private List<SubscriptionItem> getSubscriptionItems(@RequestBody BenefitSimulatorRequest request) {
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