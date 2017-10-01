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
    private double changeInNewSubscriptionCount;
    private double changeInChurnedSubscriptionCount;
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
}
