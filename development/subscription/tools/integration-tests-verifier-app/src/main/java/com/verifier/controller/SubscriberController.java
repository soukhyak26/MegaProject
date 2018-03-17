package com.verifier.controller;

import com.verifier.domains.subscriber.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "subscriber")
public class SubscriberController {

    private final BenefitViewRepository benefitViewRepository;
    private final DeliveryActualsViewRepository deliveryActualsViewRepository;
    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;
    private final DeliveryForecastTrendViewRepository deliveryForecastTrendViewRepository;
    private final DeliveryForecastViewRepository deliveryForecastViewRepository;
    private final DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository;
    private final DeliveryViewPagingRepository deliveryViewPagingRepository;
    private final DeliveryViewRepository deliveryViewRepository;
    private final LatestPriceBucketViewRepository latestPriceBucketViewRepository;
    private final PaymentSchemesViewRepository paymentSchemesViewRepository;
    private final ProductViewRepository productViewRepository;
    private final SubscriberForecastTrendViewRepository subscriberForecastTrendViewRepository;
    private final SubscriberPseudoActualsViewRepository subscriberPseudoActualsViewRepository;
    private final SubscribersActualsViewRepository subscribersActualsViewRepository;
    private final SubscribersForecastViewRepository subscribersForecastViewRepository;
    private final SubscriberViewRepository subscriberViewRepository;
    private final SubscriptionActualsViewRepoitory subscriptionActualsViewRepoitory;
    private final SubscriptionForecastTrendViewRepository subscriptionForecastTrendViewRepository;
    private final SubscriptionForecastViewRepository subscriptionForecastViewRepository;
    private final SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository;
    private final SubscriptionReceivedPaymentViewRepository subscriptionReceivedPaymentViewRepository;
    private final SubscriptionRuleViewRepository subscriptionRuleViewRepository ;
    private final SubscriptionSummaryViewRepository subscriptionSummaryViewRepository;
    private final SubscriptionViewRepository subscriptionViewRepository;

    @Autowired
    public SubscriberController(BenefitViewRepository benefitViewRepository, DeliveryActualsViewRepository deliveryActualsViewRepository, DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository, DeliveryForecastTrendViewRepository deliveryForecastTrendViewRepository, DeliveryForecastViewRepository deliveryForecastViewRepository, DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository, DeliveryViewPagingRepository deliveryViewPagingRepository, DeliveryViewRepository deliveryViewRepository, LatestPriceBucketViewRepository latestPriceBucketViewRepository, PaymentSchemesViewRepository paymentSchemesViewRepository, ProductViewRepository productViewRepository, SubscriberForecastTrendViewRepository subscriberForecastTrendViewRepository, SubscriberPseudoActualsViewRepository subscriberPseudoActualsViewRepository, SubscribersActualsViewRepository subscribersActualsViewRepository, SubscribersForecastViewRepository subscribersForecastViewRepository, SubscriberViewRepository subscriberViewRepository, SubscriptionActualsViewRepoitory subscriptionActualsViewRepoitory, SubscriptionForecastTrendViewRepository subscriptionForecastTrendViewRepository, SubscriptionForecastViewRepository subscriptionForecastViewRepository, SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository, SubscriptionReceivedPaymentViewRepository subscriptionReceivedPaymentViewRepository, SubscriptionRuleViewRepository subscriptionRuleViewRepository, SubscriptionSummaryViewRepository subscriptionSummaryViewRepository, SubscriptionViewRepository subscriptionViewRepository) {
        this.benefitViewRepository = benefitViewRepository;
        this.deliveryActualsViewRepository = deliveryActualsViewRepository;
        this.deliveryChargesRuleViewRepository = deliveryChargesRuleViewRepository;
        this.deliveryForecastTrendViewRepository = deliveryForecastTrendViewRepository;
        this.deliveryForecastViewRepository = deliveryForecastViewRepository;
        this.deliveryPseudoActualsViewRepository = deliveryPseudoActualsViewRepository;
        this.deliveryViewPagingRepository = deliveryViewPagingRepository;
        this.deliveryViewRepository = deliveryViewRepository;
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
        this.paymentSchemesViewRepository = paymentSchemesViewRepository;
        this.productViewRepository = productViewRepository;
        this.subscriberForecastTrendViewRepository = subscriberForecastTrendViewRepository;
        this.subscriberPseudoActualsViewRepository = subscriberPseudoActualsViewRepository;
        this.subscribersActualsViewRepository = subscribersActualsViewRepository;
        this.subscribersForecastViewRepository = subscribersForecastViewRepository;
        this.subscriberViewRepository = subscriberViewRepository;
        this.subscriptionActualsViewRepoitory = subscriptionActualsViewRepoitory;
        this.subscriptionForecastTrendViewRepository = subscriptionForecastTrendViewRepository;
        this.subscriptionForecastViewRepository = subscriptionForecastViewRepository;
        this.subscriptionPseudoActualsViewRepository = subscriptionPseudoActualsViewRepository;
        this.subscriptionReceivedPaymentViewRepository = subscriptionReceivedPaymentViewRepository;
        this.subscriptionRuleViewRepository = subscriptionRuleViewRepository;
        this.subscriptionSummaryViewRepository = subscriptionSummaryViewRepository;
        this.subscriptionViewRepository = subscriptionViewRepository;
    }
}
