package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
@Document(collection = "SubscriptionView")
public class SubscriptionView {

    @Id
    private String subscriptionId;
    private String subscriberId;
    private ConsumerBasketActivationStatus consumerBasketStatus;
    private List<BasketItemView> basketItemViews;
    private Address shippingAddress;
    private Address billingAddress;
    private ContactDetails contactDetails;
    private double totalAmountAfterDiscount;
    private LocalDate basketCreatedDate;
    private LocalDate basketExpiredDate;

    public SubscriptionView(String subscriptionId, String subscriberId, ConsumerBasketActivationStatus consumerBasketStatus, List<BasketItemView> basketItemViews, Address shippingAddress, Address billingAddress, ContactDetails contactDetails, double totalAmountAfterDiscount, LocalDate basketCreatedDate, LocalDate basketExpiredDate) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.consumerBasketStatus = consumerBasketStatus;
        this.basketItemViews = basketItemViews;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.contactDetails = contactDetails;
        this.totalAmountAfterDiscount = totalAmountAfterDiscount;
        this.basketCreatedDate = basketCreatedDate;
        this.basketExpiredDate = basketExpiredDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public ConsumerBasketActivationStatus getConsumerBasketStatus() {
        return consumerBasketStatus;
    }

    public void setConsumerBasketStatus(ConsumerBasketActivationStatus consumerBasketStatus) {
        this.consumerBasketStatus = consumerBasketStatus;
    }

    public List<BasketItemView> getBasketItemViews() {
        return basketItemViews;
    }

    public void setBasketItemViews(List<BasketItemView> basketItemViews) {
        this.basketItemViews = basketItemViews;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public double getTotalAmountAfterDiscount() {
        return totalAmountAfterDiscount;
    }

    public void setTotalAmountAfterDiscount(double totalAmountAfterDiscount) {
        this.totalAmountAfterDiscount = totalAmountAfterDiscount;
    }

    public LocalDate getBasketCreatedDate() {
        return basketCreatedDate;
    }

    public void setBasketCreatedDate(LocalDate basketCreatedDate) {
        this.basketCreatedDate = basketCreatedDate;
    }

    public LocalDate getBasketExpiredDate() {
        return basketExpiredDate;
    }

    public void setBasketExpiredDate(LocalDate basketExpiredDate) {
        this.basketExpiredDate = basketExpiredDate;
    }
}
