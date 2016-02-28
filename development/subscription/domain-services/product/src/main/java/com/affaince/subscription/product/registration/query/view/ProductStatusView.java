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

    //TODO: check if constructor is proper (i.e. default and/or with both parameters is required)
    
    @Id
    private final String productId;
    private final List<ProductStatus> productStatuses;
    {
        productStatuses = new ArrayList<>();
        productStatuses.add(ProductStatus.PRODUCT_NEW);
    }

    public ProductStatusView(String productId) {
        this.productId = productId;
    }

    /*public ProductStatusView(String productId, List<ProductStatus> productStatuses) {
        this.productId = productId;
        this.productStatuses = productStatuses;
    }*/

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
        productStatuses.add(ProductStatus.PRODUCT_NEW);
    }

    //TODO: move this logic to ProductConfigurationValidator
    public /*ProductStatus*/ boolean addProductStatus(ProductStatus productStatus) /*throws InvalidProductStatusException*/ {
        /*switch (productStatus) {
            case PRODUCT_REGISTERED:
                productStatuses.add(productStatus);
                break;
            case PRODUCT_CONFIGURED:
                validateProductStatus(productStatus, ProductStatus.PRODUCT_REGISTERED);
                productStatuses.add(productStatus);
                if(productStatuses.contains(ProductStatus.PRODUCT_FORECASTED)) {
                    productStatuses.add(ProductStatus.PRODUCT_COMPLETED);
                }
                break;
            case PRODUCT_FORECASTED:
                validateProductStatus(productStatus, ProductStatus.PRODUCT_REGISTERED);
                productStatuses.add(productStatus);
                if(productStatuses.contains(ProductStatus.PRODUCT_CONFIGURED)) {
                    productStatuses.add(ProductStatus.PRODUCT_COMPLETED);
                }
                break;
            case PRODUCT_COMPLETED:
                validateProductStatus(productStatus, ProductStatus.PRODUCT_CONFIGURED);
                validateProductStatus(productStatus, ProductStatus.PRODUCT_FORECASTED);
                productStatuses.add(productStatus);
                break;
            case PRODUCT_EXPENSES_DISTRIBUTED:
                validateProductStatus(productStatus, ProductStatus.PRODUCT_COMPLETED);
                productStatuses.add(productStatus);
                break;
            case PRODUCT_ACTIVATED:
                validateProductStatus(productStatus, ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED);
                productStatuses.add(productStatus);
                break;
        }
        return productStatuses.get(productStatuses.size() - 1);*/
        try {
            productStatuses.add(productStatus);
            ProductConfigurationValidator.validateProductConfiguration(this);
            return true;
        } catch (InvalidProductStatusException e) {
            productStatuses.remove(productStatuses.size() - 1);
            return false;
        }
    }

    /*private void validateProductStatus(ProductStatus actualStatus, ProductStatus expectedStatus) throws InvalidProductStatusException {
        if (!productStatuses.contains(expectedStatus)) {
            throw InvalidProductStatusException.build(productId,
                    actualStatus,
                    expectedStatus);
        }
    }*/
}
