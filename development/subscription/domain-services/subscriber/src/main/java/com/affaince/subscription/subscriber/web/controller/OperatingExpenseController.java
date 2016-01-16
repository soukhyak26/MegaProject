package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.AddCommonOperatingExpenseCommand;
import com.affaince.subscription.subscriber.vo.OperatingExpense;
import com.affaince.subscription.subscriber.web.request.CommonOperatingExpensesRequest;
import org.jgroups.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.Consumes;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@RestController
@RequestMapping(value = "operatingexpense")
public class OperatingExpenseController {

    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public OperatingExpenseController(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> setOperatingExpenses(@RequestBody @Valid CommonOperatingExpensesRequest request) throws Exception {
        for (OperatingExpense expense : request.getExpenses()) {
            final AddCommonOperatingExpenseCommand command = new AddCommonOperatingExpenseCommand(new UUID().randomUUID().toString(),
                    expense);
            try {
                commandGateway.executeAsync(command);
            } catch (Exception e) {
                throw e;
            }
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
