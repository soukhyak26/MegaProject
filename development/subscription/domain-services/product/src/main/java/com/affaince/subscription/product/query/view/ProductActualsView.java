package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.services.aggregators.AggregationVisitor;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 10-07-2016.
 * ActualsView represents daily actual data
 */
@Document(collection = "ProductActualsView")
public class ProductActualsView implements Comparable<ProductActualsView>, AggregationVisitor<ProductActualsView> {
    @Id
    private final ProductVersionId productVersionId;
    private LocalDateTime endDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;

    public ProductActualsView(ProductVersionId productVersionId, LocalDateTime endDate) {
        this.productVersionId=productVersionId;
        this.endDate=endDate;
    }

    public ProductVersionId getProductVersionId() {
        return productVersionId;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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

    @Override
    public int compareTo(ProductActualsView o) {
        return this.getProductVersionId().compareTo(o.getProductVersionId());
    }

    @Override
    public ProductActualsView visit(ProductActualsView aggregatedView) {
        aggregatedView.setNewSubscriptions(aggregatedView.getNewSubscriptions() + this.getNewSubscriptions());
        aggregatedView.setChurnedSubscriptions(aggregatedView.getChurnedSubscriptions() + this.getChurnedSubscriptions());
        aggregatedView.setTotalNumberOfExistingSubscriptions(this.getTotalNumberOfExistingSubscriptions());
        // aggregatedView.setEndDate(this.getEndDate());
        return aggregatedView;
    }
}
