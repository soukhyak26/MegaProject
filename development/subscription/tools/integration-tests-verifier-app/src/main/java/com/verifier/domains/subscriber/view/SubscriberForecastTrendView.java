package com.verifier.domains.subscriber.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.verifier.domains.subscriber.vo.SubscriberVersionId;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/22/2017.
 */
public class SubscriberForecastTrendView {
    private SubscriberVersionId subscriberVersionId;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private double referenceNewSubscriberCount;
    private double changeInNewSubscriberCount;
    private double referenceChurnedSubscriberCount;
    private double changeInChurnedSubscriberCount;
    private double referenceTotalSubscriberCount;
    private double changeInTotalSubscriberCount;

    public SubscriberForecastTrendView() {
    }

    public SubscriberForecastTrendView(LocalDate forecastDate, LocalDate startDate, LocalDate endDate) {
        this.subscriberVersionId =new SubscriberVersionId(forecastDate, startDate);
        this.endDate = endDate;
    }


    public SubscriberVersionId getSubscriberVersionId() {
        return subscriberVersionId;
    }

    public void setSubscriberVersionId(SubscriberVersionId subscriberVersionId) {
        this.subscriberVersionId = subscriberVersionId;
    }

    public double getChangeInNewSubscriberCount() {
        return changeInNewSubscriberCount;
    }

    public void setChangeInNewSubscriberCount(double changeInNewSubscriberCount) {
        this.changeInNewSubscriberCount = changeInNewSubscriberCount;
    }

    public double getChangeInChurnedSubscriberCount() {
        return changeInChurnedSubscriberCount;
    }

    public void setChangeInChurnedSubscriberCount(double changeInChurnedSubscriberCount) {
        this.changeInChurnedSubscriberCount = changeInChurnedSubscriberCount;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getChangeInTotalSubscriberCount() {
        return changeInTotalSubscriberCount;
    }

    public void setChangeInTotalSubscriberCount(double changeInTotalSubscriberCount) {
        this.changeInTotalSubscriberCount = changeInTotalSubscriberCount;
    }

    public double getReferenceNewSubscriberCount() {
        return referenceNewSubscriberCount;
    }

    public void setReferenceNewSubscriberCount(double referenceNewSubscriberCount) {
        this.referenceNewSubscriberCount = referenceNewSubscriberCount;
    }

    public double getReferenceChurnedSubscriberCount() {
        return referenceChurnedSubscriberCount;
    }

    public void setReferenceChurnedSubscriberCount(double referenceChurnedSubscriberCount) {
        this.referenceChurnedSubscriberCount = referenceChurnedSubscriberCount;
    }

    public double getReferenceTotalSubscriberCount() {
        return referenceTotalSubscriberCount;
    }

    public void setReferenceTotalSubscriberCount(double referenceTotalSubscriberCount) {
        this.referenceTotalSubscriberCount = referenceTotalSubscriberCount;
    }
}
