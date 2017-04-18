package com.affaince.subscription.product.factory;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.command.domain.*;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 4/10/2017.
 */
@Component
public class PriceBucketFactory {


    public static PriceBucket createPriceBucket(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercentDiscountPerUnit, EntityStatus entityStatus, LocalDateTime fromDate){
        if (productPricingCategory == ProductPricingCategory.PRICE_COMMITMENT){
            return new ValueCommittedPriceBucket(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate);
        }else if(productPricingCategory == ProductPricingCategory.DISCOUNT_COMMITMENT){
            return new PercentCommittedPriceBucket(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate);
        }else{
            return new NoneCommittedPriceBucket(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate);
        }
    }
}
