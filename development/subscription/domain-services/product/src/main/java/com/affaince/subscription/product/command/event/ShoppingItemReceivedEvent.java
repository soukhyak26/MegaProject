package com.affaince.subscription.product.command.event;


/**
 * Created by mandark on 07-09-2015.
 */
public class ShoppingItemReceivedEvent {
    private String shoppingItemId;
    private String categroyId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private String productId;

    public ShoppingItemReceivedEvent() {
    }

    public String getShoppingItemId() {
        return this.shoppingItemId;
    }

    public void setShoppingItemId(String shoppingItemId) {
        this.shoppingItemId = shoppingItemId;
    }

    public String getCategroyId() {
        return this.categroyId;
    }

    public void setCategroyId(String categroyId) {
        this.categroyId = categroyId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return this.subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
