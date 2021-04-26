package com.verifier.domains.inventory.vo;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;

import java.util.Objects;

public class InventoryInOutRecord {
    private String batchId;
    private LocalDate purchaseDate;
    private long inItemCount;
    private PriceTaggedWithProduct taggedPriceVersion;
    private long balanceItemCount;

    public InventoryInOutRecord(String batchId, long inItemCount, PriceTaggedWithProduct taggedPriceVersion, LocalDate purchaseDate) {
        this.batchId = batchId;
        this.purchaseDate = purchaseDate;
        this.inItemCount = inItemCount;
        this.taggedPriceVersion = taggedPriceVersion;
        this.balanceItemCount = inItemCount;
    }
    public InventoryInOutRecord(){}
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String getBatchId() {
        return batchId;
    }

    public long getInItemCount() {
        return inItemCount;
    }

    public long getBalanceItemCount() {
        return balanceItemCount;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public boolean deductFromBalanceItemCount(long itemsToBeDeducted) {
        this.balanceItemCount -= itemsToBeDeducted;
        return this.balanceItemCount > 0;
    }

    public double calculateCostOfBalancedGoods() {
        return balanceItemCount * taggedPriceVersion.getPurchasePricePerUnit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryInOutRecord that = (InventoryInOutRecord) o;
        return Objects.equals(getPurchaseDate(), that.getPurchaseDate()) &&
                Objects.equals(getTaggedPriceVersion(), that.getTaggedPriceVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPurchaseDate(), getTaggedPriceVersion());
    }
}
