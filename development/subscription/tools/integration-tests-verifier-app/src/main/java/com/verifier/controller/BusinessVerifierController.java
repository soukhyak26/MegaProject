package com.verifier.controller;


import com.affaince.subscription.date.SysDate;
import com.verifier.domains.business.repository.*;
import com.verifier.domains.business.view.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "business")
public class BusinessVerifierController {

    private final BusinessBusinessAccountViewRepository businessBusinessAccountViewRepository;
    private final BusinessBusinessAccountConfigurationViewRepository businessBusinessAccountConfigurationViewRepository;
    private final BusinessPurchaseCostAccountViewRepository businessPurchaseCostAccountViewRepository;
    private final BusinessPurchaseCostAccountTransactionsViewRepository businessPurchaseCostAccountTransactionsViewRepository;
    private final BusinessBookingAmountAccountViewRepository businessBookingAmountAccountViewRepository;
    private final BusinessBookingAmountTransactionsViewRepository businessBookingAmountTransactionsViewRepository;
    private final BusinessBudgetChangeRecommendationViewRepository businessBudgetChangeRecommendationViewRepository;
    private final BusinessBenefitAccountViewRepository businessBenefitAccountViewRepository;
    private final BusinessBenefitAccountTransactionsViewRepository businessBenefitAccountTransactionsViewRepository;
    private final BusinessCommonExpenseAccountViewRepository businessCommonExpenseAccountViewRepository;
    private final BusinessCommonExpensesTransactionsViewRepository businessCommonExpensesTransactionsViewRepository;
    private final BusinessVariableExpenseAccountViewRepository businessVariableExpenseAccountViewRepository;
    private final BusinessVariableExpenseAccountTransactionsViewRepository businessVariableExpenseAccountTransactionsViewRepository;
    private final BusinessProductViewRepository businessProductViewRepository;
    private final BusinessProfitAccountViewRepository businessProfitAccountViewRepository;
    private final BusinessProfitAccountTransactionsViewRepository businessProfitAccountTransactionsViewRepository;
    private final BusinessRevenueAccountViewRepository businessRevenueAccountViewRepository;
    private final BusinessRevenueAccountTransactionsViewRepository businessRevenueAccountTransactionsViewRepository;
    private final BusinessSubscriptionInfoViewRepository businessSubscriptionInfoViewRepository;
    private final BusinessTaxesAccountViewRepository businessTaxesAccountViewRepository;
    private final BusinessTaxesAccountTransactionsViewRepository businessTaxesAccountTransactionsViewRepository;

