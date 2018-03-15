package com.verifier.domains.subscriber.view;

import com.verifier.domains.subscriber.vo.SubscriptionVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/22/2017.
 */
@Document(collection="SubscriptionForecastTrendView")
public class SubscriptionForecastTrendView {
    @Id
    private SubscriptionVersionId subscriptionVersionId;
    private LocalDate endDate;
    private double referenceNewSubscriptionCount;
    private double changeInNewSubscriptionCount;
    private double referenceChurnedSubscriptionCount;
    private double changeInChurnedSubscriptionCount;
    private double referenceTotalSubscriptionCount;
    private double changeInTotalSubscriptionCount;


    public SubscriptionForecastTrendView(LocalDate forecastDate,LocalDate startDate, LocalDate endDate,double valueRangeMin, double valueRangeMax) {
        this.subscriptionVersionId = new SubscriptionVersionId(forecastDate,startDate,valueRangeMin,valueRangeMax);
        this.endDate=endDate;
    }

    public SubscriptionVersionId getSubscriptionVersionId() {
        return subscriptionVersionId;
    }

    public void setChangeInNewSubscriptionCount(double changeInNewSubscriptionCount) {
        this.changeInNewSubscriptionCount = changeInNewSubscriptionCount;
    }

    public void setChangeInChurnedSubscriptionCount(double changeInChurnedSubscriptionCount) {
        this.changeInChurnedSubscriptionCount = changeInChurnedSubscriptionCount;
    }

    public double getChangeInNewSubscriptionCount() {
        return changeInNewSubscriptionCount;
    }

    public double getChangeInChurnedSubscriptionCount() {
        return changeInChurnedSubscriptionCount;
    }

    public LocalDate getStartDate() {
        return subscriptionVersionId.getStartDate();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getChangeInTotalSubscriptionCount() {
        return changeInTotalSubscriptionCount;
    }

    public void setChangeInTotalSubscriptionCount(double changeInTotalSubscriptionCount) {
        this.changeInTotalSubscriptionCount = changeInTotalSubscriptionCount;
    }

    public double getReferenceNewSubscriptionCount() {
        return referenceNewSubscriptionCount;
    }

    public void setReferenceNewSubscriptionCount(double referenceNewSubscriptionCount) {
        this.referenceNewSubscriptionCount = referenceNewSubscriptionCount;
    }

    public double getReferenceChurnedSubscriptionCount() {
        return referenceChurnedSubscriptionCount;
    }

    public void setReferenceChurnedSubscriptionCount(double referenceChurnedSubscriptionCount) {
        this.referenceChurnedSubscriptionCount = referenceChurnedSubscriptionCount;
    }

    public double getReferenceTotalSubscriptionCount() {
        return referenceTotalSubscriptionCount;
    }

    public void setReferenceTotalSubscriptionCount(double referenceTotalSubscriptionCount) {
        this.referenceTotalSubscriptionCount = referenceTotalSubscriptionCount;
    }
}
