package com.affaince.subscription.distribution.sampler;

import com.affaince.subscription.common.type.QuantityUnit;

public class Product {
    private final String merchantId;
    private final String productId;
    private final String categoryId;
    private final String productName;
    private final long netQuantity;
    private final QuantityUnit quantityUnit;

    public Product(String merchantId, String productId, String categoryId, String productName, long netQuantity, QuantityUnit quantityUnit) {
        this.merchantId = merchantId;
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.netQuantity = netQuantity;
        this.quantityUnit = quantityUnit;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getProductId() {
        return productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public long getNetQuantity() {
        return netQuantity;
    }

    public QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }
}
