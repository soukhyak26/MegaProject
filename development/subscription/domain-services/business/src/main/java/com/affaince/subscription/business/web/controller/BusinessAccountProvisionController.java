package com.affaince.subscription.business.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.AddCommonOperatingExpenseCommand;
import com.affaince.subscription.business.command.CreateProvisionCommand;
import com.affaince.subscription.business.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.business.command.domain.MonthlyCommonOperatingExpense;
import com.affaince.subscription.business.command.event.FixedExpenseUpdatedToProductEvent;
import com.affaince.subscription.business.process.operatingexpenses.DefaultOperatingExpensesDeterminator;
import com.affaince.subscription.business.process.operatingexpenses.OperatingExpensesDeterminator;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.vo.OperatingExpenseVO;
import com.affaince.subscription.business.web.request.CommonOperatingExpensesRequest;
import com.affaince.subscription.business.web.request.ProvisionRequest;
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
import java.util.Map;

/**
 * Created by anayonkar on 29/4/16.
 */
@RestController
@RequestMapping(value="businessacount")
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

    @RequestMapping(method = RequestMethod.POST, value = "setProvision")
    @Consumes("application/json")
    public ResponseEntity<Object> setProvision(@RequestBody @Valid ProvisionRequest request) throws Exception {
        CreateProvisionCommand command = new CreateProvisionCommand(
                request.getProvisionDate().getYear(),
                request.getProvisionForPurchaseCost(),
                request.getProvisionForLosses(),
                request.getProvisionForBenefits(),
                request.getProvisionForTaxes(),
                request.getProvisionForOthers(),
                request.getProvisionForCommonExpenses(),
                request.getProvisionForSubscriptionSpecificExpenses(),
                request.getDefaultPercentFixedExpensePerUnitPrice(),
                request.getDefaultPercentVariableExpensePerUnitPrice(),
                request.getProvisionDate());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "common")
    @Consumes("application/json")
    public ResponseEntity<Object> setCommonOperatingExpenses(@RequestBody @Valid CommonOperatingExpensesRequest request) throws Exception {
        //String commonExpenseId = UUID.randomUUID().toString();
        double monthlyCommonExpenseAmount = 0;
        for (OperatingExpenseVO expense : request.getExpenses()) {
            if (expense.getPeriod().getUnit() == PeriodUnit.WEEK) {
                monthlyCommonExpenseAmount = (expense.getAmount() / expense.getPeriod().getValue()) * 4;
            } else if (expense.getPeriod().getUnit() == PeriodUnit.MONTH) {
                monthlyCommonExpenseAmount = (expense.getAmount() / expense.getPeriod().getValue());
            } else if (expense.getPeriod().getUnit() == PeriodUnit.YEAR) {
                monthlyCommonExpenseAmount = expense.getAmount() / (expense.getPeriod().getValue() * 12);
            }
            String commonExpensePrefix = "COMMON_EXP_";
            YearMonth monthOfYear= YearMonth.now();
            for (int i = 0; i <= 11; i++) {
                monthOfYear = monthOfYear.plusMonths(i);
                final String commonExpenseId=commonExpensePrefix + monthOfYear.toString();
                //final AddCommonOperatingExpenseCommand command = new AddCommonOperatingExpenseCommand(commonExpenseId, monthOfYear,expense.getExpenseHeader(), monthlyCommonExpenseAmount, expense.getSensitivityCharacteristic());
                OperatingExpensesDeterminator operatingExpensesDeterminator =
                        new DefaultOperatingExpensesDeterminator();
                MonthlyCommonOperatingExpense monthlyCommonOperatingExpense= new MonthlyCommonOperatingExpense(commonExpenseId, monthOfYear,expense.getExpenseHeader(), monthlyCommonExpenseAmount, expense.getSensitivityCharacteristic());
                final Map<String, Double> perUnitOperatingExpenses = operatingExpensesDeterminator.calculateOperatingExpensesPerProduct(monthlyCommonOperatingExpense);
                perUnitOperatingExpenses.forEach((productId, perUnitExpense) ->{
                    UpdateFixedExpenseToProductCommand command = new UpdateFixedExpenseToProductCommand(productId, SysDate.now(), perUnitExpense);
                    try {
                        commandGateway.executeAsync(command);
                    }catch(Exception ex){
                        LOGGER.error("UpdateFixedExpenseToProductCommand could not be executed :" + ex.getMessage());
                    }

                });
            }
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
