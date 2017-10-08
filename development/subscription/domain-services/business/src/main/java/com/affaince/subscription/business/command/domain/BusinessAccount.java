package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import com.affaince.subscription.business.exception.ProvisionNotCreatedException;
import com.affaince.subscription.business.process.operatingexpenses.DefaultOperatingExpensesDeterminator;
import com.affaince.subscription.business.process.operatingexpenses.OperatingExpensesDeterminator;
import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;
import com.affaince.subscription.business.vo.RecommendationReceiver;
import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public class BusinessAccount extends AbstractAnnotatedAggregateRoot<Integer> {
    @AggregateIdentifier
    private Integer id;

    @EventSourcedMember
    private ProfitAccount profitAccount;
    private long totalSubscriptionsRegistered;
    private long registeredProductCount;

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

    private double defaultPercentFixedExpensePerUnitPrice = 1.0;
    private double defaultPercentVariableExpensePerUnitPrice = 1.0;

    public BusinessAccount() {

    }

    public BusinessAccount(Integer id, LocalDate dateOfProvision) {
        apply(new BusinessAccountCreatedEvent(id, dateOfProvision));
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

    public long getTotalSubscriptionsRegistered() {
        return totalSubscriptionsRegistered;
    }

    public void setTotalSubscriptionsRegistered(long totalSubscriptionsRegistered) {
        this.totalSubscriptionsRegistered = totalSubscriptionsRegistered;
    }

    public void adjustOperatingExpenses(ExpenseType expenseType, double expenseAmount) {
        try {
            switch (expenseType) {
                case COMMON_EXPENSE:
                    this.provisionalCommonExpensesAccount.debitFromCommonExpenses(this.id, expenseAmount);
                    break;
                case SUBSCRIPTION_SPECIFIC_EXPENSE:
                    this.provisionalSubscriptionSpecificExpensesAccount.debitFromSubscriptionSpecificExpenses(this.id, expenseAmount);
                    break;
                default:
                    //TODO : error handling
            }
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    public void creditToBookingAmount(double bookingAmount) {
        try {
            this.bookingAmountAccount.creditToBookingAmount(this.id, bookingAmount);
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }
    //TODO: The transfer should happen only if the delivery status is 'delivered'
    public void transferFromBookingAmountToRevenue(String contributorId, double basketAmount, double deliveryAmount) {
        try {
            this.bookingAmountAccount.debitFromBookingAmount(this.id, contributorId, basketAmount);
            this.revenueAccount.creditToRevenue(this.id, contributorId, basketAmount);
            adjustOperatingExpenses(ExpenseType.SUBSCRIPTION_SPECIFIC_EXPENSE, deliveryAmount);
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }

    public void adjustBenefits(String contributorId, double benefitAmount) {
        try {
            this.provisionalBenefitsAccount.issueBenefitsAmountToBeneficiary(this.id, contributorId, benefitAmount);
        } catch (NullPointerException npe) {
            throw new ProvisionNotCreatedException(INIT_ERROR_MESSAGE, npe);
        }
    }
    public void debitDeliveredItemsCostFromPurchaseAccount(String productId, double purchaseCostContribution){
        this.getProvisionalPurchaseCostAccount().debitDeliveredItemsCostFromPurchaseAccount(this.id,productId,purchaseCostContribution );
    }
    @EventSourcingHandler
    public void on(BusinessAccountCreatedEvent event) {
        this.id = event.getId();
        this.dateForProvision = event.getDateOfProvision();
        LocalDate endDate = dateForProvision.year().withMaximumValue();

        this.provisionalPurchaseCostAccount = new PurchaseCostAccount(dateForProvision, endDate);
        this.provisionalLossesAccount = new LossesAccount(dateForProvision, endDate);
        this.provisionalBenefitsAccount = new BenefitsAccount(dateForProvision, endDate);
        this.provisionalTaxesAccount = new TaxesAccount(dateForProvision, endDate);
        this.provisionalOthersAccount = new OthersAccount(dateForProvision, endDate);
        this.provisionalCommonExpensesAccount = new CommonExpensesAccount(dateForProvision, endDate);
        this.provisionalSubscriptionSpecificExpensesAccount = new SubscriptionSpecificExpensesAccount(dateForProvision, endDate);
        this.revenueAccount = new RevenueAccount(dateForProvision, endDate);
        this.interestsGainAccount = new InterestsAccount(dateForProvision, endDate);
        this.profitAccount = new ProfitAccount(dateForProvision, endDate);
    }


    //Evnt listener for register/upgrade fixed expense should fire UpdateFixedExpenseTOProducCommand
    //TODO:Currently unused
    public void updateFixedExpenseToProduct(String productId, double distributionAmountPerUnit, LocalDate distributionDate) {
        apply(new FixedExpenseUpdatedToProductEvent(productId, distributionDate, distributionAmountPerUnit));
    }

    public void registerProvisionForPurchaseCost(Integer id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        this.getProvisionalPurchaseCostAccount().registerProvisionForPurchaseCost(id,startDate,endDate,provisionForPurchaseOfGoods);
    }

    public void registerProvisionForLosses(Integer id, LocalDate startDate, LocalDate endDate, double provisionForLosses) {
            this.getProvisionalLossesAccount().registerProvisionForLosses(id,startDate,endDate,provisionForLosses);
    }

    public void registerProvisionForBenefits(Integer id, LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        this.getProvisionalBenefitsAccount().registerProvisionForBenefits(id,startDate,endDate,provisionForBenefits);
    }

    public void registerProvisionForTaxes(Integer id, LocalDate startDate, LocalDate endDate, double provisionForTaxes) {
        this.getProvisionalTaxesAccount().registerProvisionForTaxes(id,startDate,endDate,provisionForTaxes);
    }

    public void registerProvisionForOtherCost(Integer id, LocalDate startDate, LocalDate endDate, double provisionForOtherCost) {
        this.getProvisionalOthersAccount().registerProvisionForOtherCost(id,startDate,endDate,provisionForOtherCost);
    }

    public void registerProvisionForCommonExpenses(Integer id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods,DefaultOperatingExpensesDeterminator defaultOperatingExpensesDeterminator) {
        this.provisionalCommonExpensesAccount.registerProvisionForCommonExpenses(id,startDate,endDate,provisionForPurchaseOfGoods,defaultOperatingExpensesDeterminator);
    }

    public void registerProvisionForSubscriptionSpecificExpenses(Integer id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        this.provisionalSubscriptionSpecificExpensesAccount.registerProvisionForSubscriptionSpecificExpenses(id,startDate,endDate,provisionForPurchaseOfGoods);
    }

    public void registerProvisionForNodal(Integer id, LocalDate startDate, LocalDate endDate, double provisionForNodal) {
        this.nodalAccount.registerProvisionForNodal(id,startDate,endDate,provisionForNodal);
    }

    public void addToPurchaseCostAccount(Integer businessAccountId, String productId, long totalSubscriptionsRegistered, double productPurchasePricePerUnit) {
        this.provisionalPurchaseCostAccount.addToRecommendationForAdditionToPurchaseCost(businessAccountId, productId, totalSubscriptionsRegistered, productPurchasePricePerUnit);
    }

    public void reduceFromPurchaseCostAccount(Integer businessAccountId, String productId, long totalSubscriptionsRegistered, double productPurchasePricePerUnit) {
        this.provisionalPurchaseCostAccount.addToRecommendationForReductionToPurchaseCost(businessAccountId, productId, totalSubscriptionsRegistered, productPurchasePricePerUnit);
    }

    public void reconcilePurchaseCostProvision(String productId, Double currentPurchasePrice, Double currentMRP, Integer currentStockInUnits) {

    }

    public void updatePurchaseCostRevenueAndProfit(String productId, double purchaseCostContribution, double revenueContribution, double profitContribution) {
        //this.addToPurchaseCostAccount(purchaseCostContribution);
        this.debitDeliveredItemsCostFromPurchaseAccount(productId,purchaseCostContribution);
        this.addToRevenueAccount(productId, revenueContribution);
        this.addToProfitAccount(productId, profitContribution);
    }

    private void addToProfitAccount(String productId, double profitContribution) {
        this.profitAccount.addProfitToProfitAccount(this.id, productId, profitContribution);
    }

    private void addToRevenueAccount(String productId, double revenueContribution) {
        this.revenueAccount.creditToRevenue(this.id, productId, revenueContribution);
    }

    public void addToNodalAccount(String productId, double excessProfit, LocalDate transactionDate) {
        apply(new ExcessProfitAddedToNodalAccountEvent(this.id, productId, excessProfit, transactionDate));
    }

    @EventSourcingHandler
    public void on(ExcessProfitAddedToNodalAccountEvent event) {
        this.nodalAccount.credit(event.getExcessProfit());
        this.profitAccount.addToExcessProfitToDebit(new ProfitDebitAccount(event.getProductId(), event.getExcessProfit(), SysDate.now()));
    }

    public void concludeRecommendations(List<BudgetChangeRecommendationView> acceptedRecommendations, List<BudgetChangeRecommendationView> rejectedRecommendations) {
        for (BudgetChangeRecommendationView acceptedRecommendation : acceptedRecommendations) {
            RecommendationReceiver recommendationReceiver = acceptedRecommendation.getRecommendationReceiver();
            switch (recommendationReceiver) {
                case PURCHASE_COST_ACCOUNT:
                    this.provisionalPurchaseCostAccount.acceptOrOverrideRecommendation(acceptedRecommendation);
                    break;
                case BENEFITS_ACCOUNT:
                    this.provisionalBenefitsAccount.acceptOrOverrideRecommendation(acceptedRecommendation);
                    break;
                case TAXES_ACCOUNT:
                    this.provisionalTaxesAccount.acceptOrOverrideRecommendation(acceptedRecommendation);
                    break;
                case COMMON_EXPENSES_ACCOUNT:
                    this.provisionalCommonExpensesAccount.acceptOrOverrideRecommendation(acceptedRecommendation);
                    break;
                case SUBSCRIPTION_SPICIFIC_EXPENSES_ACCOUNT:
                    this.provisionalSubscriptionSpecificExpensesAccount.acceptOrOverrideRecommendation(acceptedRecommendation);
                    break;
            }
        }
        for (BudgetChangeRecommendationView rejectedRecommendation : rejectedRecommendations) {
            RecommendationReceiver recommendationReceiver = rejectedRecommendation.getRecommendationReceiver();
            switch (recommendationReceiver) {
                case PURCHASE_COST_ACCOUNT:
                    this.provisionalPurchaseCostAccount.rejectRecommendation(rejectedRecommendation);
                    break;
                case BENEFITS_ACCOUNT:
                    this.provisionalBenefitsAccount.rejectRecommendation(rejectedRecommendation);
                case TAXES_ACCOUNT:
                    this.provisionalTaxesAccount.rejectRecommendation(rejectedRecommendation);
                    break;
                case COMMON_EXPENSES_ACCOUNT:
                    this.provisionalCommonExpensesAccount.rejectRecommendation(rejectedRecommendation);
                    break;
                case SUBSCRIPTION_SPICIFIC_EXPENSES_ACCOUNT:
                    this.provisionalSubscriptionSpecificExpensesAccount.rejectRecommendation(rejectedRecommendation);
                    break;

            }
        }
    }

    public void addRegisteredProductCount(long registeredProductCount) {
        apply ( new RegisteredProductCountAddedToBusinessAccountEvent(this.id,registeredProductCount ));
    }

    @EventSourcingHandler
    public void on(RegisteredProductCountAddedToBusinessAccountEvent event){
        this.registeredProductCount +=event.getRegisteredProductCount();
        this.getProvisionalPurchaseCostAccount().addToRemainingProductCount(event.getRegisteredProductCount());
    }
}
