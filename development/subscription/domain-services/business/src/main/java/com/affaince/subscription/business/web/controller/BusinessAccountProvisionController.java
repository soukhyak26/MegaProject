package com.affaince.subscription.business.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.CreateProvisionCommand;
import com.affaince.subscription.business.provision.ProvisionIndex;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.web.request.ProvisionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anayonkar on 29/4/16.
 */
@RestController
@RequestMapping(value="businessacount")
public class BusinessAccountProvisionController {
    private final SubscriptionCommandGateway commandGateway;
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public BusinessAccountProvisionController(SubscriptionCommandGateway commandGateway,
                                              BusinessAccountViewRepository businessAccountViewRepository) {
        this.commandGateway = commandGateway;
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "setProvision")
    @Consumes("application/json")
    public ResponseEntity<Object> setProvision(@RequestBody @Valid ProvisionRequest request) throws Exception {
        List<Double> provisionList = createProvisionList(request);
        CreateProvisionCommand command = new CreateProvisionCommand(provisionList, request.getProvisionDate());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    private List<Double> createProvisionList(ProvisionRequest request) {
        List<Double> provisionList = new ArrayList<>();
        provisionList.add(ProvisionIndex.PURCHASE_COST.getIndex(), request.getProvisionForPurchaseCost());
        provisionList.add(ProvisionIndex.LOSSES.getIndex(), request.getProvisionForLosses());
        provisionList.add(ProvisionIndex.BENEFITS.getIndex(), request.getProvisionForBenefits());
        provisionList.add(ProvisionIndex.TAXES.getIndex(), request.getProvisionForTaxes());
        provisionList.add(ProvisionIndex.OTHERS.getIndex(), request.getProvisionForOthers());
        provisionList.add(ProvisionIndex.COMMON_EXPENSES.getIndex(), request.getProvisionForCommonExpenses());
        provisionList.add(ProvisionIndex.NODAL_ACCOUNT.getIndex(), request.getProvisionForNodalAccount());
        provisionList.add(ProvisionIndex.REVENUE.getIndex(), request.getProvisionForRevenue());
        provisionList.add(ProvisionIndex.BENEFITS.getIndex(), request.getProvisionForBookingAmount());
        provisionList.add(ProvisionIndex.SUBSCRIPTION_SPECIFIC_EXPENSES.getIndex(), request.getProvisionForSubscriptionSpecificExpenses());
        return provisionList;
    }
}
