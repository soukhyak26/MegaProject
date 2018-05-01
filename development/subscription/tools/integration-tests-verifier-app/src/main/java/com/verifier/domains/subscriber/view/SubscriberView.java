package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.type.NetWorthSubscriberStatus;
import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.common.vo.SubscriberName;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Document(collection = "SubscriberView")
public class SubscriberView {
    @Id
    private String subscriberId;
    private SubscriberName subscriberName;
    private Address address;
    private ContactDetails contactDetails;
    private LocalDate creationDate;
    private NetWorthSubscriberStatus status;
    private List<String> couponCodes;
    private int rewardPoints;
    private String password;

    public SubscriberView() {
    }

    private boolean subscriberStatus;

    public SubscriberView(String subscriberId, SubscriberName subscriberName, Address address, ContactDetails contactDetails, LocalDate creationDate, NetWorthSubscriberStatus status, List<String> couponCodes, int rewardPoints) {
        this.subscriberId = subscriberId;
        this.subscriberName = subscriberName;
        this.address = address;
        this.contactDetails = contactDetails;
        this.creationDate=creationDate;
        this.status = status;
        this.couponCodes = couponCodes;
        this.rewardPoints = rewardPoints;
        this.subscriberStatus=true;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public SubscriberName getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(SubscriberName subscriberName) {
        this.subscriberName = subscriberName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public NetWorthSubscriberStatus getStatus() {
        return status;
    }

    public void setStatus(NetWorthSubscriberStatus status) {
        this.status = status;
    }

    public List<String> getCouponCodes() {
        return couponCodes;
    }

    public void setCouponCodes(List<String> couponCodes) {
        this.couponCodes = couponCodes;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSubscriberStatus() {
        return subscriberStatus;
    }

    public void setSubscriberStatus(boolean subscriberStatus) {
        this.subscriberStatus = subscriberStatus;
    }
}
