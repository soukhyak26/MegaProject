package com.verifier.domains.fulfillment.view;

import com.verifier.domains.fulfillment.vo.ProductInventoryUpdateId;
import org.joda.time.LocalDate;

public class ProductStockProvisionRequestProxy {
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
}
