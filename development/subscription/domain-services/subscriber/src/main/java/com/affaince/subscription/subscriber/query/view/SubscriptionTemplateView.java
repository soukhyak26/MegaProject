package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.subscriber.web.request.SubscriptionRequirementOfOneFamily;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rsavaliya on 26/3/16.
 */
@Document (collection = "SubscriptionTemplateView")
public class SubscriptionTemplateView {
    @Id
    private String templateId;
    private List<SubscriptionRequirementOfOneFamily> subscriptionRequirementOfOneFamily;

    public SubscriptionTemplateView(String templateId, List<SubscriptionRequirementOfOneFamily> subscriptionRequirementOfOneFamily) {
        this.templateId = templateId;
        this.subscriptionRequirementOfOneFamily = subscriptionRequirementOfOneFamily;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<SubscriptionRequirementOfOneFamily> getSubscriptionRequirementOfOneFamily() {
        return subscriptionRequirementOfOneFamily;
    }

    public void setSubscriptionRequirementOfOneFamily(List<SubscriptionRequirementOfOneFamily> subscriptionRequirementOfOneFamily) {
        this.subscriptionRequirementOfOneFamily = subscriptionRequirementOfOneFamily;
    }
}
