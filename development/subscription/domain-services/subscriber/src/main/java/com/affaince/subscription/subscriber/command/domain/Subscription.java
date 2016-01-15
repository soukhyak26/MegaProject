package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.affaince.subscription.common.type.Frequency;
import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.subscriber.command.AddAddressToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.AddItemToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.*;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class Subscription extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String subscriptionId;
    private String subscriberId;
    private ConsumerBasketActivationStatus consumerBasketStatus;
    private List<BasketItem> basketItems;
    private Address shippingAddress;
    private Address billingAddress;
    private ContactDetails contactDetails;
    private double totalAmount;
    private double totalAmountAfterDiscount;
    private LocalDate basketCreatedDate;
    private LocalDate basketExpiredDate;

    public Subscription(String subscriptionId, String subscriberId) {
        apply(new SubscriptionCreatedEvent(subscriptionId, subscriberId, LocalDate.now(), null, ConsumerBasketActivationStatus.CREATED));
    }

    public Subscription() {

    }

    @EventSourcingHandler
    public void on(SubscriptionCreatedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.subscriberId = event.getSubscriberId();
        this.basketCreatedDate = event.getBasketCreatedDate();
        this.basketExpiredDate = event.getBasketExpiredDate();
        this.consumerBasketStatus = event.getConsumerBasketStatus();
    }

    @EventSourcingHandler
    public void on(ContactDetailsAddedEvent event) {
        ContactDetails contactDetails = new ContactDetails(
                event.getEmail(),
                event.getMobileNumber(),
                event.getAlternativeNumber()
        );
        this.contactDetails = contactDetails;
    }

    @EventSourcingHandler
    public void on(ShippingAddressAddedToSubscriptionEvent event) {
        Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        this.shippingAddress = address;
    }

    @EventSourcingHandler
    public void on(BillingAddressAddedToSubscriptionEvent event) {
        Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        this.billingAddress = address;
    }

    @EventSourcingHandler
    public void on(ItemAddedToSubscriptionEvent event) {
        final Frequency frequency = new Frequency(event.getQuantityPerBasket(), new Period(event.getFrequency(),
                PeriodUnit.valueOf(event.getFrequencyUnit())));
        final BasketItem basketItem = new BasketItem(event.getItemId(),
                frequency, event.getDiscountedOfferedPrice(), event.getNoOfCycle());
        if (basketItems == null) {
            basketItems = new ArrayList<>();
        }
        basketItems.add(basketItem);
    }

    @EventSourcingHandler
    public void on(ItemRemovedFromSubscriptionEvent event) {
        basketItems.removeIf(item -> item.getItemId().equals(event.getItemId()));
    }

    public void on(SubscriptionActivatedEvent event) {
        this.consumerBasketStatus = ConsumerBasketActivationStatus.ACTIVATED;
    }

    public void updateContactDetails(String email, String mobileNumber, String alternativeNumber) {
        apply(new ContactDetailsAddedEvent(this.subscriptionId, email, mobileNumber, alternativeNumber));
    }

    public void addAddress(AddAddressToSubscriptionCommand command) {
        if (command.getAddressType().equals("shipping")) {
            apply(new ShippingAddressAddedToSubscriptionEvent(this.subscriptionId,
                    command.getAddressLine1(),
                    command.getAddressLine2(),
                    command.getCity(),
                    command.getState(),
                    command.getCountry(),
                    command.getPinCode()));
        } else {
            apply(new BillingAddressAddedToSubscriptionEvent(this.subscriptionId,
                    command.getAddressLine1(),
                    command.getAddressLine2(),
                    command.getCity(),
                    command.getState(),
                    command.getCountry(),
                    command.getPinCode()));
        }
    }

    public void addItemToBasket(AddItemToSubscriptionCommand command) {
        apply(new ItemAddedToSubscriptionEvent(this.subscriptionId, command.getItemId(),
                command.getQuantityPerBasket(), command.getFrequency(), command.getFrequencyUnit(),
                command.getDiscountedOfferedPrice(), command.getNoOfCycle()));
    }

    public void updateBasketStatus(int statusCode, int reasonCode, Date dispatchDate) {
        System.out.println("****@@@ConsumerBasket: subscriptionId:" + this.subscriptionId);
        System.out.println("****@@@ConsumerBasket: status code:" + statusCode);
        System.out.println("****@@@ConsumerBasket: reason code:" + reasonCode);
        System.out.println("****@@@ConsumerBasket: dispatch date:" + dispatchDate);
        apply(new BasketDispatchStatusUpdatedEvent(subscriptionId, dispatchDate, statusCode, reasonCode));
    }

    public void deleteItem(String itemId) {
        apply(new ItemRemovedFromSubscriptionEvent(this.subscriptionId, itemId));
    }

    public void activateSubscription() {
        apply(new SubscriptionActivatedEvent(this.subscriptionId));
        Map<String, Integer> subscribedProductUpdateCount = new HashMap<>(basketItems.size());
        for (BasketItem basketItem : basketItems) {
            Frequency frequency = basketItem.getFrequency();
            subscribedProductUpdateCount.put(basketItem.getItemId(), basketItem.getNoOfCycle() * frequency.getValue());
        }
        apply(new SubscribedProductCountUpdatedEvent(subscribedProductUpdateCount));
    }
}
