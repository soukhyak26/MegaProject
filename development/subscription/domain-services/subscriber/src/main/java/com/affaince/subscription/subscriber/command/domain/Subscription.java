package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.command.AddAddressToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.AddItemToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.PaymentReceivedFromSourceCommand;
import com.affaince.subscription.subscriber.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class Subscription extends AbstractAnnotatedEntity {

    private String subscriptionId;
    private ConsumerBasketActivationStatus consumerBasketStatus;
    private List<SubscriptionItem> subscriptionItems;
    private Address shippingAddress;
    private Address billingAddress;
    private ContactDetails contactDetails;
    //TODO: Need to be removed as the total Subscription amount  with/without discount may not be fixed for non-committed/discount committed products
    //TODO: may be update only after last delivery calculation
    private double totalSubscriptionAmount;
    //TODO: Need to be removed as the discount amount may not be fixed for non-committed/discount committed products
    //TODO:may be update only after last delivery calculation
    private double subscriptionLevelDiscount;
    private double totalProfit;
    //TODO: Need to be removed as the total Subscription amount  with/without discount may not be fixed for non-committed/discount committed products
    //TODO: may be update only after last delivery calculation
    private double totalSubscriptionAmountAfterDiscount;
    private double totalPaymentReceived;
    private LocalDate subscriptionCreatedDate;
    private LocalDate subscriptionExpiredDate;

    public Subscription(String subscriptionId, String subscriberId) {
        apply(new SubscriptionCreatedEvent(subscriptionId, subscriberId, SysDate.now(), null, ConsumerBasketActivationStatus.CREATED));
    }

    public Subscription() {

    }

    public double getTotalSubscriptionAmount() {
        return totalSubscriptionAmount;
    }

    @EventSourcingHandler
    public void on(SubscriptionCreatedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.subscriptionCreatedDate = event.getBasketCreatedDate();
        this.subscriptionExpiredDate = event.getBasketExpiredDate();
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
        final SubscriptionItem subscriptionItem = new SubscriptionItem(event.getItemId(),
                event.getWeightInGrms(), event.getCountPerPeriod(), event.getPeriod(), event.getDiscountedOfferedPrice(),
                event.getOfferedPriceWithBasketLevelDiscount(), event.getNoOfCycles(), event.getProductPricingCategory());
        if (subscriptionItems == null) {
            subscriptionItems = new ArrayList<>();
        }
        subscriptionItems.add(subscriptionItem);
    }

    @EventSourcingHandler
    public void on(ItemRemovedFromSubscriptionEvent event) {
        subscriptionItems.removeIf(item -> item.getProductId().equals(event.getItemId()));
    }

    @EventSourcingHandler
    public void on(SubscriptionActivatedEvent event) {
        this.consumerBasketStatus = ConsumerBasketActivationStatus.ACTIVATED;
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.totalPaymentReceived = this.totalPaymentReceived - event.getPaymentAmount();
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
        apply(new ItemAddedToSubscriptionEvent(this.subscriptionId, command.getProductId(),
                command.getCountPerPeriod(), command.getPeriod(), command.getDiscountedOfferedPrice(),
                command.getOfferedPriceWithBasketLevelDiscount(), command.getNoOfCycles(), command.getWeightInGrms(),
                command.getProductPricingCategory()));
    }

    public void deleteItem(String itemId) {
        apply(new ItemRemovedFromSubscriptionEvent(this.subscriptionId, itemId));
    }

    public void activateSubscription() {
        apply(new SubscriptionActivatedEvent(this.subscriptionId));
        for (SubscriptionItem subscriptionItem : subscriptionItems) {
            apply(new ProductSubscriptionActivatedEvent(subscriptionItem.getProductId(),
                    subscriptionItem.getNoOfCycles() * subscriptionItem.getCountPerPeriod(),
                    SysDate.now()));
        }
    }

    public ConsumerBasketActivationStatus getConsumerBasketStatus() {
        return consumerBasketStatus;
    }

    public void addReceivedPayment(PaymentReceivedFromSourceCommand command) {
        apply(new PaymentProcessedEvent(this.subscriptionId, command.getSubscriberId(), command.getPaymentAmount(), command.getPaymentDate()));
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public List<SubscriptionItem> getSubscriptionItems() {
        return subscriptionItems;
    }
}
