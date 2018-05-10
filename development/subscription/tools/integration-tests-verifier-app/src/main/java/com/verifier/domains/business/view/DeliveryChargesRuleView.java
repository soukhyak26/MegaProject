package com.verifier.domains.business.view;

import com.affaince.subscription.common.type.QuantityUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by rsavaliya on 21/1/16.
 */
@Document(collection = "DeliveryChargesRuleView")
public class DeliveryChargesRuleView {
    @Id
    private String ruleId;
    private String ruleHeader;
    private double ruleMinimum;
    private double ruleMaximum;
    private QuantityUnit ruleUnit;
    private double applicableValue;

    public DeliveryChargesRuleView() {
    }

    public DeliveryChargesRuleView(String ruleId, String ruleHeader, double ruleMinimum, double ruleMaximum, QuantityUnit ruleUnit, double applicableValue) {
        this.ruleId = ruleId;
        this.ruleHeader = ruleHeader;
        this.ruleMinimum = ruleMinimum;
        this.ruleMaximum = ruleMaximum;
        this.ruleUnit = ruleUnit;
        this.applicableValue = applicableValue;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleHeader() {
        return ruleHeader;
    }

    public void setRuleHeader(String ruleHeader) {
        this.ruleHeader = ruleHeader;
    }

    public double getRuleMinimum() {
        return ruleMinimum;
    }

    public void setRuleMinimum(double ruleMinimum) {
        this.ruleMinimum = ruleMinimum;
    }

    public double getRuleMaximum() {
        return ruleMaximum;
    }

    public void setRuleMaximum(double ruleMaximum) {
        this.ruleMaximum = ruleMaximum;
    }

    public QuantityUnit getRuleUnit() {
        return ruleUnit;
    }

    public void setRuleUnit(QuantityUnit ruleUnit) {
        this.ruleUnit = ruleUnit;
    }

    public double getApplicableValue() {
        return applicableValue;
    }

    public void setApplicableValue(double applicableValue) {
        this.applicableValue = applicableValue;
    }

}
