package com.affaince.subscription.product.registration.command;

import com.affaince.subscription.common.type.QuantityUnit;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class RegisterProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private long quantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;

    public RegisterProductCommand(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.substitutes = substitutes;
        this.complements = complements;
    }

    public RegisterProductCommand() {
    }

    public String getProductId() {
        return productId;
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

    public long getQuantity() {
        return quantity;
    }

    public QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }

    public List<String> getSubstitutes() {
        return substitutes;
    }

    public List<String> getComplements() {
        return complements;
    }
}
