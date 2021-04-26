package com.verifier.domains.inventory.view;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class InventoryInOutRecordsView {
    @Id
    private String batchId;
    private LocalDate purchaseDate;
    private long inItemCount;
    private PriceTaggedWithProduct taggedPriceVersion;
    private long balanceItemCount;
    private LocalDate expiryDate;

    public InventoryInOutRecordsView(String batchId,long inItemCount, PriceTaggedWithProduct taggedPriceVersion,LocalDate purchaseDate) {
        this.batchId = batchId;
        this.purchaseDate = purchaseDate;
        this.inItemCount = inItemCount;
        this.taggedPriceVersion = taggedPriceVersion;
        this.balanceItemCount = inItemCount;
        this.expiryDate = new LocalDate(9999,12,31);
    }
    public InventoryInOutRecordsView(){}
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
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


    public void setBalanceItemCount(long balanceItemCount) {
        this.balanceItemCount = balanceItemCount;
    }

    public void addToInItemCount(long itemCount) {
        this.inItemCount += itemCount;
        this.balanceItemCount += itemCount;
    }

    public void deductFromBalanceItemCount(long itemsToBeDeducted) {
        this.balanceItemCount -= itemsToBeDeducted;
        //return itemsToBeDeducted*taggedPriceVersion.getPurchasePricePerUnit();
    }

    public double calculateCostOfBalancedGoods() {
        return balanceItemCount*taggedPriceVersion.getPurchasePricePerUnit();
    }

    public String getBatchId() {
        return batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryInOutRecordsView that = (InventoryInOutRecordsView) o;
        return Objects.equals(getPurchaseDate(), that.getPurchaseDate()) &&
                Objects.equals(getTaggedPriceVersion(), that.getTaggedPriceVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPurchaseDate(), getTaggedPriceVersion());
    }

} 
