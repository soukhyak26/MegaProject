package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.common.type.ProductPricingCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/28/2017.
 */
@Document(collection="ProductView")
public class ProductView {
    @Id
    private String productId;
    private String productName;
    private ProductPricingCategory productPricingCategory;

    public ProductView(String productId, String productName, ProductPricingCategory productPricingCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPricingCategory = productPricingCategory;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }
}
