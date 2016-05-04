package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.accounting.Account;
import com.affaince.subscription.business.command.event.CreateProvisionEvent;
import com.affaince.subscription.business.command.event.ProductStatusReceivedEvent;
import com.affaince.subscription.business.provision.ProvisionIndex;
import com.affaince.subscription.common.type.TimeBoundMoney;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

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

    List<Double> provisionList;
    List<Account> accountList = new ArrayList<>(ProvisionIndex.MAX_CAPACITY.getIndex());
    List<Account> provisionalAccountList = new ArrayList<>(ProvisionIndex.MAX_CAPACITY.getIndex());

    private LocalDate dateForProvision;

    {
        for(int i = 0 ; i < ProvisionIndex.MAX_CAPACITY.getIndex() ; i++) {
            accountList.add(i, new Account(0));
        }
    }
    public BusinessAccount() {

    }

    public BusinessAccount(String id,
                           List<Double> provisionList,
                           LocalDate provisionDate) {
        /*this.id = id;
        this.provisionalPurchaseCostAccount = new Account(provisionForPurchaseCost);
        this.dateForProvision = provisionDate;*/
        apply(new CreateProvisionEvent(id,
                provisionList,
                provisionDate));
    }

   /* public void createProvisionForPurchaseCost(String businessAccountId, double provisionForPurchaseCost, LocalDate provisionDate) {
        apply(new CreateProvisionEvent(businessAccountId, provisionForPurchaseCost, provisionDate));
    }*/

    @EventSourcingHandler
    public void on(ProductStatusReceivedEvent event) {
        double currentPurchasePrice = event.getCurrentPurchasePrice();
        /*purchaseCostAccount.credit(currentPurchasePrice);
        provisionalPurchaseCostAccount.debit(currentPurchasePrice);*/
        accountList.get(ProvisionIndex.PURCHASE_COST.getIndex()).credit(currentPurchasePrice);
        provisionalAccountList.get(ProvisionIndex.PURCHASE_COST.getIndex()).debit(currentPurchasePrice);
    }

    @EventSourcingHandler
    public void on(CreateProvisionEvent event) {
        this.id = event.getBusinessAccountId();
        /*this.provisionalPurchaseCostAccount = new Account(event.getProvisionForPurchaseCost());
        this.provisionalLossesAccount = new Account(event.getProvisionForLosses());
        this.provisionalBenefitsAccount = new Account(event.getProvisionForBenefits());*/
        /*for(ProvisionIndex provisionIndex : ProvisionIndex.values()) {
            switch (provisionIndex) {
                case MAX_CAPACITY:
                    break;
                default:
                    provisionalAccountList.add(provisionIndex.getIndex(), new Account(event.getProvisionList().get(provisionIndex.getIndex())));
            }
        }*/
        for(int i = 0 ; i < ProvisionIndex.MAX_CAPACITY.getIndex() ; i++) {
            provisionalAccountList.add(i, new Account(event.getProvisionList().get(i)));
        }
        this.dateForProvision = event.getProvisionDate();
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
