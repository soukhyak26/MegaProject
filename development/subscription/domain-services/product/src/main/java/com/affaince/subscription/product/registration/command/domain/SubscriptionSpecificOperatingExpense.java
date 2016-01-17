package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.product.registration.command.event.SubscriptionSpecificExpenseAddedEvent;
import com.affaince.subscription.product.registration.vo.RangeRule;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class SubscriptionSpecificOperatingExpense {

    private String id;
    private String expenseHeader;
    private List<RangeRule> deliveryChargesRules;
    private LocalDate subscriptionCreatedDate;
    private LocalDate subscriptionExpiredDate;

    public SubscriptionSpecificOperatingExpense() {
    }

    public SubscriptionSpecificOperatingExpense(String id, String expenseHeader, List<RangeRule> deliveryChargesRules) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.deliveryChargesRules = deliveryChargesRules;
    }

    public double findValueBetween(double min, double max) {
        RangeRule rule = new RangeRule();
        rule.setRuleMinimum(min);
        rule.setRuleMaximum(max);
        if (deliveryChargesRules.contains(rule)) {
            return deliveryChargesRules.get(deliveryChargesRules.indexOf(rule)).getApplicableValue();
        }
        return 0;
    }

    @EventSourcingHandler
    public void on(SubscriptionSpecificExpenseAddedEvent event) {
        this.id = event.getId();
        this.expenseHeader = event.getExpenseHeader();
        this.deliveryChargesRules = event.getDeliveryChargesRules();
    }

    /*
        public void processMonthlyDeliveryExpensesForSubscriptionPeriod(){

        }
    */
/*
    public double calculateMonthlySubscriptionSpecificExepnses(SubscriptionInfo subscriptionInfo) {
        Map<Integer, DeliveryInfo> deliveriesPerWeek = subscriptionInfo.getDeliveriesPerWeek();
        int subscriptionStartMonth = subscriptionCreatedDate.getMonthOfYear();
        int subscriptionStartYear = subscriptionCreatedDate.getYear();
        int subscriptionStartWeek=subscriptionCreatedDate.getWeekOfWeekyear();
        int subscriptionEndMonth = subscriptionExpiredDate.getMonthOfYear();
        int subscriptionEndYear = subscriptionExpiredDate.getYear();
        int subscriptionEndWeek= subscriptionExpiredDate.getWeekOfWeekyear();


        int weeksOfStartYear=new DateTime().withYear(subscriptionStartYear).weekOfWeekyear().getMaximumValue();
        if (subscriptionStartYear!= subscriptionEndYear){
            if(subscriptionCreatedDate.getDayOfWeek()>3) {
                subscriptionEndWeek = weeksOfStartYear - subscriptionStartWeek;
            }else{
                subscriptionEndWeek = weeksOfStartYear - subscriptionStartWeek+1;
            }
        }
        for(int i=subscriptionStartWeek;i<)
        for (DeliveryInfo delivery : deliveriesPerWeek) {
        }
        return 0;
    }
*/
}
