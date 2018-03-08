package com.test.verifier.controller;


import com.test.verifier.domains.business.repository.*;
import com.test.verifier.domains.business.view.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "businessaccount/configure")
    ResponseEntity<BusinessAccountConfigurationView> getBusinessAccountConfiguration(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount")
    ResponseEntity<PurchaseCostAccountView> getPurchaseAccount(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount/transaction")
    ResponseEntity<PurchaseCostAccountTransactionsView> getPurchaseAccountTransaction(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookingaccount")
    ResponseEntity<BookingAmountAccountView> getBookingAccount(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookingaccount/transaction")
    ResponseEntity<BookingAmountTransactionsView> getBookingAccountTransaction(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "budgetchange/recommend")
    ResponseEntity<BudgetChangeRecommendationView> getBudgetChangeRecommendation(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount")
    ResponseEntity<BenefitAccountView> getBenefitAccount(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount/transaction")
    ResponseEntity<BenefitsAccountTransactionsView> getBenefitAccountTransaction(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense")
    ResponseEntity<CommonExpenseAccountView> getCommonOpExpeseAccount(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense/transaction")
    ResponseEntity<CommonExpensesTransactionsView> getCommonOpExpeseAccountTransaction(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense")
    ResponseEntity<VariableExpenseAccountView> getVariableExpenseAccount(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense/transaction")
    ResponseEntity<BusinessAccountView> getVariableExpenseAccountTransaction(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "product")
    ResponseEntity<ProductView> getProductView(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription")
    ResponseEntity<SubscriptionInfoView> getSubscriptionInfoView(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "profitaccount")
    ResponseEntity<ProfitAccountView> getProfitAccountView(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "profitaccount/transaction")
    ResponseEntity<ProfitAccountTransactionView> getProfitAccountTransaction(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "revenueaccount")
    ResponseEntity<RevenueAccountView> getRevenueAccount(){
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "revenueaccount/transaction")
    ResponseEntity<RevenueAccountTransactionsView> getRevenueAccountTransaction(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount")
    ResponseEntity<TaxesAccountView> getTaxesAccount(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/transaction")
    ResponseEntity<TaxesAccountTransactionsView> getTaxesAccountTransaction(){
        return null;
    }

}
