package com.verifier.domains.fulfillment.view;

import com.verifier.domains.fulfillment.vo.ProductInventoryUpdateId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductStockProvisionRequestProxy {
    @Id
    private ProductInventoryUpdateId productInventoryUpdateId;
    private long receivedProductCount;
    private double purchasePricePerUnit;
    private double MRP;
    private LocalDate periodStartDate;
    private LocalDate periodEndDate;


    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public long getReceivedProductCount() {
        return receivedProductCount;
    }

    public LocalDate getPeriodStartDate() {
        return periodStartDate;
    }

    public LocalDate getPeriodEndDate() {
        return periodEndDate;
    }

    public ProductInventoryUpdateId getProductInventoryUpdateId() {
        return productInventoryUpdateId;
    }

    public void setProductInventoryUpdateId(ProductInventoryUpdateId productInventoryUpdateId) {
        this.productInventoryUpdateId = productInventoryUpdateId;
    }

    public void setReceivedProductCount(long receivedProductCount) {
        this.receivedProductCount = receivedProductCount;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public void setPeriodStartDate(LocalDate periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public void setPeriodEndDate(LocalDate periodEndDate) {
        this.periodEndDate = periodEndDate;
    }
}
