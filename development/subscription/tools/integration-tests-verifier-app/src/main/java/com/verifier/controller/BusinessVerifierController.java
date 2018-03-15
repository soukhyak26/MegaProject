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

    private final BusinessAccountViewRepository businessAccountViewRepository;
    private final BusinessAccountConfigurationViewRepository businessAccountConfigurationViewRepository;
    private final PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;
    private final PurchaseCostAccountTransactionsViewRepository purchaseCostAccountTransactionsViewRepository;
    private final BookingAmountAccountViewRepository bookingAmountAccountViewRepository;
    private final BookingAmountTransactionsViewRepository bookingAmountTransactionsViewRepository;
    private final BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository;
    private final BenefitAccountViewRepository benefitAccountViewRepository;
    private final BenefitAccountTransactionsViewRepository benefitAccountTransactionsViewRepository;
    private final CommonExpenseAccountViewRepository commonExpenseAccountViewRepository;
    private final CommonExpensesTransactionsViewRepository commonExpensesTransactionsViewRepository;
    private final VariableExpenseAccountViewRepository variableExpenseAccountViewRepository;
    private final VariableExpenseAccountTransactionsViewRepository variableExpenseAccountTransactionsViewRepository;
    private final ProductViewRepository productViewRepository;
    private final ProfitAccountViewRepository profitAccountViewRepository;
    private final ProfitAccountTransactionsViewRepository profitAccountTransactionsViewRepository;
    private final RevenueAccountViewRepository revenueAccountViewRepository;
    private final RevenueAccountTransactionsViewRepository revenueAccountTransactionsViewRepository;
    private final SubscriptionInfoViewRepository subscriptionInfoViewRepository;
    private final TaxesAccountViewRepository taxesAccountViewRepository;
    private final TaxesAccountTransactionsViewRepository taxesAccountTransactionsViewRepository;

    @Autowired
    public BusinessVerifierController(BusinessAccountViewRepository businessAccountViewRepository, BusinessAccountConfigurationViewRepository businessAccountConfigurationViewRepository, PurchaseCostAccountViewRepository purchaseCostAccountViewRepository, PurchaseCostAccountTransactionsViewRepository purchaseCostAccountTransactionsViewRepository, BookingAmountAccountViewRepository bookingAmountAccountViewRepository, BookingAmountTransactionsViewRepository bookingAmountTransactionsViewRepository, BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository, BenefitAccountViewRepository benefitAccountViewRepository, BenefitAccountTransactionsViewRepository benefitAccountTransactionsViewRepository, CommonExpenseAccountViewRepository commonExpenseAccountViewRepository, CommonExpensesTransactionsViewRepository commonExpensesTransactionsViewRepository, VariableExpenseAccountViewRepository variableExpenseAccountViewRepository, VariableExpenseAccountTransactionsViewRepository variableExpenseAccountTransactionsViewRepository, ProductViewRepository productViewRepository, ProfitAccountViewRepository profitAccountViewRepository, ProfitAccountTransactionsViewRepository profitAccountTransactionsViewRepository, RevenueAccountViewRepository revenueAccountViewRepository, RevenueAccountTransactionsViewRepository revenueAccountTransactionsViewRepository, SubscriptionInfoViewRepository subscriptionInfoViewRepository, TaxesAccountViewRepository taxesAccountViewRepository, TaxesAccountTransactionsViewRepository taxesAccountTransactionsViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
        this.businessAccountConfigurationViewRepository = businessAccountConfigurationViewRepository;
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
        this.purchaseCostAccountTransactionsViewRepository = purchaseCostAccountTransactionsViewRepository;
        this.bookingAmountAccountViewRepository = bookingAmountAccountViewRepository;
        this.bookingAmountTransactionsViewRepository = bookingAmountTransactionsViewRepository;
        this.budgetChangeRecommendationViewRepository = budgetChangeRecommendationViewRepository;
        this.benefitAccountViewRepository = benefitAccountViewRepository;
        this.benefitAccountTransactionsViewRepository = benefitAccountTransactionsViewRepository;
        this.commonExpenseAccountViewRepository = commonExpenseAccountViewRepository;
        this.commonExpensesTransactionsViewRepository = commonExpensesTransactionsViewRepository;
        this.variableExpenseAccountViewRepository = variableExpenseAccountViewRepository;
        this.variableExpenseAccountTransactionsViewRepository = variableExpenseAccountTransactionsViewRepository;
        this.productViewRepository = productViewRepository;
        this.profitAccountViewRepository = profitAccountViewRepository;
        this.profitAccountTransactionsViewRepository = profitAccountTransactionsViewRepository;
        this.revenueAccountViewRepository = revenueAccountViewRepository;
        this.revenueAccountTransactionsViewRepository = revenueAccountTransactionsViewRepository;
        this.subscriptionInfoViewRepository = subscriptionInfoViewRepository;
        this.taxesAccountViewRepository = taxesAccountViewRepository;
        this.taxesAccountTransactionsViewRepository = taxesAccountTransactionsViewRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount")
    ResponseEntity<BusinessAccountView> getBusinessAccount(){
        BusinessAccountView businessAccountView=null;
        List<BusinessAccountView> views = businessAccountViewRepository.findByEndDateAfter(SysDate.now());
        if(null != views && !views.isEmpty()) {
            businessAccountView =views.get(0);
        }
        return new ResponseEntity<BusinessAccountView>(businessAccountView,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount/configure")
    ResponseEntity<BusinessAccountConfigurationView> getBusinessAccountConfiguration(){
        Iterable<BusinessAccountConfigurationView> configurations = businessAccountConfigurationViewRepository.findAll();
        return new ResponseEntity<BusinessAccountConfigurationView>(configurations.iterator().next(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount/{id}")
    ResponseEntity<PurchaseCostAccountView> getPurchaseAccount(@PathVariable String id){
        PurchaseCostAccountView purchaseCostAccountView= purchaseCostAccountViewRepository.findOne(id);
        return new ResponseEntity<PurchaseCostAccountView>(purchaseCostAccountView,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<PurchaseCostAccountTransactionsView>> getPurchaseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        List<PurchaseCostAccountTransactionsView> purchaseCostAccountTransactionsViews = purchaseCostAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction);
        return new ResponseEntity<List<PurchaseCostAccountTransactionsView>>(purchaseCostAccountTransactionsViews,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookingaccount/{id}")
    ResponseEntity<BookingAmountAccountView> getBookingAccount(@PathVariable String id){
        return new ResponseEntity<BookingAmountAccountView>(bookingAmountAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookingaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<BookingAmountTransactionsView>> getBookingAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<BookingAmountTransactionsView>>(bookingAmountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "budgetchange/recommend/{recommendationDate}")
    ResponseEntity<List<BudgetChangeRecommendationView>> getBudgetChangeRecommendation(@PathVariable LocalDate recommendationDate){
        return new ResponseEntity<List<BudgetChangeRecommendationView>>(budgetChangeRecommendationViewRepository.findByRecommendationDate(recommendationDate),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount/{id}")
    ResponseEntity<BenefitAccountView> getBenefitAccount(@PathVariable String id){
        return new ResponseEntity<BenefitAccountView>(benefitAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<BenefitsAccountTransactionsView>> getBenefitAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<BenefitsAccountTransactionsView>>(benefitAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense/{id}")
    ResponseEntity<CommonExpenseAccountView> getCommonOpExpeseAccount(@PathVariable String id) {
        return new ResponseEntity<CommonExpenseAccountView>(commonExpenseAccountViewRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense/transaction/{dateOfTransaction}")
    ResponseEntity<List<CommonExpensesTransactionsView>> getCommonOpExpeseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<CommonExpensesTransactionsView>>(commonExpensesTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense/{id}")
    ResponseEntity<VariableExpenseAccountView> getVariableExpenseAccount(@PathVariable String id){
        return new ResponseEntity<VariableExpenseAccountView>(variableExpenseAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense/transaction/{dateOfTransaction}")
    ResponseEntity<List<VariableExpenseAccountTransactionsView>> getVariableExpenseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<VariableExpenseAccountTransactionsView>>(variableExpenseAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "product/{id}")
    ResponseEntity<ProductView> getProductView(@PathVariable String id){
        return new ResponseEntity<ProductView>(productViewRepository.findByProductId(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/{id}")
    ResponseEntity<SubscriptionInfoView> getSubscriptionInfoView(@PathVariable String id){
        return new ResponseEntity<SubscriptionInfoView>(subscriptionInfoViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "profitaccount/{id}")
    ResponseEntity<ProfitAccountView> getProfitAccountView(@PathVariable String id){
        return new ResponseEntity<ProfitAccountView>(profitAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "profitaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<ProfitAccountTransactionView>> getProfitAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<ProfitAccountTransactionView>>(profitAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "revenueaccount/{id}")
    ResponseEntity<RevenueAccountView> getRevenueAccount(@PathVariable String id){
        return new ResponseEntity<RevenueAccountView>(revenueAccountViewRepository.findOne(id),HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "revenueaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<RevenueAccountTransactionsView>> getRevenueAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<RevenueAccountTransactionsView>>(revenueAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/{id}")
    ResponseEntity<TaxesAccountView> getTaxesAccount(@PathVariable String id){
        return new ResponseEntity<TaxesAccountView>(taxesAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<TaxesAccountTransactionsView>> getTaxesAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<TaxesAccountTransactionsView>>(taxesAccountTransactionsViewRepository.findByDateOfTransaction(dateOfTransaction),HttpStatus.OK);
    }

}
