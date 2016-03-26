package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rsavaliya on 26/3/16.
 */
public class AddSubscriptionTemplateRequest {

    private SubscriptionRequirementOfOneFamily [] subscriptionRequirementOfOneFamily;

    public SubscriptionRequirementOfOneFamily[] getSubscriptionRequirementOfOneFamily() {
        return subscriptionRequirementOfOneFamily;
    }

    public void setSubscriptionRequirementOfOneFamily(SubscriptionRequirementOfOneFamily[] subscriptionRequirementOfOneFamily) {
        this.subscriptionRequirementOfOneFamily = subscriptionRequirementOfOneFamily;
    }
}
