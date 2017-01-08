package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.deserializer.LocalDateTimeDeserializer;
import com.affaince.subscription.common.serializer.LocalDateTimeSerializer;
import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private ProductForecastStatus productForecastStatus;

/*
    public ProductForecastView(ProductVersionId productVersionId, LocalDateTime endDate, long newSubscriptions, long churnedSubscriptions, long totalNumberOfExistingSubscriptions, ProductForecastStatus productForecastStatus) {
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        this.productForecastStatus = productForecastStatus;
    }
*/

/*
    public ProductForecastView(ProductVersionId productVersionId, LocalDate endDate){
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = 0;
        this.churnedSubscriptions = 0;
        this.totalNumberOfExistingSubscriptions = 0;
        this.productForecastStatus = ProductForecastStatus.ACTIVE;
    }
*/
    public ProductForecastView(ProductVersionId productVersionId, LocalDate endDate, long newSubscriptions, long churnedSubscriptions, long totalNumberOfExistingSubscriptions) {
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        this.productForecastStatus = ProductForecastStatus.ACTIVE;
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

    public ProductForecastStatus getProductForecastStatus() {
        return productForecastStatus;
    }

    public void setProductForecastStatus(ProductForecastStatus productForecastStatus) {
        this.productForecastStatus = productForecastStatus;
    }

    public int compareTo(ProductForecastView o) {
        return this.getProductVersionId().compareTo(o.getProductVersionId());
    }
}
