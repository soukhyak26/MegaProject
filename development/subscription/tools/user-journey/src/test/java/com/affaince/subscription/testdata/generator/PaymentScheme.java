package com.affaince.subscription.testdata.generator;

import ch.qos.logback.core.property.ResourceExistsPropertyDefiner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PaymentScheme {
    private String paymentSchemeId;

    public void setPaymentSchemeId(String paymentSchemeId) {
        this.paymentSchemeId = paymentSchemeId;
    }

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }
}
