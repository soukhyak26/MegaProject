package com.affaince.subscription.consumerbasket.query.view;

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
@Document(collection = "ConsumerBasket")
public class ConsumerBasketView {

    @Id
    private String basketId;
    private String subscriberId;
    private ConsumerBasketActivationStatus consumerBasketStatus;
    private List<BasketItem> basketItems;
    private Address shippingAddress;
    private Address billingAddress;
    private ContactDetails contactDetails;
    private double totalAmountAfterDiscount;
    private LocalDate basketCreatedDate;
    private LocalDate basketExpiredDate;

    public ConsumerBasketView(String basketId, String subscriberId, ConsumerBasketActivationStatus consumerBasketStatus, List<BasketItem> basketItems, Address shippingAddress, Address billingAddress, ContactDetails contactDetails, double totalAmountAfterDiscount, LocalDate basketCreatedDate, LocalDate basketExpiredDate) {
        this.basketId = basketId;
        this.subscriberId = subscriberId;
        this.consumerBasketStatus = consumerBasketStatus;
        this.basketItems = basketItems;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.contactDetails = contactDetails;
        this.totalAmountAfterDiscount = totalAmountAfterDiscount;
        this.basketCreatedDate = basketCreatedDate;
        this.basketExpiredDate = basketExpiredDate;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
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

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
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
