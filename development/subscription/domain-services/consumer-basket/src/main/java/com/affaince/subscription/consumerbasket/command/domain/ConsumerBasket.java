package com.affaince.subscription.consumerbasket.command.domain;

import com.affaince.subscription.consumerbasket.command.AddAddressToConsumerBasketCommand;
import com.affaince.subscription.consumerbasket.command.AddItemToConsumerBasketCommand;
import com.affaince.subscription.consumerbasket.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class ConsumerBasket extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String basketId;
    private String userId;
    private List<BasketItem> basketItems;
    private Address shippingAddress;
    private Address billingAddress;
    private ContactDetails contactDetails;
    private double totalAmount;
    private double totalAmountAfterDiscount;

    public ConsumerBasket(String basketId, String userId) {
        apply(new ConsumerBasketCreatedEvent(basketId, userId));
    }

    public ConsumerBasket() {

    }

    @EventSourcingHandler
    public void on(ConsumerBasketCreatedEvent event) {
        this.basketId = event.getBasketId();
        this.userId = event.getUserId();
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
    public void on(ShippingAddressAddedToConsumerBasketEvent event) {
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
    public void on(BillingAddressAddedToConsumerBasketEvent event) {
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
    public void on(ItemAddedToConsumerBasketEvent event) {
        System.out.println("in event publish " + event.getFrequencyUnit());
        Frequency frequency = new Frequency(event.getFrequency(), event.getFrequencyUnit());
        BasketItem basketItem = new BasketItem(event.getItemId(), event.getProductId(),
                event.getQuantityPerBasket(), frequency, event.getItemMRP(),
                event.getItemDiscountedPrice());
        if (basketItems == null) {
            basketItems = new ArrayList<>();
        }
        basketItems.add(basketItem);
    }

    public void updateContactDetails(String email, String mobileNumber, String alternativeNumber) {
        apply(new ContactDetailsAddedEvent(this.basketId, email, mobileNumber, alternativeNumber));
    }

    public void addAddress(AddAddressToConsumerBasketCommand command) {
        if (command.getAddressType().equals("shipping")) {
            apply(new ShippingAddressAddedToConsumerBasketEvent(this.basketId,
                    command.getAddressLine1(),
                    command.getAddressLine2(),
                    command.getCity(),
                    command.getState(),
                    command.getCountry(),
                    command.getPinCode()));
        } else {
            apply(new BillingAddressAddedToConsumerBasketEvent(this.basketId,
                    command.getAddressLine1(),
                    command.getAddressLine2(),
                    command.getCity(),
                    command.getState(),
                    command.getCountry(),
                    command.getPinCode()));
        }
    }

    public void addItemToBasket(AddItemToConsumerBasketCommand command) {
        System.out.println("in aggregate " + command.getFrequencyUnit());
        apply(new ItemAddedToConsumerBasketEvent(this.basketId, command.getItemId(),
                command.getProductId(), command.getQuantityPerBasket(), command.getFrequency(), command.getFrequencyUnit(),
                command.getItemMRP(), command.getItemDiscountedPrice()));
    }
}
