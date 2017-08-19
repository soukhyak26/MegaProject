package com.affaince.subscription.payments.simulator.controller;

import com.affaince.subscription.payments.simulator.request.PaymentSimulatorRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "payment")
public class PaymentSimulatorController {

    @RequestMapping(method = RequestMethod.POST, value = "/simulate")
    public String simulatePaymentInstallments (@RequestBody PaymentSimulatorRequest request) {
        return request.toString();
    }
}