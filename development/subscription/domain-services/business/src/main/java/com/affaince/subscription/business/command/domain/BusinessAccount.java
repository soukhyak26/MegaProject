package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.accounting.Account;
import com.affaince.subscription.business.accounting.AccountType;
import com.affaince.subscription.business.command.event.*;
import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.TimeBoundMoney;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class BusinessAccount extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String id;
    private TimeBoundMoney commonOperationExpensesDemand;
    private TimeBoundMoney subscriptionSpecificOperatingExpensesDemand;
    private TimeBoundMoney salesAndMarketingExpensesDemand;
    private TimeBoundMoney goodsPurchaseCostDemand;

    private TimeBoundMoney commonOperationExpensesReserve;
    private TimeBoundMoney subscriptionSpecificOperatingExpensesReserve;
    private TimeBoundMoney salesAndMarketingExpensesReserve;
    private TimeBoundMoney goodsPurchaseCostReserve;

    private TimeBoundMoney netProfitRegistered;
    private TimeBoundMoney totalRevenueRegistered;
    private long totalSubscriptionsRegistered;

    //EXPENSES
    private Account purchaseCostAccount;
    private Account lossesAccount;
    private Account benefitsAccount;
    private Account taxesAccount;
    private Account othersAccount;
    private Account commonExpensesAccount;
    private Account nodalAccount;
    private Account bookingAmountAccount;
    private Account subscriptionSpecificExpensesAccount;

    //PROVISIONS
    private Account provisionalPurchaseCostAccount;
    private Account provisionalLossesAccount;
    private Account provisionalBenefitsAccount;
    private Account provisionalTaxesAccount;
    private Account provisionalOthersAccount;
    private Account provisionalCommonExpensesAccount;
    private Account provisionalSubscriptionSpecificExpensesAccount;

    //GAINS
    private Account revenueAccount;
    private Account interestsGainAccount;


    private LocalDate dateForProvision;

    public Account getLossesAccount() {
        return lossesAccount;
    }

    public void setLossesAccount(Account lossesAccount) {
        this.lossesAccount = lossesAccount;
    }

    public Account getProvisionalLossesAccount() {
        return provisionalLossesAccount;
    }

    public void setProvisionalLossesAccount(Account provisionalLossesAccount) {
        this.provisionalLossesAccount = provisionalLossesAccount;
    }

    public BusinessAccount() {

    }

    public BusinessAccount(String id,
                           double provisionForPurchaseCost,
                           double provisionForLosses,
                           double provisionForBenefits,
                           double provisionForTaxes,
                           double provisionForOthers,
                           double provisionForCommonExpenses,
                           double provisionForSubscriptionSpecificExpenses,
                           LocalDate provisionDate) {
        /*this.id = id;
        this.provisionalPurchaseCostAccount = new Account(provisionForPurchaseCost);
        this.dateForProvision = provisionDate;*/
        apply(new CreateProvisionEvent(id,
                provisionForPurchaseCost,
                provisionForLosses,
                provisionForBenefits,
                provisionForTaxes,
                provisionForOthers,
                provisionForCommonExpenses,
                provisionForSubscriptionSpecificExpenses,
                provisionDate));
    }

   /* public void createProvisionForPurchaseCost(String businessAccountId, double provisionForPurchaseCost, LocalDate provisionDate) {
        apply(new CreateProvisionEvent(businessAccountId, provisionForPurchaseCost, provisionDate));
    }*/

    /*@EventSourcingHandler
    public void on(ProductStatusReceivedEvent event) {
        double currentPurchasePrice = event.getCurrentPurchasePrice();
        purchaseCostAccount.credit(currentPurchasePrice);
        provisionalPurchaseCostAccount.debit(currentPurchasePrice);
    }*/

    public void adjustPurchaseCost(double totalPurchaseCost) {
        apply(new PurchaseCostDebitedEvent(this.id, totalPurchaseCost));
        apply(new PurchaseCostCreditedEvent(this.id, totalPurchaseCost));
    }

    public void adjustOperatingExpenses(ExpenseType expenseType, double expenseAmount) {
        apply(new OperatingExpenseDebitedEvent(this.id, expenseType, expenseAmount));
        apply(new OperatingExpenseCreditedEvent(this.id, expenseType, expenseAmount));
    }

    public void adjustBookingAmount(double bookingAmount) {
        apply(new BookingAmountCreditedEvent(this.id, bookingAmount));
    }

    @EventSourcingHandler
    public void on(CreateProvisionEvent event) {
        this.id = event.getBusinessAccountId();

        this.purchaseCostAccount = new Account(AccountType.PURCHASE_COST, 0);
        this.lossesAccount = new Account(AccountType.LOSSES, 0);
        this.benefitsAccount = new Account(AccountType.BENEFITS, 0);
        this.taxesAccount = new Account(AccountType.TAXES, 0);
        this.othersAccount = new Account(AccountType.OTHERS, 0);
        this.commonExpensesAccount = new Account(AccountType.COMMON_EXPENSES, 0);
        this.nodalAccount = new Account(AccountType.NODAL_ACCOUNT, 0);
        this.bookingAmountAccount = new Account(AccountType.BOOKING_AMOUNT, 0);
        this.subscriptionSpecificExpensesAccount = new Account(AccountType.SUBSCRIPTION_SPECIFIC_EXPENSES, 0);

        this.provisionalPurchaseCostAccount = new Account(AccountType.PURCHASE_COST, event.getProvisionForPurchaseCost());
        this.provisionalLossesAccount = new Account(AccountType.LOSSES, event.getProvisionForLosses());
        this.provisionalBenefitsAccount = new Account(AccountType.BENEFITS, event.getProvisionForBenefits());
        this.provisionalTaxesAccount = new Account(AccountType.TAXES, event.getProvisionForTaxes());
        this.provisionalOthersAccount = new Account(AccountType.OTHERS, event.getProvisionForOthers());
        this.provisionalCommonExpensesAccount = new Account(AccountType.COMMON_EXPENSES, event.getProvisionForCommonExpenses());
        this.subscriptionSpecificExpensesAccount = new Account(AccountType.SUBSCRIPTION_SPECIFIC_EXPENSES, event.getProvisionForSubscriptionSpecificExpenses());
        this.dateForProvision = event.getProvisionDate();

        this.revenueAccount = new Account(AccountType.REVENUE, 0);
        this.interestsGainAccount = new Account(AccountType.INTERESTS, 0);
    }

    @EventSourcingHandler
    public void on(PurchaseCostDebitedEvent event) {
        this.provisionalPurchaseCostAccount.debit(event.getAmountToDebit());
    }

    @EventSourcingHandler
    public void on(PurchaseCostCreditedEvent event) {
        this.purchaseCostAccount.credit(event.getAmountToCredit());
    }

    @EventSourcingHandler
    public void on(OperatingExpenseDebitedEvent event) {
        switch (event.getExpenseType()) {
            case COMMON_EXPENSE:
                this.provisionalCommonExpensesAccount.debit(event.getAmountToDebit());
                break;
            case SUBSCRIPTION_SPECIFIC_EXPENSE:
                this.provisionalSubscriptionSpecificExpensesAccount.debit(event.getAmountToDebit());
                break;
            default:
                //TODO : error handling
        }
    }

    @EventSourcingHandler
    public void on(OperatingExpenseCreditedEvent event) {
        switch (event.getExpenseType()) {
            case COMMON_EXPENSE:
                this.commonExpensesAccount.credit(event.getAmountToCredit());
                break;
            case SUBSCRIPTION_SPECIFIC_EXPENSE:
                this.subscriptionSpecificExpensesAccount.credit(event.getAmountToCredit());
                break;
            default:
                //TODO : error handling
        }
    }

    @EventSourcingHandler
    public void on(BookingAmountCreditedEvent event) {
        this.bookingAmountAccount.credit(event.getAmountToCredit());
    }

    public Account getPurchaseCostAccount() {
        return purchaseCostAccount;
    }

    public void setPurchaseCostAccount(Account purchaseCostAccount) {
        this.purchaseCostAccount = purchaseCostAccount;
    }

    public Account getProvisionalPurchaseCostAccount() {
        return provisionalPurchaseCostAccount;
    }

    public void setProvisionalPurchaseCostAccount(Account provisionalPurchaseCostAccount) {
        this.provisionalPurchaseCostAccount = provisionalPurchaseCostAccount;
    }

    public Account getBenefitsAccount() {
        return benefitsAccount;
    }

    public void setBenefitsAccount(Account benefitsAccount) {
        this.benefitsAccount = benefitsAccount;
    }

    public Account getTaxesAccount() {
        return taxesAccount;
    }

    public void setTaxesAccount(Account taxesAccount) {
        this.taxesAccount = taxesAccount;
    }

    public Account getOthersAccount() {
        return othersAccount;
    }

    public void setOthersAccount(Account othersAccount) {
        this.othersAccount = othersAccount;
    }

    public Account getProvisionalBenefitsAccount() {
        return provisionalBenefitsAccount;
    }

    public void setProvisionalBenefitsAccount(Account provisionalBenefitsAccount) {
        this.provisionalBenefitsAccount = provisionalBenefitsAccount;
    }

    public Account getProvisionalTaxesAccount() {
        return provisionalTaxesAccount;
    }

    public void setProvisionalTaxesAccount(Account provisionalTaxesAccount) {
        this.provisionalTaxesAccount = provisionalTaxesAccount;
    }

    public Account getProvisionalOthersAccount() {
        return provisionalOthersAccount;
    }

    public void setProvisionalOthersAccount(Account provisionalOthersAccount) {
        this.provisionalOthersAccount = provisionalOthersAccount;
    }

    public TimeBoundMoney getCommonOperationExpensesReserve() {
        return commonOperationExpensesReserve;
    }

    public void setCommonOperationExpensesReserve(TimeBoundMoney commonOperationExpensesReserve) {
        this.commonOperationExpensesReserve = commonOperationExpensesReserve;
    }

    public TimeBoundMoney getSubscriptionSpecificOperatingExpensesReserve() {
        return subscriptionSpecificOperatingExpensesReserve;
    }

    public void setSubscriptionSpecificOperatingExpensesReserve(TimeBoundMoney subscriptionSpecificOperatingExpensesReserve) {
        this.subscriptionSpecificOperatingExpensesReserve = subscriptionSpecificOperatingExpensesReserve;
    }

    public TimeBoundMoney getSalesAndMarketingExpensesReserve() {
        return salesAndMarketingExpensesReserve;
    }

    public void setSalesAndMarketingExpensesReserve(TimeBoundMoney salesAndMarketingExpensesReserve) {
        this.salesAndMarketingExpensesReserve = salesAndMarketingExpensesReserve;
    }

    public TimeBoundMoney getGoodsPurchaseCostReserve() {
        return goodsPurchaseCostReserve;
    }

    public void setGoodsPurchaseCostReserve(TimeBoundMoney goodsPurchaseCostReserve) {
        this.goodsPurchaseCostReserve = goodsPurchaseCostReserve;
    }

    public TimeBoundMoney getNetProfitRegistered() {
        return netProfitRegistered;
    }

    public void setNetProfitRegistered(TimeBoundMoney netProfitRegistered) {
        this.netProfitRegistered = netProfitRegistered;
    }

    public TimeBoundMoney getTotalRevenueRegistered() {
        return totalRevenueRegistered;
    }

    public void setTotalRevenueRegistered(TimeBoundMoney totalRevenueRegistered) {
        this.totalRevenueRegistered = totalRevenueRegistered;
    }

    public long getTotalSubscriptionsRegistered() {
        return totalSubscriptionsRegistered;
    }

    public void setTotalSubscriptionsRegistered(long totalSubscriptionsRegistered) {
        this.totalSubscriptionsRegistered = totalSubscriptionsRegistered;
    }

    public TimeBoundMoney getCommonOperationExpensesDemand() {
        return commonOperationExpensesDemand;
    }

    public void setCommonOperationExpensesDemand(TimeBoundMoney commonOperationExpensesDemand) {
        this.commonOperationExpensesDemand = commonOperationExpensesDemand;
    }

    public TimeBoundMoney getSubscriptionSpecificOperatingExpensesDemand() {
        return subscriptionSpecificOperatingExpensesDemand;
    }

    public void setSubscriptionSpecificOperatingExpensesDemand(TimeBoundMoney subscriptionSpecificOperatingExpensesDemand) {
        this.subscriptionSpecificOperatingExpensesDemand = subscriptionSpecificOperatingExpensesDemand;
    }

    public TimeBoundMoney getSalesAndMarketingExpensesDemand() {
        return salesAndMarketingExpensesDemand;
    }

    public void setSalesAndMarketingExpensesDemand(TimeBoundMoney salesAndMarketingExpensesDemand) {
        this.salesAndMarketingExpensesDemand = salesAndMarketingExpensesDemand;
    }

    public TimeBoundMoney getGoodsPurchaseCostDemand() {
        return goodsPurchaseCostDemand;
    }

    public void setGoodsPurchaseCostDemand(TimeBoundMoney goodsPurchaseCostDemand) {
        this.goodsPurchaseCostDemand = goodsPurchaseCostDemand;
    }

    public void addToCommonOperatingExpenseReserve(double expenseAmount, int periodInDays) {
        commonOperationExpensesReserve.addToTimeBoundMoney(expenseAmount, periodInDays);
    }

    public void addToSubscriptionSpecificOperatingExpenseReserve(double expenseAmount, int periodInDays) {
        subscriptionSpecificOperatingExpensesReserve.addToTimeBoundMoney(expenseAmount, periodInDays);
    }

    public void addToSalesAndMarketingExpenseReserve(double expenseAmount, int periodInDays) {
        salesAndMarketingExpensesReserve.addToTimeBoundMoney(expenseAmount, periodInDays);
    }

    public void addToGoodsPurchaseCostReserve(double expenseAmount, int periodInDays) {
        goodsPurchaseCostReserve.addToTimeBoundMoney(expenseAmount, periodInDays);
    }

    public void addToCommonOperatingExpenseDemand(double expenseAmount, int periodInDays) {
        commonOperationExpensesDemand.addToTimeBoundMoney(expenseAmount, periodInDays);
    }

    public void addToSubscriptionSpecificOperatingExpenseDemand(double expenseAmount, int periodInDays) {
        subscriptionSpecificOperatingExpensesDemand.addToTimeBoundMoney(expenseAmount, periodInDays);
    }

    public void addToSalesAndMarketingExpenseDemand(double expenseAmount, int periodInDays) {
        salesAndMarketingExpensesDemand.addToTimeBoundMoney(expenseAmount, periodInDays);
    }

    public void addToGoodsPurchaseCostDemand(double expenseAmount, int periodInDays) {
        goodsPurchaseCostDemand.addToTimeBoundMoney(expenseAmount, periodInDays);
    }

}
