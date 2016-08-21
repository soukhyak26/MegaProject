package com.affaince.subscription.pricing.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
import com.affaince.subscription.product.command.domain.ProductPricingCategory;
import com.affaince.subscription.product.vo.DemandWiseProfitSharingRule;
import com.affaince.subscription.product.vo.PricingStrategyType;
*/

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Document(collection = "Product")
public class ProductView {

    @Id
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
