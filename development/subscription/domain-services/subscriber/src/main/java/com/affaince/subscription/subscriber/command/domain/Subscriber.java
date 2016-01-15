package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.NetWorthSubscriberStatus;
import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.common.vo.SubscriberName;
import com.affaince.subscription.subscriber.command.DeleteBasketCommand;
import com.affaince.subscription.subscriber.command.ItemDispatchStatus;
import com.affaince.subscription.subscriber.command.UpdateStatusAndDispatchDateCommand;
import com.affaince.subscription.subscriber.command.UpdateSubscriberAddressCommand;
import com.affaince.subscription.subscriber.command.event.*;
import com.affaince.subscription.subscriber.services.Base64Encoder;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, Basket> baskets;
    private String password;

    private static final Logger logger = LoggerFactory.getLogger(Subscriber.class);

    public Subscriber(String subscriberId, SubscriberName subscriberName, Address address, ContactDetails contactDetails) {
        apply(new SubscriberCreatedEvent(subscriberId, subscriberName, address, contactDetails, NetWorthSubscriberStatus.NORMAL.getSubscriberStatusCode()));
        baskets = new HashMap<>();
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

    @EventSourcingHandler
    public void on(BasketCreatedEvent event) {
        this.subscriberId = event.getSubscriberId();
        final Basket basket = new Basket(event.getBasketId(), event.getDeliveryItems(),
                event.getDeliveryDate(), event.getDispatchDate(), event.getStatus());
        this.baskets.put(event.getBasketId(), basket);
    }

    @EventSourcingHandler
    public void on(StatusAndDispatchDateUpdatedEvent event) {
        this.subscriberId = event.getSubscriptionId();
        Basket basket = this.baskets.get(event.getBasketId());
        basket.setDispatchDate(new LocalDate(event.getDispatchDate()));
        basket.setStatus(DeliveryStatus.valueOf(event.getBasketDeliveryStatus()));
        List<DeliveryItem> deliveryItems = basket.getDeliveryItems();
        for (ItemDispatchStatus itemDispatchStatus : event.getItemDispatchStatuses()) {
            DeliveryItem deliveryItem = new DeliveryItem(itemDispatchStatus.getItemId(), null);
            deliveryItem = deliveryItems.get(deliveryItems.indexOf(deliveryItem));
            deliveryItem.setDeliveryStatus(DeliveryStatus.valueOf(itemDispatchStatus.getItemDeliveryStatus()));
        }
    }

    @EventSourcingHandler
    public void on(BasketDeletedEvent event) {
        this.subscriberId = event.getSubscriptionId();
        Basket basket = this.baskets.get(event.getBasketId());
        basket.setStatus(DeliveryStatus.DELETED);
    }

    @EventSourcingHandler
    public void on(SubscriberPasswordSetEvent event) {
        this.subscriberId = event.getSubscriberId();
        this.password = event.getPassword();
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

    public void addBasket(Basket basket) {
        apply(new BasketCreatedEvent(
                this.subscriberId, basket.getBasketId(), basket.getDeliveryItems(),
                basket.getDeliveryDate(), basket.getDispatchDate(), basket.getStatus()));
    }

    public void updateStatusAndDispatchDate(UpdateStatusAndDispatchDateCommand command) {
        apply(new StatusAndDispatchDateUpdatedEvent(this.subscriberId, command.getBasketId(), command.getBasketDeliveryStatus(), command.getDispatchDate(),
                command.getItemDispatchStatuses()));
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
}
