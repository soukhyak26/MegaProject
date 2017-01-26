package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import com.affaince.subscription.business.exception.ProvisionNotCreatedException;
import com.affaince.subscription.business.process.operatingexpenses.DefaultOperatingExpensesDeterminator;
import com.affaince.subscription.business.process.operatingexpenses.OperatingExpensesDeterminator;
import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.common.type.TimeBoundMoney;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Map;

public class BusinessAccount extends AbstractAnnotatedAggregateRoot<Integer> {
    @AggregateIdentifier
    private Integer id;
    private TimeBoundMoney netProfitRegistered;
    private TimeBoundMoney totalRevenueRegistered;
    private long totalSubscriptionsRegistered;

    @EventSourcedMember
    private NodalAccount nodalAccount;
    @EventSourcedMember
    private BookingAmountAccount bookingAmountAccount;

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

    public BusinessAccount(Integer id,LocalDate dateOfProvision){
        apply(new BusinessAccountCreatedEvent(id,dateOfProvision));
    }

    public Integer getId() {
        return id;
    }


    public NodalAccount getNodalAccount() {
        return nodalAccount;
    }

    public BookingAmountAccount getBookingAmountAccount() {
        return bookingAmountAccount;
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

    public void adjustPurchaseCost(double totalPurchaseCost) {
        try {
            this.provisionalPurchaseCostAccount.fireDebitedEvent(this.id, totalPurchaseCost);
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    public void adjustOperatingExpenses(ExpenseType expenseType, double expenseAmount) {
        try {
            switch (expenseType) {
                case COMMON_EXPENSE:
                    this.provisionalCommonExpensesAccount.fireDebitedEvent(this.id, expenseAmount);
                    break;
                case SUBSCRIPTION_SPECIFIC_EXPENSE:
                    this.provisionalSubscriptionSpecificExpensesAccount.fireDebitedEvent(this.id, expenseAmount);
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
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    @EventSourcingHandler
    public void on(BusinessAccountCreatedEvent event) {
        this.id=event.getId();
        this.dateForProvision=event.getDateOfProvision();
        LocalDate endDate=dateForProvision.year().withMaximumValue();

        this.provisionalPurchaseCostAccount = new PurchaseCostAccount(dateForProvision,endDate);
        this.provisionalLossesAccount = new LossesAccount(dateForProvision,endDate);
        this.provisionalBenefitsAccount = new BenefitsAccount(dateForProvision,endDate);
        this.provisionalTaxesAccount = new TaxesAccount(dateForProvision,endDate);
        this.provisionalOthersAccount = new OthersAccount(dateForProvision,endDate);
        this.provisionalCommonExpensesAccount = new CommonExpensesAccount(dateForProvision,endDate);
        this.provisionalSubscriptionSpecificExpensesAccount = new SubscriptionSpecificExpensesAccount(dateForProvision,endDate);

        this.revenueAccount = new RevenueAccount(dateForProvision,endDate);
        this.interestsGainAccount = new InterestsAccount(dateForProvision,endDate);
    }


    //Evnt listener for register/upgrade fixed expense shoould fire UpdateFixedExpenseTOProducCommand
    public void updateFixedExpenseToProduct(String productId, double distributionAmountPerUnit, LocalDate distributionDate) {
        apply(new FixedExpenseUpdatedToProductEvent(productId, distributionDate, distributionAmountPerUnit));
    }

    public void registerProvisionForPurchaseCost(Integer id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        apply(new ProvisionForPurchaseCostRegisteredEvent(id,startDate,endDate,provisionForPurchaseOfGoods));
    }

    public void registerProvisionForLosses(Integer id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        apply(new ProvisionForLossesRegisteredEvent(id,startDate,endDate,provisionForPurchaseOfGoods));
    }

    public void registerProvisionForBenefits(Integer id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        apply(new ProvisionForBenefitsRegisteredEvent(id,startDate,endDate,provisionForPurchaseOfGoods));
    }

    public void registerProvisionForTaxes(Integer id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        apply(new ProvisionForTaxesRegisteredEvent(id,startDate,endDate,provisionForPurchaseOfGoods));
    }

    public void registerProvisionForOtherCost(Integer id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        apply(new ProvisionForOtherCostRegisteredEvent(id,startDate,endDate,provisionForPurchaseOfGoods));
    }

    public void registerProvisionForCommonExpenses(Integer id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        apply(new ProvisionForCommonExpensesRegisteredEvent(id,startDate,endDate,provisionForPurchaseOfGoods));

        OperatingExpensesDeterminator operatingExpensesDeterminator =
                new DefaultOperatingExpensesDeterminator();
        final Map<String, Double> perUnitOperatingExpenses = operatingExpensesDeterminator.calculateOperatingExpensesPerProduct(provisionForPurchaseOfGoods);
        perUnitOperatingExpenses.forEach((productId, perUnitExpense) -> apply(
                new FixedExpenseUpdatedToProductEvent(productId, startDate, perUnitExpense)
        ));
    }

    public void registerProvisionForSubscriptionSpecificExpenses(Integer id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        apply(new ProvisionForSubscriptionSpecificExpensesRegisteredEvent(id,startDate,endDate,provisionForPurchaseOfGoods));
    }

    public void registerProvisionForNodal(Integer id, LocalDate startDate, LocalDate endDate, double provisionForNodal) {
        apply(new ProvisionForNodalRegisteredEvent(id,startDate,endDate,provisionForNodal));
    }

    public void addToPurchaseCostAccount(double amountTobeAdded) {
        this.provisionalPurchaseCostAccount.addToPurchaseCost(this.getId(),amountTobeAdded);
    }

    public void reconcilePurchaseCostProvision(String productId, Double currentPurchasePrice, Double currentMRP, Integer currentStockInUnits) {

    }
}
