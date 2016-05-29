package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandark on 28-01-2016.
 */

@Document
public class PriceBucketView {
    @Id
    private ProductVersionId productVersionId;
    private LocalDate toDate;
    private double offeredPricePerUnit;
    private double percentDiscountPerUnit;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;
    private EntityStatus entityStatus;

    public String getProductId() {
        return productVersionId.getProductId();
    }

    public void setProductId(String productId) {
        this.productVersionId.setProductId(productId);
    }

    public LocalDate getFromDate() {
        return this.productVersionId.getFromDate();
    }

    public void setFromDate(LocalDate fromDate) {
        this.productVersionId.setFromDate(fromDate);
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public double getOfferedPricePerUnit() {
        return this.offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }

    public long getNumberOfNewCustomersAssociatedWithAPrice() {
        return this.numberOfNewCustomersAssociatedWithAPrice;
    }

    public void setNumberOfNewCustomersAssociatedWithAPrice(long numberOfNewCustomersAssociatedWithAPrice) {
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
    }

    public long getNumberOfChurnedCustomersAssociatedWithAPrice() {
        return this.numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public void setNumberOfChurnedCustomersAssociatedWithAPrice(long numberOfChurnedCustomersAssociatedWithAPrice) {
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public long getNumberOfExistingCustomersAssociatedWithAPrice() {
        return this.numberOfExistingCustomersAssociatedWithAPrice;
    }

    public void setNumberOfExistingCustomersAssociatedWithAPrice(long numberOfExistingCustomersAssociatedWithAPrice) {
        this.numberOfExistingCustomersAssociatedWithAPrice = numberOfExistingCustomersAssociatedWithAPrice;
    }

    public ProductVersionId getProductVersionId() {
        return this.productVersionId;
    }

    public void setProductVersionId(ProductVersionId productVersionId) {
        this.productVersionId = productVersionId;
    }

    public EntityStatus getEntityStatus() {
        return this.entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }
}
