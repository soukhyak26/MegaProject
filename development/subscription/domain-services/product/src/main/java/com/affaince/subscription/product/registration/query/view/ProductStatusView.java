package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.registration.web.exception.InvalidProductStatusException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anayonkar on 13/2/16.
 */
@Document(collection = "ProductStatusView")
public class ProductStatusView {
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

    public String getProductId() {
        return productId;
    }

    public List<ProductStatus> getProductStatuses() {
        return productStatuses;
    }

    public void addProductStatus(ProductStatus productStatus) throws InvalidProductStatusException {
        switch (productStatus) {
            case PRODUCT_CONFIGURED:
            case PRODUCT_FORCASTED:
                validateProductStatus(productStatus, ProductStatus.PRODUCT_REGISTERED);
                productStatuses.add(productStatus);
                if(productStatuses.contains(ProductStatus.PRODUCT_CONFIGURED)
                        && productStatuses.contains(ProductStatus.PRODUCT_FORCASTED)) {
                    productStatuses.add(ProductStatus.PRODUCT_COMPLETED);
                }
                break;
            case PRODUCT_EXPENSES_DISTRIBUTED:
            case PRODUCT_ACTIVATED:
                validateProductStatus(productStatus,
                        ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED.equals(productStatus) ?
                                ProductStatus.PRODUCT_COMPLETED :
                                ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED);
                productStatuses.add(productStatus);
                break;
        }
    }

    private void validateProductStatus(ProductStatus actualStatus, ProductStatus expectedStatus) throws InvalidProductStatusException {
        if (!productStatuses.contains(expectedStatus)) {
            throw InvalidProductStatusException.build(productId,
                    actualStatus,
                    expectedStatus);
        }
    }
}
