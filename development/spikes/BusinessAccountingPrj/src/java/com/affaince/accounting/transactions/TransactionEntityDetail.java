package com.affaince.accounting.transactions;


//misfit member currently unused.
public class TransactionEntityDetail {
    private String entityId;
    private double ratePerUnit;
    private double quantity ;
    private String supplierId;
    private double discountInPercent;
    private String description ;

    public TransactionEntityDetail(String entityId, double ratePerUnit, double quantity, String supplierId, double discountInPercent, String description) {
        this.entityId = entityId;
        this.ratePerUnit = ratePerUnit;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.discountInPercent = discountInPercent;
        this.description = description;
    }

    public TransactionEntityDetail() {
    }

    public String getEntityId() {
        return entityId;
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
