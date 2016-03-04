package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.registration.validator.ProductConfigurationValidator;
import com.affaince.subscription.product.registration.web.exception.InvalidProductStatusException;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by anayonkar on 13/2/16.
 */
@Document(collection = "ProductStatusView")
public class ProductStatusView {

    @Id
    private String productId;
    private List<ProductStatus> productStatuses;

    public ProductStatusView() {

    }

    public ProductStatusView(String productId, List<ProductStatus> productStatuses) {
        this.productId = productId;
        if(productStatuses == null) {
            this.productStatuses = new ArrayList<>();
        } else {
            this.productStatuses = productStatuses;
        }
    }

    public String getProductId() {
        return productId;
    }

    public List<ProductStatus> getProductStatuses() {
        return productStatuses;
    }

    public ProductStatus getLatestProductStatus() {
        return productStatuses.get(productStatuses.size() -1);
    }

    public void flushProductStatuses() {
        productStatuses.clear();
    }

    public boolean addProductStatus(ProductStatus productStatus) {
        try {
            productStatuses.add(productStatus);
            ProductConfigurationValidator.validateProductConfiguration(this);
            return true;
        } catch (InvalidProductStatusException e) {
            productStatuses.remove(productStatuses.size() - 1);
            return false;
        }
    }

}
