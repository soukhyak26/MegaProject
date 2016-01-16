package com.affaince.subscription.integration.command.event.paymentreceipt;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class PaymentReceivedEvent {
    @DataField(name = "SUBSCRIBER_ID", pos = 1, trim = true)
    private String susbcriberId;

    @DataField(name = "AMOUNT", pos = 2, trim = true)
    private double paidAmount;

    @DataField(name = "PAY_DATE", pos = 3, trim = true)
    private LocalDate paymentDate;

    public PaymentReceivedEvent() {
    }

    public String getSusbcriberId() {
        return susbcriberId;
    }

    public void setSusbcriberId(String susbcriberId) {
        this.susbcriberId = susbcriberId;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentReceivedEvent{" +
                "susbcriberId='" + susbcriberId + '\'' +
                ", paidAmount=" + paidAmount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}


