package com.affaince.subscription.business.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.AcceptRecommendationCommand;
import com.affaince.subscription.business.command.ConfigureBusinessAccountCommand;
import com.affaince.subscription.business.web.request.ConfigureBusinessAccountRequest;
import org.joda.time.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by mandar on 10/8/2017.
 */
@RestController
@RequestMapping(value = "businessacount/config")
public class BusinessAccountConfigurationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessAccountConfigurationController.class);
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public BusinessAccountConfigurationController(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST, value = "concludeRecommendations")
    @Consumes("application/json")
    public ResponseEntity<Object> configureBusinessAccount(@RequestBody @Valid ConfigureBusinessAccountRequest request) throws Exception {
        Integer id = YearMonth.now().getYear();
        ConfigureBusinessAccountCommand command = new ConfigureBusinessAccountCommand(id, request.getBudgetAdjustmentOptions());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
