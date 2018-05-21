package com.verifier.domains.business.view;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandark on 26-01-2016.
 */
@Document(collection = "ProductView")
public class ProductView {
    @Id
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private double purchasePrice;
    private double MRP;
    private SensitivityCharacteristic sensitiveTo;
    private ProductStatus productStatus;
    private long totalAnticipatedSubscriptions;
    private double currentOperatingExpensePerUnit;
    private double productPurchaseBudgetedAmount;

    public ProductView(){}
    public ProductView(String productId, String productName, String categoryId, String subCategoryId, SensitivityCharacteristic sensitiveTo, double purchasePrice, double MRP, ProductStatus productStatus) {
        this.productId = productId;
        this.productName=productName;
        this.categoryId=categoryId;
        this.subCategoryId=subCategoryId;
        this.sensitiveTo = sensitiveTo;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.productStatus = productStatus;
        this.productPurchaseBudgetedAmount = 0;

    }


    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public SensitivityCharacteristic getSensitiveTo() {
        return this.sensitiveTo;
    }

    public void setSensitiveTo(SensitivityCharacteristic sensitiveTo) {
        this.sensitiveTo = sensitiveTo;
    }

    public double getCurrentOperatingExpensePerUnit() {
        return this.currentOperatingExpensePerUnit;
    }

    public void setCurrentOperatingExpensePerUnit(double currentOperatingExpensePerUnit) {
        this.currentOperatingExpensePerUnit = currentOperatingExpensePerUnit;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public double getProductPurchaseBudgetedAmount() {
        return productPurchaseBudgetedAmount;
    }

    public void setProductPurchaseBudgetedAmount(double productPurchaseBudgetedAmount) {
        this.productPurchaseBudgetedAmount = productPurchaseBudgetedAmount;
    }

    public long getTotalAnticipatedSubscriptions() {
        return totalAnticipatedSubscriptions;
    }

    public void setTotalAnticipatedSubscriptions(long totalAnticipatedSubscriptions) {
        this.totalAnticipatedSubscriptions = totalAnticipatedSubscriptions;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }
}
