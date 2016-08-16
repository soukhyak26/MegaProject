package com.affaince.subscription.pricing.query.view;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

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


    public ProductView(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, Map<SensitivityCharacteristic, Double> sensitiveTo) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
