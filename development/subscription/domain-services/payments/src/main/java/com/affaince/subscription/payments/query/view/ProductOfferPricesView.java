package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/26/2017.
 */
@Document()
public class ProductOfferPricesView {
    @Id
    private ProductwisePriceBucketId productwisePriceBucketId;
    private ProductPricingCategory productPricingCategory;
    private double offerPriceOrPercent;

    public ProductOfferPricesView(ProductwisePriceBucketId productwisePriceBucketId, ProductPricingCategory productPricingCategory, double offerPriceOrPercent) {
        this.productwisePriceBucketId = productwisePriceBucketId;
        this.productPricingCategory = productPricingCategory;
        this.offerPriceOrPercent = offerPriceOrPercent;
    }

    public ProductwisePriceBucketId getProductwisePriceBucketId() {
        return productwisePriceBucketId;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public double getOfferPriceOrPercent() {
        return offerPriceOrPercent;
    }

    public void setOfferPriceOrPercent(double offerPriceOrPercent) {
        this.offerPriceOrPercent = offerPriceOrPercent;
    }
}
