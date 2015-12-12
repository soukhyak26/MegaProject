package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.Discount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
@Document(collection = "BasketRule")
public class BasketRuleView {
    @Id
    private String basketRuleId;
    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private Discount maximumPermissibleDiscount;

    public BasketRuleView(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, Discount maximumPermissibleDiscount) {
        this.basketRuleId = basketRuleId;
        this.maximumPermissibleAmount = maximumPermissibleAmount;
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
    }

    public String getBasketRuleId() {
        return basketRuleId;
    }

    public void setBasketRuleId(String basketRuleId) {
        this.basketRuleId = basketRuleId;
    }

    public double getMaximumPermissibleAmount() {
        return maximumPermissibleAmount;
    }

    public void setMaximumPermissibleAmount(double maximumPermissibleAmount) {
        this.maximumPermissibleAmount = maximumPermissibleAmount;
    }

    public double getMinimumAmountForDiscountEligibility() {
        return minimumAmountForDiscountEligibility;
    }

    public void setMinimumAmountForDiscountEligibility(double minimumAmountForDiscountEligibility) {
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
    }

    public Discount getMaximumPermissibleDiscount() {
        return maximumPermissibleDiscount;
    }

    public void setMaximumPermissibleDiscount(Discount maximumPermissibleDiscount) {
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
    }
}
