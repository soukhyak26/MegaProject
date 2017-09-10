package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 10-07-2016.
 */
@Document(collection = "ProductForecastView")
public class ProductForecastView implements ProductSubscriptionMetricsView, Comparable<ProductForecastView> {
    @Id
    private final ProductVersionId productVersionId;
    private LocalDate endDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;
    private ForecastContentStatus forecastContentStatus;
    private LocalDate forecastDate;
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

/*
    public ProductForecastView(ProductVersionId productVersionId, LocalDate endDate){
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = 0;
        this.churnedSubscriptions = 0;
        this.totalNumberOfExistingSubscriptions = 0;
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
    }
*/
    public ProductForecastView(ProductVersionId productVersionId, LocalDate endDate, long newSubscriptions, long churnedSubscriptions, long totalNumberOfExistingSubscriptions,LocalDate forecastDate) {
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
        this.forecastDate=forecastDate;
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

    public long getNewSubscriptions() {
        return newSubscriptions;
    }

    public void setNewSubscriptions(long newSubscriptions) {
        this.newSubscriptions = newSubscriptions;
    }

    public long getChurnedSubscriptions() {
        return churnedSubscriptions;
    }

    public void setChurnedSubscriptions(long churnedSubscriptions) {
        this.churnedSubscriptions = churnedSubscriptions;
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

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public int compareTo(ProductForecastView o) {
        return this.getProductVersionId().compareTo(o.getProductVersionId());
    }
}