    @Autowired
    public BusinessVerifierController(BusinessBusinessAccountViewRepository businessBusinessAccountViewRepository, BusinessBusinessAccountConfigurationViewRepository businessBusinessAccountConfigurationViewRepository, BusinessPurchaseCostAccountViewRepository businessPurchaseCostAccountViewRepository, BusinessPurchaseCostAccountTransactionsViewRepository businessPurchaseCostAccountTransactionsViewRepository, BusinessBookingAmountAccountViewRepository businessBookingAmountAccountViewRepository, BusinessBookingAmountTransactionsViewRepository businessBookingAmountTransactionsViewRepository, BusinessBudgetChangeRecommendationViewRepository businessBudgetChangeRecommendationViewRepository, BusinessBenefitAccountViewRepository businessBenefitAccountViewRepository, BusinessBenefitAccountTransactionsViewRepository businessBenefitAccountTransactionsViewRepository, BusinessCommonExpenseAccountViewRepository businessCommonExpenseAccountViewRepository, BusinessCommonExpensesTransactionsViewRepository businessCommonExpensesTransactionsViewRepository, BusinessVariableExpenseAccountViewRepository businessVariableExpenseAccountViewRepository, BusinessVariableExpenseAccountTransactionsViewRepository businessVariableExpenseAccountTransactionsViewRepository, BusinessProductViewRepository businessProductViewRepository, BusinessProfitAccountViewRepository businessProfitAccountViewRepository, BusinessProfitAccountTransactionsViewRepository businessProfitAccountTransactionsViewRepository, BusinessRevenueAccountViewRepository businessRevenueAccountViewRepository, BusinessRevenueAccountTransactionsViewRepository businessRevenueAccountTransactionsViewRepository, BusinessSubscriptionInfoViewRepository businessSubscriptionInfoViewRepository, BusinessTaxesAccountViewRepository businessTaxesAccountViewRepository, BusinessTaxesAccountTransactionsViewRepository businessTaxesAccountTransactionsViewRepository) {
        this.businessBusinessAccountViewRepository = businessBusinessAccountViewRepository;
        this.businessBusinessAccountConfigurationViewRepository = businessBusinessAccountConfigurationViewRepository;
        this.businessPurchaseCostAccountViewRepository = businessPurchaseCostAccountViewRepository;
        this.businessPurchaseCostAccountTransactionsViewRepository = businessPurchaseCostAccountTransactionsViewRepository;
        this.businessBookingAmountAccountViewRepository = businessBookingAmountAccountViewRepository;
        this.businessBookingAmountTransactionsViewRepository = businessBookingAmountTransactionsViewRepository;
        this.businessBudgetChangeRecommendationViewRepository = businessBudgetChangeRecommendationViewRepository;
        this.businessBenefitAccountViewRepository = businessBenefitAccountViewRepository;
        this.businessBenefitAccountTransactionsViewRepository = businessBenefitAccountTransactionsViewRepository;
        this.businessCommonExpenseAccountViewRepository = businessCommonExpenseAccountViewRepository;
        this.businessCommonExpensesTransactionsViewRepository = businessCommonExpensesTransactionsViewRepository;
        this.businessVariableExpenseAccountViewRepository = businessVariableExpenseAccountViewRepository;
        this.businessVariableExpenseAccountTransactionsViewRepository = businessVariableExpenseAccountTransactionsViewRepository;
        this.businessProductViewRepository = businessProductViewRepository;
        this.businessProfitAccountViewRepository = businessProfitAccountViewRepository;
        this.businessProfitAccountTransactionsViewRepository = businessProfitAccountTransactionsViewRepository;
        this.businessRevenueAccountViewRepository = businessRevenueAccountViewRepository;
        this.businessRevenueAccountTransactionsViewRepository = businessRevenueAccountTransactionsViewRepository;
        this.businessSubscriptionInfoViewRepository = businessSubscriptionInfoViewRepository;
        this.businessTaxesAccountViewRepository = businessTaxesAccountViewRepository;
        this.businessTaxesAccountTransactionsViewRepository = businessTaxesAccountTransactionsViewRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount")
    ResponseEntity<BusinessAccountView> getBusinessAccount(){
        BusinessAccountView businessAccountView=null;
        List<BusinessAccountView> views = businessBusinessAccountViewRepository.findByEndDateAfter(SysDate.now());
        if(null != views && !views.isEmpty()) {
            businessAccountView =views.get(0);
        }
        return new ResponseEntity<BusinessAccountView>(businessAccountView,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount/configure")
    ResponseEntity<BusinessAccountConfigurationView> getBusinessAccountConfiguration(){
        List<BusinessAccountConfigurationView> configurations = businessBusinessAccountConfigurationViewRepository.findAll();
        return new ResponseEntity<BusinessAccountConfigurationView>(configurations.get(0), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount/{id}")
    ResponseEntity<PurchaseCostAccountView> getPurchaseAccount(@PathVariable String id){
        PurchaseCostAccountView purchaseCostAccountView= businessPurchaseCostAccountViewRepository.findOne(id);
        return new ResponseEntity<PurchaseCostAccountView>(purchaseCostAccountView,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<PurchaseCostAccountTransactionsView>> getPurchaseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        List<PurchaseCostAccountTransactionsView> purchaseCostAccountTransactionsViews = businessPurchaseCostAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction);
        return new ResponseEntity<List<PurchaseCostAccountTransactionsView>>(purchaseCostAccountTransactionsViews,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookingaccount/{id}")
    ResponseEntity<BookingAmountAccountView> getBookingAccount(@PathVariable String id){
        return new ResponseEntity<BookingAmountAccountView>(businessBookingAmountAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookingaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<BookingAmountTransactionsView>> getBookingAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<BookingAmountTransactionsView>>(businessBookingAmountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "budgetchange/recommend/{recommendationDate}")
    ResponseEntity<List<BudgetChangeRecommendationView>> getBudgetChangeRecommendation(@PathVariable LocalDate recommendationDate){
        return new ResponseEntity<List<BudgetChangeRecommendationView>>(businessBudgetChangeRecommendationViewRepository.findByRecommendationDate(recommendationDate),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount/{id}")
    ResponseEntity<BenefitAccountView> getBenefitAccount(@PathVariable String id){
        return new ResponseEntity<BenefitAccountView>(businessBenefitAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<BenefitsAccountTransactionsView>> getBenefitAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<BenefitsAccountTransactionsView>>(businessBenefitAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense/{id}")
    ResponseEntity<CommonExpenseAccountView> getCommonOpExpeseAccount(@PathVariable String id) {
        return new ResponseEntity<CommonExpenseAccountView>(businessCommonExpenseAccountViewRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense/transaction/{dateOfTransaction}")
    ResponseEntity<List<CommonExpensesTransactionsView>> getCommonOpExpeseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<CommonExpensesTransactionsView>>(businessCommonExpensesTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense/{id}")
    ResponseEntity<VariableExpenseAccountView> getVariableExpenseAccount(@PathVariable String id){
        return new ResponseEntity<VariableExpenseAccountView>(businessVariableExpenseAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense/transaction/{dateOfTransaction}")
    ResponseEntity<List<VariableExpenseAccountTransactionsView>> getVariableExpenseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<VariableExpenseAccountTransactionsView>>(businessVariableExpenseAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "product/{id}")
    ResponseEntity<ProductView> getProductView(@PathVariable String id){
        return new ResponseEntity<ProductView>(businessProductViewRepository.findByProductId(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/{id}")
    ResponseEntity<SubscriptionInfoView> getSubscriptionInfoView(@PathVariable String id){
        return new ResponseEntity<SubscriptionInfoView>(businessSubscriptionInfoViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "profitaccount/{id}")
    ResponseEntity<ProfitAccountView> getProfitAccountView(@PathVariable String id){
        return new ResponseEntity<ProfitAccountView>(businessProfitAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "profitaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<ProfitAccountTransactionView>> getProfitAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<ProfitAccountTransactionView>>(businessProfitAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "revenueaccount/{id}")
    ResponseEntity<RevenueAccountView> getRevenueAccount(@PathVariable String id){
        return new ResponseEntity<RevenueAccountView>(businessRevenueAccountViewRepository.findOne(id),HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "revenueaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<RevenueAccountTransactionsView>> getRevenueAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<RevenueAccountTransactionsView>>(businessRevenueAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/{id}")
    ResponseEntity<TaxesAccountView> getTaxesAccount(@PathVariable String id){
        return new ResponseEntity<TaxesAccountView>(businessTaxesAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<TaxesAccountTransactionsView>> getTaxesAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<TaxesAccountTransactionsView>>(businessTaxesAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

}