package com.verifier.domains.business.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 5/31/2017.
 */
public class ModifiedSubscriptionContent {
    private String susbcriptionId;
    private List<ModifiedDeliveryContent> modifiedDeliveries;
    private double modifiedTotalSubscriptionPayment;
    private double modifiedTotalDuePayment;

    public ModifiedSubscriptionContent(String susbcriptionId) {
        this.susbcriptionId = susbcriptionId;
        this.modifiedDeliveries= new ArrayList<>();
    }

    public String getSusbcriptionId() {
        return susbcriptionId;
    }

    public List<ModifiedDeliveryContent> getModifiedDeliveries() {
        return modifiedDeliveries;
    }

    public double getModifiedTotalSubscriptionPayment() {
        return modifiedTotalSubscriptionPayment;
    }

    public double getModifiedTotalDuePayment() {
        return modifiedTotalDuePayment;
    }

    public void setModifiedDeliveries(List<ModifiedDeliveryContent> modifiedDeliveries) {
        this.modifiedDeliveries = modifiedDeliveries;
    }

    public void setModifiedTotalSubscriptionPayment(double modifiedTotalSubscriptionPayment) {
        this.modifiedTotalSubscriptionPayment = modifiedTotalSubscriptionPayment;
    }

    public void setModifiedTotalDuePayment(double modifiedTotalDuePayment) {
        this.modifiedTotalDuePayment = modifiedTotalDuePayment;
    }
}
