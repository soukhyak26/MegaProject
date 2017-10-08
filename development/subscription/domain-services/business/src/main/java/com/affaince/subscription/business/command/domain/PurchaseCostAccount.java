package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;
import com.affaince.subscription.business.vo.*;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import java.util.ArrayList;
import java.util.List;

public class PurchaseCostAccount extends AbstractAnnotatedEntity {

    //private double additionalRecommendedProvisionAmount;
    private List<AdditionalBudgetRecommendation> recommendations;
    private double provisionAmount;
    private List<InstallmentPerCalendarPeriod> installmentCalendar;
    private LocalDate startDate;
    private LocalDate endDate;
    private long remainingProductCount;


    public PurchaseCostAccount(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.recommendations = new ArrayList<>();
        this.installmentCalendar= new ArrayList<>();
    }

    public void debit(double amount) {
        this.provisionAmount-=amount;
    }


    public double getProvisionAmount() {
        return provisionAmount;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


    public void addToRemainingProductCount(long registeredProductCount){
        remainingProductCount +=registeredProductCount;
    }
    public List<AdditionalBudgetRecommendation> getRecommendations() {
        return recommendations;
    }

    public long getRemainingProductCount() {
        return remainingProductCount;
    }

    public void addToAdditionalProvisionRecommendation(String recommenderId, LocalDate recommendationDate, RecommenderType recommenderType, double recommendedAmount, RecommendationReason recommendationReason) {
        recommendations.add(new AdditionalBudgetRecommendation(recommenderId, recommendationDate, recommenderType, recommendedAmount, recommendationReason));
    }

    public void deductFromPurchaseCost(Integer businessAccountId,String productId,long totalSubscriptionsRegistered,double productPurchasePricePerUnit) {
        double additionalBudgetedAmount = totalSubscriptionsRegistered * productPurchasePricePerUnit;
        apply(new PurchaseCostDebitedEvent(businessAccountId, productId, totalSubscriptionsRegistered, productPurchasePricePerUnit, additionalBudgetedAmount));
    }

    public void registerProvisionForPurchaseCost(Integer id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        int startMonth= startDate.getMonthOfYear();
        YearMonth month=new YearMonth(startDate.getYear(), startDate.getMonthOfYear());
        int endMonth=endDate.getMonthOfYear();
        int period= endMonth - startMonth +1;

        List<InstallmentPerCalendarPeriod> calendar= new ArrayList<>();
        double monthlyInstallment=provisionForPurchaseOfGoods/period;
        for(int i=0;i<period;i++){
            InstallmentPerCalendarPeriod installment= new InstallmentPerCalendarPeriod(startDate.dayOfMonth().withMinimumValue(),startDate.dayOfMonth().withMaximumValue());
            installment.addAmount(monthlyInstallment);
            startDate= startDate.plusMonths(1);
        }
        apply(new ProvisionForPurchaseCostRegisteredEvent(id, startDate, endDate, provisionForPurchaseOfGoods,calendar));
    }


    @EventSourcingHandler
    public void on(ProvisionForPurchaseCostRegisteredEvent event) {
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        //Manual provision should be directly registered in provisionAmount
        this.provisionAmount = event.getProvisionForPurchaseOfGoods();
        this.installmentCalendar=event.getCalendar();
    }

    @EventSourcingHandler
    public void on(PurchaseCostDebitedEvent event) {
        this.provisionAmount -=event.getExcessBudgetedAmount();
    }

    public void addToPurchaseCostDueToSubscriptionCountChange(Integer id, String productId, long totalSubscriptionsRegistered, double productPurchasePricePerUnit) {
        double additionalBudgetedAmount = totalSubscriptionsRegistered * productPurchasePricePerUnit;
        apply(new PurchaseCostCreditedEvent(id, productId, totalSubscriptionsRegistered, productPurchasePricePerUnit, additionalBudgetedAmount));
    }

    public void addToPurchaseCostDueToPurchasePriceChange(Integer id, String productId, double additionalBudgetProvision) {
        apply(new PurchaseCostRecommendationCreditedEvent(id, productId, SysDate.now(), additionalBudgetProvision, RecommenderType.PRODUCT, RecommendationReason.PURCHASE_PRICE_INCREASED));
    }


    public void addToRecommendationForAdditionToPurchaseCost(Integer id, String productId, long totalSubscriptionsRegistered, double productPurchasePricePerUnit) {
        double additionalBudgetedAmount = totalSubscriptionsRegistered * productPurchasePricePerUnit;
        apply(new PurchaseCostRecommendationCreditedEvent(id, productId, SysDate.now(), additionalBudgetedAmount, RecommenderType.PRODUCT, RecommendationReason.SUBSCRIPTION_COUNT_INCREASED));
    }

    public void addToRecommendationForReductionToPurchaseCost(Integer id, String productId, long totalSubscriptionsRegistered, double productPurchasePricePerUnit) {
        double reducedBudgetedAmount = totalSubscriptionsRegistered * productPurchasePricePerUnit;
        apply(new PurchaseCostRecommendationCreditedEvent(id, productId, SysDate.now(), reducedBudgetedAmount, RecommenderType.PRODUCT, RecommendationReason.SUBSCRIPTION_COUNT_DECREASED));
    }

    @EventSourcingHandler
    public void on(PurchaseCostRecommendationCreditedEvent event) {
        this.addToAdditionalProvisionRecommendation(event.getProductId(), event.getRecommendationDate(), event.getRecommenderType(), event.getAdditionalBudgetedAmount(), event.getRecommendationReason());
    }

    @EventSourcingHandler
    public void on(PurchaseCostCreditedEvent event) {
        this.provisionAmount += event.getAdditionalBudgetedAmount();
    }

    public void acceptOrOverrideRecommendation(BudgetChangeRecommendationView acceptedRecommendation) {
        if (acceptedRecommendation.getRecommendationReceiver() == RecommendationReceiver.PURCHASE_COST_ACCOUNT) {
            double newPurchaseProvisionAmount=this.provisionAmount+acceptedRecommendation.getAdditionalBudgetedAmount();
            apply(new AdditionalBudgetRecommendationConfirmedEvent(acceptedRecommendation.getBusinessAccountId(), acceptedRecommendation.getRecommenderId(), acceptedRecommendation.getRecommendationDate(), acceptedRecommendation.getAdditionalBudgetedAmount(), newPurchaseProvisionAmount,acceptedRecommendation.getRecommenderType(), acceptedRecommendation.getRecommendationReason(),acceptedRecommendation.getRecommendationReceiver()));
        }
    }

    public void rejectRecommendation(BudgetChangeRecommendationView rejectedRecommendation) {
        if (rejectedRecommendation.getRecommendationReceiver() == RecommendationReceiver.PURCHASE_COST_ACCOUNT) {
            apply(new AdditionalBudgetRecommendationRejectedEvent(rejectedRecommendation.getBusinessAccountId(), rejectedRecommendation.getRecommenderId(), rejectedRecommendation.getRecommendationDate(), rejectedRecommendation.getAdditionalBudgetedAmount(), rejectedRecommendation.getRecommenderType(), rejectedRecommendation.getRecommendationReason(),rejectedRecommendation.getRecommendationReceiver()));
        }
    }

    @EventSourcingHandler
    public void on(AdditionalBudgetRecommendationConfirmedEvent event) {
        for (AdditionalBudgetRecommendation recommendation : recommendations) {
            if (event.getAdditionalBudgetedAmount() == recommendation.getRecommendationAmount()
                    && event.getRecommendationDate() == recommendation.getRecommendationDate()
                    && event.getRecommendationReason() == recommendation.getRecommendationReason()
                    && event.getRecommenderId() == recommendation.getRecommenderId()) {
                this.provisionAmount =event.getRevisedProvisionalAmount();

                recommendations.remove(recommendation);
                break;
            }
        }

    }

    @EventSourcingHandler
    public void on(AdditionalBudgetRecommendationRejectedEvent event) {
        for (AdditionalBudgetRecommendation recommendation : recommendations) {
            if (event.getAdditionalBudgetedAmount() == recommendation.getRecommendationAmount()
                    && event.getRecommendationDate() == recommendation.getRecommendationDate()
                    && event.getRecommendationReason() == recommendation.getRecommendationReason()
                    && event.getRecommenderId() == recommendation.getRecommenderId()) {
                recommendations.remove(recommendation);
                break;
            }
        }
    }

    public void debitDeliveredItemsCostFromPurchaseAccount(Integer businessAccountId,String productId, double purchasePriceContribution) {
        double revisedProvisionAmount = this.provisionAmount-purchasePriceContribution;
        apply(new CostOfDeliveredGoodsDebitedEvent(businessAccountId,productId,purchasePriceContribution,revisedProvisionAmount,SysDate.now(),new LocalDate(YearMonth.now().getYear(),12,31)));
    }

    @EventSourcingHandler
    public void on(CostOfDeliveredGoodsDebitedEvent event){
        this.provisionAmount=event.getRevisedProvisionAmount();
    }

    public void addToPurchaseCost(Integer businessAccountId, String productId, long totalSubscriptionsRegistered, double productPurchasePricePerUnit) {
        double additionalBudgetedAmount = totalSubscriptionsRegistered * productPurchasePricePerUnit;
        apply(new PurchaseCostCreditedEvent(businessAccountId, productId, totalSubscriptionsRegistered, productPurchasePricePerUnit, additionalBudgetedAmount));
    }
}
