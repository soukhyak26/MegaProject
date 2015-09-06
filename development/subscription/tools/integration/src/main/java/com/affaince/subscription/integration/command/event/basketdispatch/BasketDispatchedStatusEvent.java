package com.affaince.subscription.integration.command.event.basketdispatch;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.util.Date;

/**
 * Created by mandark on 21-08-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class BasketDispatchedStatusEvent {
    @DataField(name = "BASKET_ID", pos = 1, trim = true)
    private String basketId;


    @DataField(name = "DISPATCH_DATE", pos = 2, pattern = "mmddyyyy", trim = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private Date dispatchDate;

    @DataField(name = "DISPATCH_STATUS", pos = 3, trim = true)
    private int dispactchStatusCode;
    @DataField(name = "REASON_CODE", pos = 4, trim = true)
    private int reasonCode;

    public BasketDispatchedStatusEvent() {
    }

    public BasketDispatchedStatusEvent(String basketId, Date dispatchDate, int dispactchStatusCode, int reasonCode) {
        this.basketId = basketId;
        this.dispatchDate = dispatchDate;
        this.dispactchStatusCode = dispactchStatusCode;
        this.reasonCode = reasonCode;
    }

    public String getBasketId() {
        return this.basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public Date getDispatchDate() {
        return this.dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public int getDispactchStatusCode() {
        return this.dispactchStatusCode;
    }

    public void setDispactchStatusCode(int dispactchStatusCode) {
        this.dispactchStatusCode = dispactchStatusCode;
    }

    public int getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }
}
