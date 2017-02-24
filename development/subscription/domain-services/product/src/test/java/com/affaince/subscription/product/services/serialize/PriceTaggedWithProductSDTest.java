package com.affaince.subscription.product.services.serialize;
import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.command.domain.PriceBucketSample;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by rbsavaliya on 18-02-2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class PriceTaggedWithProductSDTest {

    @Test
    public  void testSerializationOfPRiceBucket() throws IOException {
        PriceTaggedWithProduct priceTaggedWithProduct = new PriceTaggedWithProduct(
                "1", 20, 30, LocalDate.now(), LocalDate.now().plusDays(20)
        );

        ObjectMapper objectMapper = new ObjectMapper();

        String a = objectMapper.writeValueAsString(priceTaggedWithProduct);

        priceTaggedWithProduct = objectMapper.readValue(a, PriceTaggedWithProduct.class);

        System.out.println("$$$$$ taggedPriceVersionID: " + priceTaggedWithProduct.getTaggedPriceVersionId());

        PriceBucketSample priceBucket2 = new PriceBucketSample();
        priceBucket2.setProductId("1");
        priceBucket2.setPriceBucketId("2");
        priceBucket2.setProductPricingCategory(ProductPricingCategory.PRICE_COMMITMENT);
        priceBucket2.setNumberOfNewSubscriptions(2100);
        priceBucket2.setNumberOfChurnedSubscriptions(360);
        priceBucket2.setNumberOfExistingSubscriptions(32000);
        priceBucket2.setNumberOfDeliveredSubscriptions(23000);
        priceBucket2.setExpectedProfit(234567);
        priceBucket2.setRegisteredProfit(245678);
        priceBucket2.setRegisteredPurchaseCostOfDeliveredUnits(123456);
        priceBucket2.setRegisteredRevenue(3445344545.00);
        priceBucket2.setOfferedPriceOrPercentDiscountPerUnit(20);
        priceBucket2.setRegisteredProfit(2000.0);

        //priceBucket2.setF
        priceBucket2.setTaggedPriceVersion(priceTaggedWithProduct);
        priceBucket2.setEntityStatus(EntityStatus.EXPIRED);
        String b = objectMapper.writeValueAsString(priceBucket2);
        PriceBucketSample priceBucket1 = objectMapper.readValue(b, PriceBucketSample.class);
        System.out.println("__________________________________________________________");
        System.out.println(" @@@@@ toString: " + priceBucket1);
        System.out.println("__________________________________________________________");
        System.out.println("$$$$ OfferedPriceOrPercent:" + priceBucket1.getOfferedPriceOrPercentDiscountPerUnit());
    }
}
