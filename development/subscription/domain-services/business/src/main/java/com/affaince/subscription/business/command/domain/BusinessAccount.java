package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.accounting.*;
import com.affaince.subscription.business.command.event.ProvisionCreatedEvent;
import com.affaince.subscription.business.exception.ProvisionNotCreatedException;
import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.TimeBoundMoney;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
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
    @EventSourcedMember
    private PurchaseCostAccount purchaseCostAccount;
    @EventSourcedMember
    private LossesAccount lossesAccount;
    @EventSourcedMember
    private BenefitsAccount benefitsAccount;
    @EventSourcedMember
    private TaxesAccount taxesAccount;
    @EventSourcedMember
    private OthersAccount othersAccount;
    @EventSourcedMember
    private CommonExpensesAccount commonExpensesAccount;
    @EventSourcedMember
    private NodalAccount nodalAccount;
    @EventSourcedMember
    private BookingAmountAccount bookingAmountAccount;
    @EventSourcedMember
    private SubscriptionSpecificExpensesAccount subscriptionSpecificExpensesAccount;

    //PROVISIONS
    @EventSourcedMember
    private PurchaseCostAccount provisionalPurchaseCostAccount;
    @EventSourcedMember
    private LossesAccount provisionalLossesAccount;
    @EventSourcedMember
    private BenefitsAccount provisionalBenefitsAccount;
    @EventSourcedMember
    private TaxesAccount provisionalTaxesAccount;
    @EventSourcedMember
    private OthersAccount provisionalOthersAccount;
    @EventSourcedMember
    private CommonExpensesAccount provisionalCommonExpensesAccount;
    @EventSourcedMember
    private SubscriptionSpecificExpensesAccount provisionalSubscriptionSpecificExpensesAccount;

    //GAINS
    @EventSourcedMember
    private RevenueAccount revenueAccount;
    @EventSourcedMember
    private InterestsAccount interestsGainAccount;

    private LocalDate dateForProvision;

    private static final String INIT_ERROR_MESSAGE = "Please make sure that BusinessAccount aggregate is properly created via CreateProvisionEvent";

    private double defaultPercentFixedExpensePerUnitPrice =1.0;
    private double defaultPercentVariableExpensePerUnitPrice=1.0;

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
                           double defaultPercentFixedExpensePerUnitPrice,
                           double defaultPercentVariableExpensePerUnitPrice,
                           LocalDate provisionDate) {

        apply(new ProvisionCreatedEvent(id,
                provisionForPurchaseCost,
                provisionForLosses,
                provisionForBenefits,
                provisionForTaxes,
                provisionForOthers,
                provisionForCommonExpenses,
                provisionForSubscriptionSpecificExpenses,
                defaultPercentFixedExpensePerUnitPrice,
                defaultPercentVariableExpensePerUnitPrice,
                provisionDate));
    }

    public void adjustPurchaseCost(double totalPurchaseCost) {
        try {
            this.provisionalPurchaseCostAccount.fireDebitedEvent(this.id, totalPurchaseCost);
            this.purchaseCostAccount.fireCreditedEvent(this.id, totalPurchaseCost);
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    public void adjustOperatingExpenses(ExpenseType expenseType, double expenseAmount) {
        try {
            switch (expenseType) {
                case COMMON_EXPENSE:
                    this.provisionalCommonExpensesAccount.fireDebitedEvent(this.id, expenseAmount);
                    this.commonExpensesAccount.fireCreditedEvent(this.id, expenseAmount);
                    break;
                case SUBSCRIPTION_SPECIFIC_EXPENSE:
                    this.provisionalSubscriptionSpecificExpensesAccount.fireDebitedEvent(this.id, expenseAmount);
                    this.subscriptionSpecificExpensesAccount.fireCreditedEvent(this.id, expenseAmount);
                    break;
                default:
                    //TODO : error handling
            }
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    public void adjustBookingAmount(double bookingAmount) {
        try {
            this.bookingAmountAccount.fireCreditedEvent(this.id, bookingAmount);
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    public void adjustBasketAndDeliveryAmount(double basketAmount, double deliveryAmount) {
        try {
            this.bookingAmountAccount.fireDebitedEvent(this.id, basketAmount);
            this.revenueAccount.fireCreditedEvent(this.id, basketAmount);
            adjustOperatingExpenses(ExpenseType.SUBSCRIPTION_SPECIFIC_EXPENSE, deliveryAmount);
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    public void adjustBenefits(double benefitAmount) {
        try {
            this.provisionalBenefitsAccount.fireDebitedEvent(this.id, benefitAmount);
            this.benefitsAccount.fireCreditedEvent(this.id, benefitAmount);
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    @EventSourcingHandler
    public void on(ProvisionCreatedEvent event) {
        this.id = event.getBusinessAccountId();

        this.dateForProvision = event.getProvisionDate();

        this.purchaseCostAccount = new PurchaseCostAccount(0, dateForProvision);
        this.lossesAccount = new LossesAccount(0, dateForProvision);
        this.benefitsAccount = new BenefitsAccount(0, dateForProvision);
        this.taxesAccount = new TaxesAccount(0, dateForProvision);
        this.othersAccount = new OthersAccount(0, dateForProvision);
        this.commonExpensesAccount = new CommonExpensesAccount(0, dateForProvision);
        this.nodalAccount = new NodalAccount(0, dateForProvision);
        this.bookingAmountAccount = new BookingAmountAccount(0, dateForProvision);
        this.subscriptionSpecificExpensesAccount = new SubscriptionSpecificExpensesAccount(0, dateForProvision);

        this.provisionalPurchaseCostAccount = new PurchaseCostAccount(event.getProvisionForPurchaseCost(), dateForProvision);
        this.provisionalLossesAccount = new LossesAccount(event.getProvisionForLosses(), dateForProvision);
        this.provisionalBenefitsAccount = new BenefitsAccount(event.getProvisionForBenefits(), dateForProvision);
        this.provisionalTaxesAccount = new TaxesAccount(event.getProvisionForTaxes(), dateForProvision);
        this.provisionalOthersAccount = new OthersAccount(event.getProvisionForOthers(), dateForProvision);
        this.provisionalCommonExpensesAccount = new CommonExpensesAccount(event.getProvisionForCommonExpenses(), dateForProvision);
        this.provisionalSubscriptionSpecificExpensesAccount = new SubscriptionSpecificExpensesAccount(event.getProvisionForSubscriptionSpecificExpenses(), dateForProvision);

        this.revenueAccount = new RevenueAccount(0, dateForProvision);
        this.interestsGainAccount = new InterestsAccount(0, dateForProvision);
        this.defaultPercentFixedExpensePerUnitPrice=defaultPercentFixedExpensePerUnitPrice;
        this.defaultPercentVariableExpensePerUnitPrice=defaultPercentVariableExpensePerUnitPrice;
    }

    /*private void validateInitialization(Object... inputs) {
        if(inputs == null) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE);
        }
        for(Object input : inputs) {
            if(input == null) {
                throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE);
            }
        }
    }*/

    public String getId() {
        return id;
    }

    public PurchaseCostAccount getPurchaseCostAccount() {
        return purchaseCostAccount;
    }

    public LossesAccount getLossesAccount() {
        return lossesAccount;
    }

    public BenefitsAccount getBenefitsAccount() {
        return benefitsAccount;
    }

    public TaxesAccount getTaxesAccount() {
        return taxesAccount;
    }

    public OthersAccount getOthersAccount() {
        return othersAccount;
    }

    public CommonExpensesAccount getCommonExpensesAccount() {
        return commonExpensesAccount;
    }

    public NodalAccount getNodalAccount() {
        return nodalAccount;
    }

    public BookingAmountAccount getBookingAmountAccount() {
        return bookingAmountAccount;
    }

    public SubscriptionSpecificExpensesAccount getSubscriptionSpecificExpensesAccount() {
        return subscriptionSpecificExpensesAccount;
    }

    public PurchaseCostAccount getProvisionalPurchaseCostAccount() {
        return provisionalPurchaseCostAccount;
    }

    public LossesAccount getProvisionalLossesAccount() {
        return provisionalLossesAccount;
    }

    public BenefitsAccount getProvisionalBenefitsAccount() {
        return provisionalBenefitsAccount;
    }

    public TaxesAccount getProvisionalTaxesAccount() {
        return provisionalTaxesAccount;
    }

    public OthersAccount getProvisionalOthersAccount() {
        return provisionalOthersAccount;
    }

    public CommonExpensesAccount getProvisionalCommonExpensesAccount() {
        return provisionalCommonExpensesAccount;
    }

    public SubscriptionSpecificExpensesAccount getProvisionalSubscriptionSpecificExpensesAccount() {
        return provisionalSubscriptionSpecificExpensesAccount;
    }

    public RevenueAccount getRevenueAccount() {
        return revenueAccount;
    }

    public InterestsAccount getInterestsGainAccount() {
        return interestsGainAccount;
    }

    public LocalDate getDateForProvision() {
        return dateForProvision;
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
