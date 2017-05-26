package com.affaince.subscription.product.factory;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.command.domain.*;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 4/10/2017.
 */
@Component
public class PriceBucketFactory {


    public static PriceBucket createPriceBucket(String productId, String latestPriceBucketId, ProductPricingCategory productPricingCategory, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercentDiscountPerUnit, EntityStatus entityStatus, LocalDateTime fromDate){
        String priceBucketId=obtainPriceBucketId(productId,latestPriceBucketId,productPricingCategory,fromDate);
        if (productPricingCategory == ProductPricingCategory.PRICE_COMMITMENT){
            return new ValueCommittedPriceBucket(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate);
        }else if(productPricingCategory == ProductPricingCategory.DISCOUNT_COMMITMENT){
            return new PercentCommittedPriceBucket(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate);
        }else{
            return new NoneCommittedPriceBucket(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate);
        }
    }

    private static String obtainPriceBucketId(String productId,String latestPriceBucketId,ProductPricingCategory productPricingCategory,LocalDateTime fromDate){
        if(productPricingCategory == ProductPricingCategory.DISCOUNT_COMMITMENT || productPricingCategory== ProductPricingCategory.PRICE_COMMITMENT) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            return priceBucketId;
        }else{
            return latestPriceBucketId;
        }
    }

}
