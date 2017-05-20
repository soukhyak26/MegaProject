package com.affaince.subscription.payments.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CreatePaymentSchemeCommand;
import com.affaince.subscription.payments.web.request.PaymentSchemeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController(value = "paymentscheme")
public class PaymentSchemeController {

    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public PaymentSchemeController(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createPaymentScheme(@RequestBody PaymentSchemeRequest request) throws Exception {
        final CreatePaymentSchemeCommand command = new CreatePaymentSchemeCommand(UUID.randomUUID().toString(),
                request.getPaymentSchemeName(), request.getPaymentSchemeDescription(), request.getPaymentSchemeRule(), request.getStartDate(), request.getEndDate());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
