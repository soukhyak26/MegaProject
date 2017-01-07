package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 10-07-2016.
 * ActualsView represents daily actual data
 */
@Document(collection = "ProductActualsView")
public class ProductActualsView implements ProductSubscriptionMetricsView, Comparable<ProductActualsView> {
    @Id
    private final ProductVersionId productVersionId;
    private LocalDate endDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;

/*
    public ProductActualsView(ProductVersionId productVersionId, LocalDateTime endDate) {
        this.productVersionId=productVersionId;
        this.endDate=endDate;
    }
*/

    public ProductActualsView(ProductVersionId productVersionId, LocalDate endDate){
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = 0;
        this.churnedSubscriptions = 0;
        this.totalNumberOfExistingSubscriptions = 0;

    }
    public ProductActualsView(ProductVersionId productVersionId, LocalDate endDate, long newSubscriptions, long churnedSubscriptions, long totalNumberOfExistingSubscriptions) {
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        //this.productForecastStatus = productForecastStatus;
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

    public void addToNewSubscriptionCount(long subscriptionCount){
        this.newSubscriptions+=subscriptionCount;
        this.totalNumberOfExistingSubscriptions+=subscriptionCount;
    }
    public void addToChurnedSubscriptionCount(long subscriptionCount){
        this.churnedSubscriptions +=subscriptionCount;
        this.totalNumberOfExistingSubscriptions-=subscriptionCount;
    }
    public void addToTotalSubscriptionCount(long subscriptionCount){
        this.totalNumberOfExistingSubscriptions+=subscriptionCount;
    }
    @Override
    public int compareTo(ProductActualsView o) {
        return this.getProductVersionId().compareTo(o.getProductVersionId());
    }

}
