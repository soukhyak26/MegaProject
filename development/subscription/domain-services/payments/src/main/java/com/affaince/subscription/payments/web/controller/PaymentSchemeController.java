package com.affaince.subscription.payments.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.CreatePaymentSchemeCommand;
import com.affaince.subscription.payments.command.ExpirePaymentSchemeCommand;
import com.affaince.subscription.payments.command.ModifyPaymentSchemeCommand;
import com.affaince.subscription.payments.web.request.CreatePaymentSchemeRequest;
import com.affaince.subscription.payments.web.request.ModifyPaymentSchemeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController(value = "/paymentscheme")
public class PaymentSchemeController {

    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public PaymentSchemeController(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value= "/create",  method = RequestMethod.POST)
    public ResponseEntity<Object> createPaymentScheme(@RequestBody CreatePaymentSchemeRequest request) throws Exception {
        final CreatePaymentSchemeCommand command = new CreatePaymentSchemeCommand(UUID.randomUUID().toString(),
                request.getPaymentSchemeName(), request.getPaymentSchemeDescription(), request.getPaymentSchemeRule(), request.getStartDate(), request.getEndDate());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @RequestMapping(value= "/expire/{schemeId}",  method = RequestMethod.POST)
    public ResponseEntity<Object> expirePaymentScheme(String schemeId) throws Exception {
        final ExpirePaymentSchemeCommand command= new ExpirePaymentSchemeCommand(schemeId, SysDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
