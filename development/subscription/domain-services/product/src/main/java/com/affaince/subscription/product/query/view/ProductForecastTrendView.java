package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.vo.ForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/21/2017.
 */
@Document(collection="ProductForecastTrendView")
public class ProductForecastTrendView {
    @Id
    private ForecastVersionId forecastVersionId;
    private LocalDate endDate;
    private double referenceNewSubscriptionCount;
    private double changeInNewSubscriptionCount;
    private double referenceChurnedSubscriptionCount;
    private double changeInChurnedSubscriptionCount;
    private double referenceTotalSubscriptionCount;
    private double changeInTotalSusbcriptionCount;

    public ProductForecastTrendView(String productId, LocalDate forecastDate, LocalDate startDate, LocalDate endDate) {
        this.forecastVersionId = new ForecastVersionId(productId,startDate,forecastDate);
        this.endDate = endDate;
    }

    public double getChangeInNewSubscriptionCount() {
        return changeInNewSubscriptionCount;
    }

    public void setChangeInNewSubscriptionCount(double changeInNewSubscriptionCount) {
        this.changeInNewSubscriptionCount = changeInNewSubscriptionCount;
    }

    public double getChangeInChurnedSubscriptionCount() {
        return changeInChurnedSubscriptionCount;
    }

    public void setChangeInChurnedSubscriptionCount(double changeInChurnedSubscriptionCount) {
        this.changeInChurnedSubscriptionCount = changeInChurnedSubscriptionCount;
    }

    public void setChangeInTotalSusbcriptionCount(double changeInTotalSusbcriptionCount) {
        this.changeInTotalSusbcriptionCount = changeInTotalSusbcriptionCount;
    }

    public ForecastVersionId getForecastVersionId() {
        return forecastVersionId;
    }

    public String getProductId() {
        return this.forecastVersionId.getProductId();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getChangeInTotalSusbcriptionCount() {
        return changeInTotalSusbcriptionCount;
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
