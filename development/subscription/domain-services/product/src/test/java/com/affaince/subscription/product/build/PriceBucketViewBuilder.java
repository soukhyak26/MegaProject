package com.affaince.subscription.product.build;

import com.affaince.subscription.common.type.ProductPricingCategory;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by mandar on 6/25/2017.
 */
public class PriceBucketViewBuilder {


    private String obtainPriceBucketId(String productId, String latestPriceBucketId, ProductPricingCategory productPricingCategory, LocalDateTime fromDate){
        if(productPricingCategory == ProductPricingCategory.DISCOUNT_COMMITMENT || productPricingCategory== ProductPricingCategory.PRICE_COMMITMENT) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            return priceBucketId;
        }else{
            return latestPriceBucketId;
        }
    }

}
