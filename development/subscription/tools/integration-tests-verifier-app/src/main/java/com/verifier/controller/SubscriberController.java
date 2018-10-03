package com.verifier.controller;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.common.vo.SubscriberName;
import com.affaince.subscription.date.SysDate;
import com.verifier.domains.subscriber.repository.*;
import com.verifier.domains.subscriber.view.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "subscriber")
public class SubscriberController {

    private final BenefitViewRepository benefitViewRepository;
    private final DeliveryActualsViewRepository deliveryActualsViewRepository;
    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;
    private final DeliveryForecastTrendViewRepository deliveryForecastTrendViewRepository;
    private final DeliveryForecastViewRepository deliveryForecastViewRepository;
    private final DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository;
    private final DeliveryViewRepository deliveryViewRepository;
    private final LatestPriceBucketViewRepository latestPriceBucketViewRepository;
    private final PaymentSchemesViewRepository paymentSchemesViewRepository;
    private final SubscriberProductViewRepository subscriberProductViewRepository;
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
    private final SubscriptionRuleViewRepository subscriptionRuleViewRepository;
    private final SubscriptionSummaryViewRepository subscriptionSummaryViewRepository;
    private final SubscriptionViewRepository subscriptionViewRepository;

    @Autowired
    public SubscriberController(BenefitViewRepository benefitViewRepository, DeliveryActualsViewRepository deliveryActualsViewRepository, DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository, DeliveryForecastTrendViewRepository deliveryForecastTrendViewRepository, DeliveryForecastViewRepository deliveryForecastViewRepository, DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository, DeliveryViewRepository deliveryViewRepository, LatestPriceBucketViewRepository latestPriceBucketViewRepository, PaymentSchemesViewRepository paymentSchemesViewRepository, SubscriberProductViewRepository subscriberProductViewRepository, SubscriberForecastTrendViewRepository subscriberForecastTrendViewRepository, SubscriberPseudoActualsViewRepository subscriberPseudoActualsViewRepository, SubscribersActualsViewRepository subscribersActualsViewRepository, SubscribersForecastViewRepository subscribersForecastViewRepository, SubscriberViewRepository subscriberViewRepository, SubscriptionActualsViewRepoitory subscriptionActualsViewRepoitory, SubscriptionForecastTrendViewRepository subscriptionForecastTrendViewRepository, SubscriptionForecastViewRepository subscriptionForecastViewRepository, SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository, SubscriptionReceivedPaymentViewRepository subscriptionReceivedPaymentViewRepository, SubscriptionRuleViewRepository subscriptionRuleViewRepository, SubscriptionSummaryViewRepository subscriptionSummaryViewRepository, SubscriptionViewRepository subscriptionViewRepository) {
        this.benefitViewRepository = benefitViewRepository;
        this.deliveryActualsViewRepository = deliveryActualsViewRepository;
        this.deliveryChargesRuleViewRepository = deliveryChargesRuleViewRepository;
        this.deliveryForecastTrendViewRepository = deliveryForecastTrendViewRepository;
        this.deliveryForecastViewRepository = deliveryForecastViewRepository;
        this.deliveryPseudoActualsViewRepository = deliveryPseudoActualsViewRepository;
        this.deliveryViewRepository = deliveryViewRepository;
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
        this.paymentSchemesViewRepository = paymentSchemesViewRepository;
        this.subscriberProductViewRepository = subscriberProductViewRepository;
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

    @RequestMapping(method = RequestMethod.GET, value = "benefits/schemes")
    public ResponseEntity<List<BenefitView>> getBenefitSchemes() {
        List<BenefitView> schemes = benefitViewRepository.findAll();
        return new ResponseEntity<>(schemes, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliveries/actuals")
    public ResponseEntity<List<DeliveryActualsView>> getDeliveryActuals() {
        List<DeliveryActualsView> deliveryActuals = deliveryActualsViewRepository.findAll();
        return new ResponseEntity<>(deliveryActuals, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliverycharge/rules")
    public ResponseEntity<List<DeliveryChargesRuleView>> getDeliveryChargeRules() {
        List<DeliveryChargesRuleView> rules = deliveryChargesRuleViewRepository.findAll();
        return new ResponseEntity<>(rules, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliveries/forecast/trend")
    public ResponseEntity<List<DeliveryForecastTrendView>> getDeliveryTrend() {
        List<DeliveryForecastTrendView> trends = deliveryForecastTrendViewRepository.findAll();
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliveries/forecast")
    public ResponseEntity<List<DeliveryForecastView>> getDeliveryForecast() {
        List<DeliveryForecastView> forecasts = deliveryForecastViewRepository.findAll();
        return new ResponseEntity<>(forecasts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliveries/pseudoactuals")
    public ResponseEntity<List<DeliveryPseudoActualsView>> getDeliveryPseudoActuals() {
        List<DeliveryPseudoActualsView> forecasts = deliveryPseudoActualsViewRepository.findAll();
        return new ResponseEntity<>(forecasts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliveries")
    public ResponseEntity<List<DeliveryView>> getDeliveries() {
        List<DeliveryView> deliveries = deliveryViewRepository.findAll();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliveries/approved/{subscriberId}/{subscriptionId}")
    public ResponseEntity<List<DeliveryView>> getApprovedDeliveriesForAsubscriber(@PathVariable String subscriberId, @PathVariable String subscriptionId) {
        List<DeliveryView> deliveries = deliveryViewRepository.findByDeliveryId_SubscriberIdAndDeliveryId_SubscriptionIdAndDeliveryStatus(subscriberId,subscriptionId, DeliveryStatus.APPROVED);
        if( deliveries != null && !deliveries.isEmpty()){
            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = {
            "deliveries/approved/next/subscriberid/{subscriberId}/subscriptionid/{subscriptionId}",
            "deliveries/approved/next/subscriberid/{subscriberId}/subscriptionid/{subscriptionId}/deliverydate/{deliveryDate}"
    })
    public ResponseEntity<DeliveryView> getNextApprovedDeliveryForASubscriber(@PathVariable String subscriberId, @PathVariable String subscriptionId, @PathVariable Optional<LocalDate> deliveryDate) {
        List<DeliveryView> deliveries=null;
        if(deliveryDate.isPresent()) {
            deliveries = deliveryViewRepository.findByDeliveryId_SubscriberIdAndDeliveryId_SubscriptionIdAndDeliveryDateAndDeliveryStatus(subscriberId, subscriptionId, deliveryDate.get(), DeliveryStatus.APPROVED);
        }else{
            deliveries = deliveryViewRepository.findByDeliveryId_SubscriberIdAndDeliveryId_SubscriptionIdAndDeliveryStatus(subscriberId, subscriptionId, DeliveryStatus.APPROVED);
        }
        if( deliveries != null && !deliveries.isEmpty()){
            DeliveryView nextDelivery = deliveries.stream().min(Comparator.comparing(DeliveryView::getDeliveryDate)).get();
            return new ResponseEntity<>(nextDelivery, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliveries/{deliveryId}/{subscriberId}/{subscriptionId}")
    public ResponseEntity<DeliveryView> getDelivery(@PathVariable String deliveryId,@PathVariable String subscriberId, @PathVariable String subscriptionId) {
        DeliveryView delivery = deliveryViewRepository.findOne(new DeliveryId(deliveryId,subscriberId,subscriptionId));
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "pricebucket/{productId}")
    public ResponseEntity<LatestPriceBucketView> getPrices(@PathVariable String productId) {
        LatestPriceBucketView price = latestPriceBucketViewRepository.findOne(productId);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "payments/scheme/{schemeId}")
    public ResponseEntity<PaymentSchemesView> getPaymentScheme(@PathVariable String schemeId) {
        PaymentSchemesView scheme = paymentSchemesViewRepository.findOne(schemeId);
        return new ResponseEntity<>(scheme, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "payments/schemes")
    public ResponseEntity<List<PaymentSchemesView>> getPaymentScheme() {
        List<PaymentSchemesView> schemes = paymentSchemesViewRepository.findAll();
        return new ResponseEntity<>(schemes, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "product/{productId}")
    public ResponseEntity<ProductView> getProduct(@PathVariable String productId) {
        ProductView productView = subscriberProductViewRepository.findOne(productId);
        return new ResponseEntity<>(productView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "forecast/trend")
    public ResponseEntity<List<SubscriberForecastTrendView>> getSubscriberForecastTrend() {
        List<SubscriberForecastTrendView> subscriberForecastTrendViews = subscriberForecastTrendViewRepository.findAll();
        return new ResponseEntity<>(subscriberForecastTrendViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "pseudoactuals")
    public ResponseEntity<List<SubscriberPseudoActualsView>> getSubscriberPseudoactuals() {
        List<SubscriberPseudoActualsView> pseudoActualsViews = subscriberPseudoActualsViewRepository.findAll();
        return new ResponseEntity<>(pseudoActualsViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "actuals")
    public ResponseEntity<List<SubscribersActualsView>> getSubscriberActuals() {
        List<SubscribersActualsView> actualsViews = subscribersActualsViewRepository.findAllByOrderByRegistrationDateDesc();
        return new ResponseEntity<>(actualsViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "forecast")
    public ResponseEntity<List<SubscribersForecastView>> getSubscriberForecast() {
        List<SubscribersForecastView> forecastViews = subscribersForecastViewRepository.findAll();
        return new ResponseEntity<>(forecastViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{subscriberId}")
    public ResponseEntity<SubscriberView> getSubscriber(@PathVariable String subscriberId) {
        SubscriberView subscriber = subscriberViewRepository.findOne(subscriberId);
        return new ResponseEntity<>(subscriber, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/active/subscriber/{subscriberId}")
    public ResponseEntity<SubscriptionView> getActiveSubscriptionForASubscriber(@PathVariable String subscriberId) {
        SubscriptionView subscription = subscriptionViewRepository.findBySubscriberIdAndConsumerBasketActivationStatus(subscriberId, ConsumerBasketActivationStatus.ACTIVATED);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "name/{firstName}/{middleName}/{lastName}/{title}")
    public ResponseEntity<SubscriberView> getSubscriberByName(@PathVariable(name = "firstName" ) String firstName,@PathVariable(name = "middleName" ) String middleName,@PathVariable(name = "lastName" ) String lastName,@PathVariable(name = "title" ) String title ) {
        SubscriberName subscriberName= new SubscriberName(title,firstName,middleName,lastName);
        SubscriberView subscriber = subscriberViewRepository.findBySubscriberName(subscriberName).get(0);
        return new ResponseEntity<>(subscriber, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscriptions/actuals")
    public ResponseEntity<List<SubscriptionActualsView>> getSubscriptions() {
        List<SubscriptionActualsView> subscriptions = subscriptionActualsViewRepoitory.findAllByOrderBySubscriptionActualsVersionId_StartDateDesc();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscriptions/forecast/trend")
    public ResponseEntity<List<SubscriptionForecastTrendView>> getSubscriptionForecastTrends() {
        List<SubscriptionForecastTrendView> trends = subscriptionForecastTrendViewRepository.findAll();
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscriptions/forecast")
    public ResponseEntity<List<SubscriptionForecastView>> getSubscriptionForecast() {
        List<SubscriptionForecastView> forecastViews = subscriptionForecastViewRepository.findAll();
        return new ResponseEntity<>(forecastViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscriptions/pseudoactuals")
    public ResponseEntity<List<SubscriptionPseudoActualsView>> getSubscriptionPseudoActuals() {
        List<SubscriptionPseudoActualsView> pseudoActualsViews = subscriptionPseudoActualsViewRepository.findAll();
        return new ResponseEntity<>(pseudoActualsViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/receivedpayment")
    public ResponseEntity<List<SubscriptionReceivedPaymentView>> getReceivedPayments() {
        List<SubscriptionReceivedPaymentView> receivedPaymentViews = subscriptionReceivedPaymentViewRepository.findAll();
        return new ResponseEntity<>(receivedPaymentViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/receivedpayment/{subscriptionId}")
    public ResponseEntity<SubscriptionReceivedPaymentView> getReceivedPayment(@PathVariable String subscriptionId) {
        SubscriptionReceivedPaymentView receivedPaymentView = subscriptionReceivedPaymentViewRepository.findOne(subscriptionId);
        return new ResponseEntity<>(receivedPaymentView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/rules")
    public ResponseEntity<List<SubscriptionRuleView>> getSubscriptionRules() {
        List<SubscriptionRuleView> rules = subscriptionRuleViewRepository.findAll();
        return new ResponseEntity<>(rules, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/summary/{Id}")
    public ResponseEntity<SubscriptionSummaryView> getSubscriptionSummary(@PathVariable int Id) {
        SubscriptionSummaryView summary = subscriptionSummaryViewRepository.findOne(Id);
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/{subscriptionId}")
    public ResponseEntity<SubscriptionView> getSubscription(@PathVariable String subscriptionId) {
        SubscriptionView subscriptionView = subscriptionViewRepository.findOne(subscriptionId);
        return new ResponseEntity<>(subscriptionView, HttpStatus.OK);
    }

}
