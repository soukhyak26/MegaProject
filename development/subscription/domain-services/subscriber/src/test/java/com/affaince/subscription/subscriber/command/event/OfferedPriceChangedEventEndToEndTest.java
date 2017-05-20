package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.subscriber.Application;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import com.affaince.subscription.subscriber.query.view.LatestPriceBucketView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 04-09-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@Ignore
public class OfferedPriceChangedEventEndToEndTest {

    @Autowired
    private GenericEventPublisher genericEventPublisher;
    @Autowired
    private LatestPriceBucketViewRepository latestPriceBucketViewRepository;

/*
    @Test
    public void offeredPriceChangedEventEndToEndTest() throws InterruptedException {
        OfferedPriceChangedEvent offeredPriceChangedEvent = new OfferedPriceChangedEvent(
                "1",
                "1",
                ProductPricingCategory.PRICE_COMMITMENT,
                new PriceTaggedWithProduct("1",89,110, LocalDate.now()),
                20,
                2,
                400,
                LocalDateTime.now(),
                new LocalDateTime(9999,12,31,0,0,0),
                EntityStatus.ACTIVE,
                100.0,
                LocalDateTime.now());
        genericEventPublisher.publish(offeredPriceChangedEvent);
        Thread.sleep(100);
        LatestPriceBucketView latestPriceBucketView = latestPriceBucketViewRepository.findOne("1");
        assertThat(latestPriceBucketView.getOfferedPricePerUnit(), is(100.0));
    }
*/
}
