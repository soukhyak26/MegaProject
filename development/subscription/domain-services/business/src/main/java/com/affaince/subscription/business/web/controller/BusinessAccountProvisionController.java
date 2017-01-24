package com.affaince.subscription.business.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.*;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.vo.OperatingExpenseVO;
import com.affaince.subscription.business.web.request.*;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.date.SysDate;
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

@RestController
@RequestMapping(value = "businessacount")
public class BusinessAccountProvisionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessAccountProvisionController.class);
    private final SubscriptionCommandGateway commandGateway;
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public BusinessAccountProvisionController(SubscriptionCommandGateway commandGateway,
                                              BusinessAccountViewRepository businessAccountViewRepository) {
        this.commandGateway = commandGateway;
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "provisionForPurchase")
    @Consumes("application/json")
    public ResponseEntity<Object> setProvisionForPurchaseCost(@RequestBody PurchaseCostProvisionRequest request) throws Exception {
        Integer id= YearMonth.now().getYear();
        CreateProvisionForPurchaseCostCommand command = new CreateProvisionForPurchaseCostCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForPurchaseOfGoods());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "provisionForBenefits")
    @Consumes("application/json")

    public ResponseEntity<Object> setProvisionForBenefits (@RequestBody @Valid BenefitsProvisionRequest request) throws Exception {
        Integer id= YearMonth.now().getYear();
        CreateProvisionForBenefitsCommand command = new CreateProvisionForBenefitsCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForBenefits());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "provisionForLosses")
    @Consumes("application/json")
    public ResponseEntity<Object> setProvisionForLosses(@RequestBody @Valid LossesProvisionRequest request) throws Exception {
        Integer id= YearMonth.now().getYear();
        CreateProvisionForLossesCommand command = new CreateProvisionForLossesCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForLosses());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "provisionForNodal")
    @Consumes("application/json")

    public ResponseEntity<Object> setProvisionForNodal(@RequestBody @Valid NodalProvisionRequest request) throws Exception {
        Integer id= YearMonth.now().getYear();
        CreateProvisionForNodalCommand command = new CreateProvisionForNodalCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForNodal());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "provisionForOthers")
    @Consumes("application/json")

    public ResponseEntity<Object> setProvisionForOthers(@RequestBody @Valid OthersProvisionRequest request) throws Exception {
        Integer id= YearMonth.now().getYear();
        CreateProvisionForOthersCommand command = new CreateProvisionForOthersCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForOtherCost());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "provisionForVariableExpenses")
    @Consumes("application/json")

    public ResponseEntity<Object> setProvisionForSubscriptionSpecificExpenses(@RequestBody @Valid SubscriptionSpecificExpensesProvisionRequest request) throws Exception {
        Integer id= YearMonth.now().getYear();
        CreateProvisionForSubscriptionSpecificExpensesCommand command = new CreateProvisionForSubscriptionSpecificExpensesCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForSubscriptionSpecificExpenses());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.POST, value = "provisionForCommonExpenses")
    @Consumes("application/json")
    public ResponseEntity<Object> setProvisionForCommonOperatingExpenses(@RequestBody @Valid CommonOperatingExpensesRequest request) throws Exception {
        double monthlyCommonExpenseAmount = 0;
        double yearlyCommonExpenseAmount = 0;
        YearMonth monthOfYear = YearMonth.now();
        //need to verify accuracy of this number
        int remainingMonths = 12 - monthOfYear.getMonthOfYear() + 1;
        for (OperatingExpenseVO expense : request.getExpenses()) {
            if (expense.getPeriod().getUnit() == PeriodUnit.WEEK) {
                monthlyCommonExpenseAmount = (expense.getAmount() / expense.getPeriod().getValue()) * 4;
            } else if (expense.getPeriod().getUnit() == PeriodUnit.MONTH) {
                monthlyCommonExpenseAmount = (expense.getAmount() / expense.getPeriod().getValue());
            } else if (expense.getPeriod().getUnit() == PeriodUnit.YEAR) {
                monthlyCommonExpenseAmount = expense.getAmount() / (expense.getPeriod().getValue() * 12);
            }
            yearlyCommonExpenseAmount += monthlyCommonExpenseAmount * remainingMonths;
        }
        Integer id= YearMonth.now().getYear();
        CreateProvisionForCommonExpensesCommand command= new CreateProvisionForCommonExpensesCommand(id,SysDate.now(),monthOfYear.plusMonths(remainingMonths).toLocalDate(31),yearlyCommonExpenseAmount);
        try{
            commandGateway.executeAsync(command);
        }catch (Exception ex) {
            LOGGER.error("UpdateFixedExpenseToProductCommand could not be executed :" + ex.getMessage());
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "provisionForTaxes")
    @Consumes("application/json")

    public ResponseEntity<Object> setProvisionForTaxes(@RequestBody @Valid TaxesProvisionRequest request) throws Exception {
        Integer id= YearMonth.now().getYear();
        CreateProvisionForTaxesCommand command = new CreateProvisionForTaxesCommand(id,request.getStartDate(), request.getEndDate(), request.getProvisionForTaxes());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
