package com.affaince.subscription.pricing.query.view;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandark on 28-01-2016.
 */

@Document
public class PriceBucketView implements Comparable <PriceBucketView> {
    @Id
    private ProductVersionId productVersionId;
    private LocalDate toDate;
    private double purchasePricePerUnit;
    private double offeredPricePerUnit;
    private double MRP;
    private double fixedOperatingExpPerUnit;
    private double variableOperatingExpPerUnit;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;
    private EntityStatus entityStatus;
    private double slope;

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

    public double getPurchasePricePerUnit() {
        return this.purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public double getOfferedPricePerUnit() {
        return this.offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }

    public double getMRP() {
        return this.MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
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

    @Override
    public int compareTo(PriceBucketView o) {
        return this.getFromDate().compareTo(o.getFromDate());
    }

    public double getSlope() {
        return this.slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getFixedOperatingExpPerUnit() {
        return this.fixedOperatingExpPerUnit;
    }

    public void setFixedOperatingExpPerUnit(double fixedOperatingExpPerUnit) {
        this.fixedOperatingExpPerUnit = fixedOperatingExpPerUnit;
    }

    public double getVariableOperatingExpPerUnit() {
        return this.variableOperatingExpPerUnit;
    }

    public void setVariableOperatingExpPerUnit(double variableOperatingExpPerUnit) {
        this.variableOperatingExpPerUnit = variableOperatingExpPerUnit;
    }

    public double calculateProfitPerBucket(){
        return (this.numberOfExistingCustomersAssociatedWithAPrice*this.offeredPricePerUnit)-(this.numberOfExistingCustomersAssociatedWithAPrice*(this.purchasePricePerUnit+this.fixedOperatingExpPerUnit+this.variableOperatingExpPerUnit));
    }
}
