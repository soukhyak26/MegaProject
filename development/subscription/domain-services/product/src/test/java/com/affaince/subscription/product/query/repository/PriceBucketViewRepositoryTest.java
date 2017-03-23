package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rsavaliya on 23/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class PriceBucketViewRepositoryTest {

    @Autowired
    private PriceBucketViewRepository priceBucketViewRepository;

    @Test
    public void testAddPriceBucketView () {
        PriceBucketView priceBucketView = new PriceBucketView(
                new ProductwisePriceBucketId("9e8b25bc-d74e-3838-b08f-df35d858c8f9","9e8b25bc-d74e-3838-b08f-df35d858c8f904022017")
        );
        priceBucketView.setEntityStatus(EntityStatus.ACTIVE);
        priceBucketViewRepository.save(priceBucketView);

        assertThat (priceBucketViewRepository.findOne(new ProductwisePriceBucketId("9e8b25bc-d74e-3838-b08f-df35d858c8f9",
                "9e8b25bc-d74e-3838-b08f-df35d858c8f904022017")), is (priceBucketView));
    }

    @After
    public void shutDown () {
        priceBucketViewRepository.deleteAll();
    }
}
