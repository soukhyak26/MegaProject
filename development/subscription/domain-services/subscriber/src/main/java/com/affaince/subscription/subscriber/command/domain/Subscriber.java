package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.NetWorthSubscriberStatus;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.common.vo.SubscriberName;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.command.DeleteBasketCommand;
import com.affaince.subscription.subscriber.command.UpdateDeliveryStatusAndDispatchDateCommand;
import com.affaince.subscription.subscriber.command.UpdateSubscriberAddressCommand;
import com.affaince.subscription.subscriber.command.event.*;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitCalculationRequest;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitResult;
import com.affaince.subscription.subscriber.services.encoder.Base64Encoder;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class Subscriber extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String subscriberId;
    private SubscriberName subscriberName;
    private Address address;
    private ContactDetails contactDetails;
    private NetWorthSubscriberStatus status;
    private List<String> couponCodes;
    private int rewardPoints;
    private Map<String, Delivery> deliveries;
    private String password;
    private double totalRewardsPoint;
    private double availableRewardsPoint;
    private double totalSubscriptionAmount;
    private int totalLoyaltyPeriod;
    private LocalDate lastDeliveryDate;
    @EventSourcedMember
    private Subscription subscription;

    private static final Logger logger = LoggerFactory.getLogger(Subscriber.class);

    public Subscriber(String subscriberId, SubscriberName subscriberName, Address address, ContactDetails contactDetails) {
        apply(new SubscriberCreatedEvent(subscriberId, subscriberName, address, contactDetails, NetWorthSubscriberStatus.NORMAL.getSubscriberStatusCode()));
        deliveries = new HashMap<>();
    }

    public Subscriber() {
    }

    @EventSourcingHandler
    public void on(SubscriberCreatedEvent event) {
        this.subscriberId = event.getSubscriberId();
        this.subscriberName = event.getSubscriberName();
        this.address = event.getAddress();
        this.contactDetails = event.getContactDetails();
        this.status = NetWorthSubscriberStatus.valueOf(event.getSubscriberStatusCode());
    }

    @EventSourcingHandler
    public void on(SubscriberContactDetailsUpdatedEvent event) {
        ContactDetails contactDetails = new ContactDetails(
                event.getEmail(),
                event.getMobileNumber(),
                event.getAlternativeNumber()
        );
        this.contactDetails = contactDetails;
    }

    @EventSourcingHandler
    public void on(SubscriberAddressUpdatedEvent event) {
        this.subscriberName = subscriberName;
        Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        this.address = address;
    }

    @EventSourcingHandler
    public void on(RewardPointsAddedEvent event) {
        this.rewardPoints = this.rewardPoints + event.getRewardPoints();
    }

    @EventSourcingHandler
    public void on(CouponCodeAddedEvent event) {
        if (this.couponCodes == null) {
            this.couponCodes = new ArrayList<>();
        }
        this.couponCodes.add(event.getCouponCode());
    }

    // TODO: Need to refactor DeliveryStatusAndDispatchDateUpdatedEvent. It might only require id, date and status
    @EventSourcingHandler
    public void on(DeliveryStatusAndDispatchDateUpdatedEvent event) {
        this.subscriberId = event.getSubscriptionId();

        Delivery delivery = this.deliveries.get(event.getBasketId());
        delivery.setDispatchDate(new LocalDate(event.getDispatchDate()));
        delivery.setStatus(DeliveryStatus.valueOf(event.getBasketDeliveryStatus()));
        List<DeliveryItem> deliveryItems = delivery.getDeliveryItems();
        for (ItemDispatchStatus itemDispatchStatus : event.getItemDispatchStatuses()) {
            DeliveryItem deliveryItem = new DeliveryItem(itemDispatchStatus.getItemId());
            deliveryItem = deliveryItems.get(deliveryItems.indexOf(deliveryItem));
            deliveryItem.setDeliveryStatus(DeliveryStatus.valueOf(itemDispatchStatus.getItemDeliveryStatus()));
        }
        if (DeliveryStatus.DELIVERED.getDeliveryStatusCode() == event.getBasketDeliveryStatus()) {
            setSubscriberLevelParameters(delivery);
        }
    }

    private void setSubscriberLevelParameters(Delivery delivery) {
        this.totalSubscriptionAmount = this.totalSubscriptionAmount + delivery.getTotalDeliveryPrice();
        this.availableRewardsPoint = this.availableRewardsPoint + delivery.getRewardPoints();
        Period period = new Period(this.lastDeliveryDate, delivery.getDeliveryDate());
        this.totalLoyaltyPeriod = this.totalLoyaltyPeriod + period.getDays();
        this.lastDeliveryDate = delivery.getDeliveryDate();
    }

    @EventSourcingHandler
    public void on(BasketDeletedEvent event) {
        this.subscriberId = event.getSubscriptionId();
        Delivery delivery = this.deliveries.get(event.getBasketId());
        delivery.setStatus(DeliveryStatus.DELETED);
    }

    @EventSourcingHandler
    public void on(SubscriberPasswordSetEvent event) {
        this.subscriberId = event.getSubscriberId();
        this.password = event.getPassword();
    }

    @EventSourcingHandler
    public void on (DeliveryCreatedEvent event) {
        deliveries.put(event.getDeliveryId(), new Delivery(event.getDeliveryId(), event.getDeliveryItems(), event.getDeliveryDate(), event.getDispatchDate(),
                event.getStatus()));
    }

    @EventSourcingHandler
    public void on(DeliveryDeletedEvent event) {
        Delivery tempDelivery = deliveries.get(event.getDeliveryId());
        deliveries.remove(event.getDeliveryId());

        final BenefitResult benefitResult = calculateBenefits(tempDelivery.getTotalDeliveryPrice() * (-1));
        for (String deliveryId : benefitResult.getRewardPointsDistribution().keySet()) {
            Delivery delivery = deliveries.get(deliveryId);
            delivery.setRewardPoints(benefitResult.getRewardPointsDistribution().get(deliveryId));
        }
    }

    public void updateContactDetails(String email, String mobileNumber, String alternativeNumber) {
        apply(new SubscriberContactDetailsUpdatedEvent(this.subscriberId, email, mobileNumber, alternativeNumber));
    }

    public void updateAddress(UpdateSubscriberAddressCommand command) {
        apply(new SubscriberAddressUpdatedEvent(
                this.subscriberId,
                command.getAddressLine1(),
                command.getAddressLine2(),
                command.getCity(),
                command.getState(),
                command.getCountry(),
                command.getPinCode()));
    }

    public void addRewardPoints(int rewardPoints) {
        apply(new RewardPointsAddedEvent(this.subscriberId, rewardPoints));
    }

    public void addCouponCode(String couponCode) {
        apply(new CouponCodeAddedEvent(this.subscriberId, couponCode));
    }

    public void updateStatusAndDispatchDate(UpdateDeliveryStatusAndDispatchDateCommand command) {
        Delivery delivery = this.deliveries.get(command.getBasketId());
        apply(new DeliveryStatusAndDispatchDateUpdatedEvent(this.subscriberId, command.getBasketId(),
                command.getBasketDeliveryStatus(), command.getDispatchDate(),
                command.getItemDispatchStatuses(), delivery.getDeliveryCharges(),
                delivery.getTotalDeliveryPrice()));
    }

    public void deleteBasket(DeleteBasketCommand command) {
        apply(new BasketDeletedEvent(this.subscriberId, command.getBasketId(), DeliveryStatus.DELETED));
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        try {
            apply(new SubscriberPasswordSetEvent(this.subscriberId, Base64Encoder.generateHash(password)));
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private Map<Integer, Delivery> makeDeliveriesReady(Subscription subscription) {
        final List<SubscriptionItem> subscriptionItems = subscription.getSubscriptionItems();
        final Map<Integer, Delivery> deliveries = new HashMap<>();
        int weekOfYear = SysDate.now().getWeekOfWeekyear();
        for (SubscriptionItem subscriptionItem : subscriptionItems) {
            int nextDeliveryWeek = weekOfYear;
            for (int i = 0; i < subscriptionItem.getNoOfCycles(); i++) {
                if (subscriptionItem.getPeriod().getUnit() == PeriodUnit.MONTH) {
                    nextDeliveryWeek = nextDeliveryWeek + subscriptionItem.getPeriod().getValue() * 4;
                } else {
                    nextDeliveryWeek = nextDeliveryWeek + subscriptionItem.getPeriod().getValue();
                }
                Delivery weeklyDelivery = deliveries.get(nextDeliveryWeek);
                if (weeklyDelivery == null) {
                    weeklyDelivery = new Delivery();
                    weeklyDelivery.setDeliveryId(nextDeliveryWeek + SysDate.now().getYear() + "");
                    weeklyDelivery.setDeliveryDate(SysDate.now().plusWeeks(nextDeliveryWeek - weekOfYear));
                    weeklyDelivery.setStatus(DeliveryStatus.CREATED);
                    deliveries.put(nextDeliveryWeek, weeklyDelivery);
                }
                for (int j = 0; j < subscriptionItem.getCountPerPeriod(); j++) {
                    DeliveryItem deliveryItem = new DeliveryItem();
                    deliveryItem.setDeliveryItemId(subscriptionItem.getProductId());
                    deliveryItem.setWeightInGrms(subscriptionItem.getWeightInGrms());
                    deliveryItem.setOfferedPriceWithBasketLevelDiscount(subscriptionItem.getOfferedPriceWithBasketLevelDiscount());
                    deliveryItem.setDeliveryStatus(DeliveryStatus.CREATED);
                    weeklyDelivery.getDeliveryItems().add(deliveryItem);
                }
            }
        }
        return deliveries;
    }

    public void confirmSubscription(DeliveryChargesRule deliveryChargesRule) {
        final Map<Integer, Delivery> deliveries = makeDeliveriesReady(subscription);
        // TODO: This is wrong place to call benefit calculation. new delivery is not added to deliveries map
        final BenefitResult benefitResult = calculateBenefits(0.0);
        Map<String, Double> rewardsPointsDistribution = benefitResult.getRewardPointsDistribution();
        for (Delivery delivery : deliveries.values()) {
            delivery.calculateTotalWeightInGrams();
            delivery.calculateItemLevelDeliveryCharges(deliveryChargesRule);
            delivery.setRewardPoints(rewardsPointsDistribution.get(delivery.getDeliveryId()));
            apply(new DeliveryCreatedEvent(delivery.getDeliveryId(), this.subscriberId, subscription.getSubscriptionId(),
                    delivery.getDeliveryItems(), delivery.getDeliveryDate(), delivery.getDispatchDate(), delivery.getStatus(),
                    delivery.getTotalWeight()));
        }
    }

    /*private BenefitResult calculateBenefits(Map<Integer, Delivery> deliveries) {
        final BenefitCalculationRequest benefitCalculationRequest = new BenefitCalculationRequest();
        benefitCalculationRequest.setCurrentSubscriptionAmount(subscription.getTotalSubscriptionAmount());
        benefitCalculationRequest.setDeliveryAmounts(deliveries.values().stream().collect(Collectors.toMap(
                Delivery::getDeliveryId, Delivery::getTotalDeliveryPrice
        )));
        benefitCalculationRequest.setTotalLoyaltyPeriod(totalLoyaltyPeriod);
        benefitCalculationRequest.setTotalSubscriptionAmount(totalSubscriptionAmount);
        BenefitExecutionContext benefitExecutionContext = new BenefitExecutionContext();
        return benefitExecutionContext.calculateBenefit(benefitCalculationRequest);
    }*/

    private BenefitResult calculateBenefits(double currentSubscriptionAmountDifference) {
        final BenefitCalculationRequest benefitCalculationRequest = new BenefitCalculationRequest();
        benefitCalculationRequest.setCurrentSubscriptionAmount(subscription.getTotalSubscriptionAmount()
                + currentSubscriptionAmountDifference);
        benefitCalculationRequest.setDeliveryAmounts(deliveries.values().stream().filter(delivery ->
                !delivery.getStatus().equals(DeliveryStatus.DELIVERED)
                        && delivery.getDeliveryDate().isAfter(SysDate.now())).collect(Collectors.toMap(
                Delivery::getDeliveryId, Delivery::getTotalDeliveryPrice
        )));
        benefitCalculationRequest.setRewardPointAdjustment(deliveries.values().stream().filter(delivery ->
                !delivery.getStatus().equals(DeliveryStatus.DELIVERED)
                        && delivery.getDeliveryDate().isAfter(SysDate.now())).mapToDouble(delivery -> delivery.getRewardPoints()).sum()
        );
        benefitCalculationRequest.setTotalLoyaltyPeriod(totalLoyaltyPeriod);
        benefitCalculationRequest.setTotalSubscriptionAmount(totalSubscriptionAmount);
        BenefitExecutionContext benefitExecutionContext = new BenefitExecutionContext();
        return benefitExecutionContext.calculateBenefit(benefitCalculationRequest);
    }

    public void setActiveSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void addDelivery(String deliveryId, LocalDate deliveryDate,
                            List<com.affaince.subscription.subscriber.web.request.DeliveryItem> deliveryItems, DeliveryChargesRule deliveryChargesRule) {
        final Delivery delivery = new Delivery(deliveryDate.getWeekOfWeekyear() + SysDate.now().getYear() + "", new ArrayList<>(),
                deliveryDate, null, DeliveryStatus.CREATED);
        deliveryItems.forEach(deliveryItem -> {
            for (int i = 0; i < deliveryItem.getQuantity(); i++) {
                delivery.getDeliveryItems().add(new DeliveryItem(deliveryItem.getDeliveryItemId(),
                        DeliveryStatus.CREATED, deliveryItem.getQuantityInGrms(), deliveryItem.getDeliveryItemOfferedPrice()));
            }
        });
        delivery.calculateTotalWeightInGrams();
        delivery.calculateItemLevelDeliveryCharges(deliveryChargesRule);
        // TODO: This is wrong place to call benefit calculation. new delivery is not added to deliveries map
        final BenefitResult benefitResult = calculateBenefits(delivery.getTotalDeliveryPrice());
        delivery.setRewardPoints(benefitResult.getRewardPointsDistribution().get(delivery.getDeliveryId()));
        apply(new DeliveryCreatedEvent(delivery.getDeliveryId(), this.subscriberId, subscription.getSubscriptionId(),
                delivery.getDeliveryItems(), delivery.getDeliveryDate(), delivery.getDispatchDate(), delivery.getStatus(),
                delivery.getTotalWeight()));
    }

    public void deleteDelivery(String deliveryId) {
        apply(new DeliveryDeletedEvent(this.subscriberId, deliveryId));
    }
}

