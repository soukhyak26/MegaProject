package com.affaince.subscription.testdata.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 30-12-2016.
 */
public class ProductDetails {
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private int quantity;
    private String quantityUnit;
    private List<String> substitute = new ArrayList<>();
    private List<String> complements =  new ArrayList<>();
    private double purchasePrice;
    private double MRP;

    public ProductDetails(String productId, String productName, String categoryId, String subCategoryId, int quantity, String quantityUnit, List<String> substitute, List<String> complements, double purchasePrice, double MRP) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.substitute = substitute;
        this.complements = complements;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public List<String> getSubstitute() {
        return substitute;
    }

    public void setSubstitute(List<String> substitute) {
        this.substitute = substitute;
    }

    public List<String> getComplements() {
        return complements;
    }

    public void setComplements(List<String> complements) {
        this.complements = complements;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }
}
