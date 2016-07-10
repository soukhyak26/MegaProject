package com.affaince.notification.events;

import com.affaince.subscription.mail.ftl.PaymentProcessedTemplateGenerator;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by rbsavaliya on 16-01-2016.
 */

public class PaymentProcessedEvent extends NotificationEvent {
    private String subscriptionId;
    private String subscriberId;
    private double paymentAmount;
    private LocalDate paymentDate;

    public PaymentProcessedEvent(String subscriptionId, String subscriberId, double paymentAmount, LocalDate paymentDate) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.receiver = subscriberId + "@email.com";
        this.subject = "Payment Processed";
    }

    @Autowired
    public PaymentProcessedEvent() {
        System.out.println("\n\t\t\t\t******************************\n\t\t\t\t"
                + "PaymentProcessedEvent Constructor Called"
                + "\n\t\t\t\t******************************\n");
    }

    public void sampleMethod() {
        System.out.println("\n\t\t\t\t******************************\n\t\t\t\t"
                + "SampleMethod Called"
                + "\n\t\t\t\t******************************\n");
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentProcessedEvent{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate=" + paymentDate +
                '}';
    }

    @Override
    public String getEmailMessage() {
        return PaymentProcessedTemplateGenerator.generateEmailMessage(subscriberId, paymentAmount, paymentDate);
    }
}
