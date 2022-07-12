package com.affaince.accounting.journal.entity;

public class TransactionEntityDetail {
    private String itemId;
    private double ratePerUnit;
    private double quantity ;
    private String supplierId;
    private double discountInPercent;
    private String description ;

    public TransactionEntityDetail(String itemId, double ratePerUnit, double quantity, String supplierId, double discountInPercent, String description) {
        this.itemId = itemId;
        this.ratePerUnit = ratePerUnit;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.discountInPercent = discountInPercent;
        this.description = description;
    }

    public TransactionEntityDetail() {
    }

    public String getItemId() {
        return itemId;
    }

    public double getRatePerUnit() {
        return ratePerUnit;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public double getDiscountInPercent() {
        return discountInPercent;
    }

    public String getDescription() {
        return description;
    }
}
