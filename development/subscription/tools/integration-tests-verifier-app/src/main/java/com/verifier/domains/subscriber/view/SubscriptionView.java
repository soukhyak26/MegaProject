package com.verifier.domains.subscriber.view;

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
    private ConsumerBasketActivationStatus consumerBasketActivationStatus;
    private List<SubscriptionItem> subscriptionItems;
    private Address shippingAddress;
    private Address billingAddress;
    private ContactDetails contactDetails;
    private double totalAmountAfterDiscount;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private String paymentSchemeId;

    public SubscriptionView() {
    }

    public SubscriptionView(String subscriptionId, String subscriberId, ConsumerBasketActivationStatus consumerBasketActivationStatus, List<SubscriptionItem> subscriptionItems, Address shippingAddress, Address billingAddress, ContactDetails contactDetails, double totalAmountAfterDiscount, LocalDate creationDate, LocalDate expirationDate) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.consumerBasketActivationStatus = consumerBasketActivationStatus;
        this.subscriptionItems = subscriptionItems;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.contactDetails = contactDetails;
        this.totalAmountAfterDiscount = totalAmountAfterDiscount;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
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

    public ConsumerBasketActivationStatus getConsumerBasketActivationStatus() {
        return consumerBasketActivationStatus;
    }

    public void setConsumerBasketActivationStatus(ConsumerBasketActivationStatus consumerBasketActivationStatus) {
        this.consumerBasketActivationStatus = consumerBasketActivationStatus;
    }

    public List<SubscriptionItem> getSubscriptionItems() {
        return subscriptionItems;
    }

    public void setSubscriptionItems(List<SubscriptionItem> subscriptionItems) {
        this.subscriptionItems = subscriptionItems;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }

    public void setPaymentSchemeId(String paymentSchemeId) {
        this.paymentSchemeId = paymentSchemeId;
    }
}
