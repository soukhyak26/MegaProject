package com.affaince.subscription.expensedistribution.vo;

/**
 * Created by rsavaliya on 13/2/16.
 */
public class Product {
    private String productId;
    private String weight;
    private int itemCountPerDelivery;
    private double purchasePrice;

    public Product(String productId, String weight, double purchasePrice) {
        this.productId = productId;
        this.weight = weight;
        this.purchasePrice = purchasePrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getWeight() {
        return weight;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productId.equals(product.productId);

    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}
