package com.affaince.subscription.business.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 10-07-2016.
 */
@Document(collection = "ProductForecastView")
public class ProductForecastView implements Comparable<ProductForecastView> {
    @Id
    private final ProductVersionId productVersionId;
    private LocalDate endDate;
    private long totalNumberOfExistingSubscriptions;
    private ForecastContentStatus forecastContentStatus;

/*
    public ProductForecastView(ProductVersionId productVersionId, LocalDateTime endDate, long newSubscriptions, long churnedSubscriptions, long totalNumberOfExistingSubscriptions, ForecastContentStatus forecastContentStatus) {
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        this.forecastContentStatus = forecastContentStatus;
    }
*/

    public ProductForecastView(ProductVersionId productVersionId, LocalDate endDate, long totalNumberOfExistingSubscriptions) {
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
    }

    public ProductVersionId getProductVersionId() {
        return productVersionId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public long getTotalNumberOfExistingSubscriptions() {
        return totalNumberOfExistingSubscriptions;
    }

    public void setTotalNumberOfExistingSubscriptions(long totalNumberOfExistingSubscriptions) {
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }

    public int compareTo(ProductForecastView o) {
        return this.getProductVersionId().compareTo(o.getProductVersionId());
    }
}
