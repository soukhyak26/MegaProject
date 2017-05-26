package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rsavaliya on 23/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@Ignore
public class PriceBucketViewRepositoryTest {

    @Autowired
    private PriceBucketViewRepository priceBucketViewRepository;

    @Test
    public void testAddPriceBucketView () {
        String productId = UUID.randomUUID().toString();
        String priceBucketId = UUID.randomUUID().toString();
        PriceBucketView priceBucketView = new PriceBucketView(
                new ProductwisePriceBucketId(productId,priceBucketId), ProductPricingCategory.PRICE_COMMITMENT);
        priceBucketView.setEntityStatus(EntityStatus.ACTIVE);
        priceBucketViewRepository.save(priceBucketView);

        assertThat (priceBucketViewRepository.findOne(new ProductwisePriceBucketId(productId,priceBucketId)), is (priceBucketView));
    }

    @After
    public void shutDown () {
        priceBucketViewRepository.deleteAll();
    }
}
