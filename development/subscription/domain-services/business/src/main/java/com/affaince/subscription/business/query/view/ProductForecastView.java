package com.affaince.subscription.business.query.view;

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
public class ProductForecastView implements Comparable<ProductForecastView> {
    @Id
    private final ProductVersionId productVersionId;
    private LocalDate endDate;
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

    public ProductForecastView(ProductVersionId productVersionId, LocalDate endDate, long totalNumberOfExistingSubscriptions) {
        this.productVersionId = productVersionId;
        this.endDate = endDate;
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
