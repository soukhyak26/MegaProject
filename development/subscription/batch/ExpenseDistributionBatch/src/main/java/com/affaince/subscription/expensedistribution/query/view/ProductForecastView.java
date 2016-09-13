package com.affaince.subscription.expensedistribution.query.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 10-07-2016.
 */
public class ProductForecastView {
    private ProductVersionId productVersionId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;
    private ProductForecastStatus productForecastStatus;

    public ProductForecastView() {
    }

    public ProductVersionId getProductVersionId() {
        return productVersionId;
    }

    public void setProductVersionId(ProductVersionId productVersionId) {
        this.productVersionId = productVersionId;
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
}
