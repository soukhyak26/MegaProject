package com.verifier.domains.inventory.view;

import org.joda.time.LocalDate;

public class ProductStockOrderView {
    private final String orderId;
    private final String productInventoryId;
    private final String supplierId;
    private final String productId;
    private final long orderCount;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate orderDate;
    private long orderFulfilmentCount;
    private LocalDate orderFulfilmentDate;

    public ProductStockOrderView(String orderId, String productInventoryId, String supplierId, String productId, long orderCount, LocalDate startDate, LocalDate endDate, LocalDate orderDate) {
        this.orderId = orderId;
        this.productInventoryId = productInventoryId;
        this.supplierId = supplierId;
        this.productId = productId;
        this.orderCount = orderCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderDate = orderDate;
        this.orderFulfilmentCount = 0;
        this.orderFulfilmentDate = null;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductInventoryId() {
        return productInventoryId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getProductId() {
        return productId;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public long getOrderFulfilmentCount() {
        return orderFulfilmentCount;
    }

    public void setOrderFulfilmentCount(long orderFulfilmentCount) {
        this.orderFulfilmentCount = orderFulfilmentCount;
    }

    public LocalDate getOrderFulfilmentDate() {
        return orderFulfilmentDate;
    }

    public void setOrderFulfilmentDate(LocalDate orderFulfilmentDate) {
        this.orderFulfilmentDate = orderFulfilmentDate;
    }
}
