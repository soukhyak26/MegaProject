package com.verifier.controller;


import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.date.SysDate;
import com.verifier.domains.business.repository.*;
import com.verifier.domains.business.view.*;
import org.apache.tomcat.jni.Local;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
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
    private final BusinessDeliveryForecastViewRepository businessDeliveryForecastViewRepository;
    private final BusinessSubscriptionForecastViewRepository businessSubscriptionForecastViewRepository;
    private final BusinessVariableExpenseAccountViewRepository businessVariableExpenseAccountViewRepository;
    private final BusinessVariableExpenseAccountTransactionsViewRepository businessVariableExpenseAccountTransactionsViewRepository;
    private final BusinessProductViewRepository businessProductViewRepository;
    private final BusinessProductForecastViewRepository businessProductForecastViewRepository;
    private final BusinessOthersAccountViewRepository businessOthersAccountViewRepository;
    private final BusinessOthersAccountTransactionsViewRepository businessOthersAccountTransactionsViewRepository;
    private final BusinessProfitAccountViewRepository businessProfitAccountViewRepository;
    private final BusinessProfitAccountTransactionsViewRepository businessProfitAccountTransactionsViewRepository;
    private final BusinessRevenueAccountViewRepository businessRevenueAccountViewRepository;
    private final BusinessRevenueAccountTransactionsViewRepository businessRevenueAccountTransactionsViewRepository;
    private final BusinessSubscriptionInfoViewRepository businessSubscriptionInfoViewRepository;
    private final BusinessTaxesAccountViewRepository businessTaxesAccountViewRepository;
    private final BusinessTaxesAccountTransactionsViewRepository businessTaxesAccountTransactionsViewRepository;
    private final BusinessBenefitsProvisionCalendarViewRepository businessBenefitsProvisionCalendarViewRepository;
    private final BusinessCommonExpensesProvisionCalendarViewRepository businessCommonExpensesProvisionCalendarViewRepository;
    private final BusinessPurchaseCostProvisionCalendarViewRepository businessPurchaseCostProvisionCalendarViewRepository;
    private final BusinessTaxesProvisionCalendarViewRepository businessTaxesProvisionCalendarViewRepository;
    private final BusinessVariableExpensesProvisionCalendarViewRepository businessVariableExpensesProvisionCalendarViewRepository;

    @Autowired
    public BusinessVerifierController(BusinessBusinessAccountViewRepository businessBusinessAccountViewRepository, BusinessBusinessAccountConfigurationViewRepository businessBusinessAccountConfigurationViewRepository, BusinessPurchaseCostAccountViewRepository businessPurchaseCostAccountViewRepository, BusinessPurchaseCostAccountTransactionsViewRepository businessPurchaseCostAccountTransactionsViewRepository, BusinessBookingAmountAccountViewRepository businessBookingAmountAccountViewRepository, BusinessBookingAmountTransactionsViewRepository businessBookingAmountTransactionsViewRepository, BusinessBudgetChangeRecommendationViewRepository businessBudgetChangeRecommendationViewRepository, BusinessBenefitAccountViewRepository businessBenefitAccountViewRepository, BusinessBenefitAccountTransactionsViewRepository businessBenefitAccountTransactionsViewRepository, BusinessCommonExpenseAccountViewRepository businessCommonExpenseAccountViewRepository, BusinessCommonExpensesTransactionsViewRepository businessCommonExpensesTransactionsViewRepository, BusinessVariableExpenseAccountViewRepository businessVariableExpenseAccountViewRepository, BusinessVariableExpenseAccountTransactionsViewRepository businessVariableExpenseAccountTransactionsViewRepository, BusinessProductViewRepository businessProductViewRepository, BusinessProfitAccountViewRepository businessProfitAccountViewRepository, BusinessProfitAccountTransactionsViewRepository businessProfitAccountTransactionsViewRepository, BusinessRevenueAccountViewRepository businessRevenueAccountViewRepository, BusinessRevenueAccountTransactionsViewRepository businessRevenueAccountTransactionsViewRepository, BusinessSubscriptionInfoViewRepository businessSubscriptionInfoViewRepository, BusinessTaxesAccountViewRepository businessTaxesAccountViewRepository, BusinessTaxesAccountTransactionsViewRepository businessTaxesAccountTransactionsViewRepository,BusinessProductForecastViewRepository businessProductForecastViewRepository,BusinessDeliveryForecastViewRepository businessDeliveryForecastViewRepository,BusinessSubscriptionForecastViewRepository businessSubscriptionForecastViewRepository,BusinessOthersAccountViewRepository businessOthersAccountViewRepository,BusinessOthersAccountTransactionsViewRepository businessOthersAccountTransactionsViewRepository,BusinessBenefitsProvisionCalendarViewRepository businessBenefitsProvisionCalendarViewRepository,BusinessCommonExpensesProvisionCalendarViewRepository businessCommonExpensesProvisionCalendarViewRepository,BusinessPurchaseCostProvisionCalendarViewRepository businessPurchaseCostProvisionCalendarViewRepository,BusinessTaxesProvisionCalendarViewRepository businessTaxesProvisionCalendarViewRepository,BusinessVariableExpensesProvisionCalendarViewRepository businessVariableExpensesProvisionCalendarViewRepository) {
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
        this.businessDeliveryForecastViewRepository = businessDeliveryForecastViewRepository;
        this.businessSubscriptionForecastViewRepository = businessSubscriptionForecastViewRepository;
        this.businessVariableExpenseAccountViewRepository = businessVariableExpenseAccountViewRepository;
        this.businessVariableExpenseAccountTransactionsViewRepository = businessVariableExpenseAccountTransactionsViewRepository;
        this.businessProductViewRepository = businessProductViewRepository;
        this.businessProductForecastViewRepository = businessProductForecastViewRepository;
        this.businessProfitAccountViewRepository = businessProfitAccountViewRepository;
        this.businessProfitAccountTransactionsViewRepository = businessProfitAccountTransactionsViewRepository;
        this.businessRevenueAccountViewRepository = businessRevenueAccountViewRepository;
        this.businessRevenueAccountTransactionsViewRepository = businessRevenueAccountTransactionsViewRepository;
        this.businessSubscriptionInfoViewRepository = businessSubscriptionInfoViewRepository;
        this.businessTaxesAccountViewRepository = businessTaxesAccountViewRepository;
        this.businessTaxesAccountTransactionsViewRepository = businessTaxesAccountTransactionsViewRepository;
        this.businessOthersAccountTransactionsViewRepository=businessOthersAccountTransactionsViewRepository;
        this.businessOthersAccountViewRepository = businessOthersAccountViewRepository;
        this.businessBenefitsProvisionCalendarViewRepository=businessBenefitsProvisionCalendarViewRepository;
        this.businessCommonExpensesProvisionCalendarViewRepository=businessCommonExpensesProvisionCalendarViewRepository;
        this.businessPurchaseCostProvisionCalendarViewRepository = businessPurchaseCostProvisionCalendarViewRepository;
        this.businessTaxesProvisionCalendarViewRepository = businessTaxesProvisionCalendarViewRepository;
        this.businessVariableExpensesProvisionCalendarViewRepository = businessVariableExpensesProvisionCalendarViewRepository;
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
        List<PurchaseCostAccountTransactionsView> purchaseCostAccountTransactionsViews = businessPurchaseCostAccountTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(org.joda.time.LocalTime.now()));
        return new ResponseEntity<List<PurchaseCostAccountTransactionsView>>(purchaseCostAccountTransactionsViews,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookingaccount/{id}")
    ResponseEntity<BookingAmountAccountView> getBookingAccount(@PathVariable String id){
        return new ResponseEntity<BookingAmountAccountView>(businessBookingAmountAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "bookingaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<BookingAmountTransactionsView>> getBookingAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<BookingAmountTransactionsView>>(businessBookingAmountTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(LocalTime.now())),HttpStatus.OK);
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
        return new ResponseEntity<List<BenefitsAccountTransactionsView>>(businessBenefitAccountTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(LocalTime.now())),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "benefitaccount/provision")
    ResponseEntity<List<BenefitsProvisionCalendarView>> getBenefitProvisionCalendar(){
        return new ResponseEntity<List<BenefitsProvisionCalendarView>>(businessBenefitsProvisionCalendarViewRepository.findAllByOrderByProvisionCalendarVersionId_StartDateAsc(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense/provision")
    ResponseEntity<List<CommonExpenseProvisionCalendarView>> getCommonExpenseProvisionCalendar(){
        return new ResponseEntity<List<CommonExpenseProvisionCalendarView>>(businessCommonExpensesProvisionCalendarViewRepository.findAllByOrderByProvisionCalendarVersionId_StartDateAsc(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "purchaseaccount/provision")
    ResponseEntity<List<PurchaseCostProvisionCalendarView>> getPurchaseAccountProvisionCalendar(){
        return new ResponseEntity<List<PurchaseCostProvisionCalendarView>>(businessPurchaseCostProvisionCalendarViewRepository.findAllByOrderByProvisionCalendarVersionId_StartDateAsc(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/provision")
    ResponseEntity<List<TaxesProvisionCalendarView>> getTaxesProvisionCalendar(){
        return new ResponseEntity<List<TaxesProvisionCalendarView>>(businessTaxesProvisionCalendarViewRepository.findAllByOrderByProvisionCalendarVersionId_StartDateAsc(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense/provision")
    ResponseEntity<List<VariableExpensesProvisionCalendarView>> getVariableExpenseProvisionCalendar(){
        return new ResponseEntity<List<VariableExpensesProvisionCalendarView>>(businessVariableExpensesProvisionCalendarViewRepository.findAllByOrderByProvisionCalendarVersionId_StartDateAsc(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense/{id}")
    ResponseEntity<CommonExpenseAccountView> getCommonOpExpeseAccount(@PathVariable String id) {
        return new ResponseEntity<CommonExpenseAccountView>(businessCommonExpenseAccountViewRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "commonexpense/transaction/{dateOfTransaction}")
    ResponseEntity<List<CommonExpensesTransactionsView>> getCommonOpExpeseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<CommonExpensesTransactionsView>>(businessCommonExpensesTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(LocalTime.now())),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense/{id}")
    ResponseEntity<VariableExpenseAccountView> getVariableExpenseAccount(@PathVariable String id){
        return new ResponseEntity<VariableExpenseAccountView>(businessVariableExpenseAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "variableexpense/transaction/{dateOfTransaction}")
    ResponseEntity<List<VariableExpenseAccountTransactionsView>> getVariableExpenseAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<VariableExpenseAccountTransactionsView>>(businessVariableExpenseAccountTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(LocalTime.now())),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "others/{id}")
    ResponseEntity<OthersAccountView> getOthersAccount(@PathVariable String id){
        return new ResponseEntity<OthersAccountView>(businessOthersAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "others/transaction/{dateOfTransaction}")
    ResponseEntity<List<OthersAccountTransactionsView>> getOthersAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<OthersAccountTransactionsView>>(businessOthersAccountTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(LocalTime.now())),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "product/{id}")
    ResponseEntity<ProductView> getProductView(@PathVariable String id){
        return new ResponseEntity<ProductView>(businessProductViewRepository.findByProductId(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "product/forecast/{id}")
    ResponseEntity<List<ProductForecastView>> getProductForecastViews(@PathVariable String id){
        return new ResponseEntity<List<ProductForecastView>>(businessProductForecastViewRepository.findByForecastVersionId_ProductIdAndForecastContentStatusOrderByEndDateAsc(id, ForecastContentStatus.ACTIVE),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "delivery/forecast/{minWeight}/{maxWeight}")
    ResponseEntity<List<DeliveryForecastView>> getDeliveryForecastViews(@PathVariable("minWeight") double minWeight,@PathVariable("maxWeight") double maxWeight ){
        List<DeliveryForecastView> forecasts = businessDeliveryForecastViewRepository.findByDeliveryForecastVersionId_WeightRangeMinAndDeliveryForecastVersionId_WeightRangeMaxAndForecastContentStatusOrderByDeliveryForecastVersionId_DeliveryDateAsc(minWeight,maxWeight,ForecastContentStatus.ACTIVE);
        return new ResponseEntity<List<DeliveryForecastView>>(forecasts,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "subscription/forecast/{valueRangeMin}/{valueRangeMax}")
    ResponseEntity<List<SubscriptionForecastView>> getSubscriptionForecastViews(@PathVariable("valueRangeMin") double valueRangeMin, @PathVariable("valueRangeMax") double valueRangeMax){
        List<SubscriptionForecastView> forecasts = businessSubscriptionForecastViewRepository.findByForecastContentStatusAndSubscriptionVersionId_ValueRangeMinAndSubscriptionVersionId_ValueRangeMaxOrderBySubscriptionVersionId_StartDate(ForecastContentStatus.ACTIVE,valueRangeMin,valueRangeMax);
        return new ResponseEntity<List<SubscriptionForecastView>>(forecasts,HttpStatus.OK);
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
        return new ResponseEntity<List<ProfitAccountTransactionView>>(businessProfitAccountTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(LocalTime.now())),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "revenueaccount/{id}")
    ResponseEntity<RevenueAccountView> getRevenueAccount(@PathVariable String id){
        return new ResponseEntity<RevenueAccountView>(businessRevenueAccountViewRepository.findOne(id),HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "revenueaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<RevenueAccountTransactionsView>> getRevenueAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<RevenueAccountTransactionsView>>(businessRevenueAccountTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(LocalTime.now())),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/{id}")
    ResponseEntity<TaxesAccountView> getTaxesAccount(@PathVariable String id){
        return new ResponseEntity<TaxesAccountView>(businessTaxesAccountViewRepository.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "taxesaccount/transaction/{dateOfTransaction}")
    ResponseEntity<List<TaxesAccountTransactionsView>> getTaxesAccountTransaction(@PathVariable LocalDate dateOfTransaction){
        return new ResponseEntity<List<TaxesAccountTransactionsView>>(businessTaxesAccountTransactionsViewRepository.findByTimeOfTransaction(dateOfTransaction.toLocalDateTime(LocalTime.now())),HttpStatus.OK);
    }

}
