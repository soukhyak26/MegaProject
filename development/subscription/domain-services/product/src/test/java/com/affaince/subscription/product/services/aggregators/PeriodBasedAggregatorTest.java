package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 14-08-2016.
 */
public class PeriodBasedAggregatorTest {

    private List <ProductActualsView> productActualsViews = new ArrayList<>(1000);

    private List <ProductActualsView> expectedAggregateViews = new ArrayList<>(33);
    private LocalDateTime date;

    @Before
    public void init () {
        date = SysDateTime.now();
        for (int i=0;i<1000;i++) {
            LocalDateTime startEndDate = date.minusDays(1000 - i);
            ProductActualsView productActualsView = new ProductActualsView(
                    new ProductVersionId("1", startEndDate), startEndDate
            );
            Random randomObj = new Random();
            productActualsView.setChurnedSubscriptions(randomObj.longs(10,30).findFirst().getAsLong());
            productActualsView.setNewSubscriptions(randomObj.longs(100,500).findFirst().getAsLong());
            productActualsViews.add(productActualsView);
        }
        int temp = 999;
        for (int i=0; i<33 ; i++) {
            ProductActualsView productActualsView = new ProductActualsView(
                    new ProductVersionId("1", date.minusDays(30 * (i + 1))), date.minusDays(30 * i));
            long newSubscription = 0;
            long churnSubscription = 0;
            for (int k=0;k<30;k++) {
                newSubscription += productActualsViews.get(temp).getNewSubscriptions();
                churnSubscription += productActualsViews.get(temp).getChurnedSubscriptions();
                temp--;
            }
            productActualsView.setNewSubscriptions(newSubscription);
            productActualsView.setChurnedSubscriptions(churnSubscription);
            expectedAggregateViews.add(productActualsView);
        }
    }

    @Test
    public void testAggregateForChunkPeriod30 () {
        final MetricsAggregator <ProductActualsView> periodBasedAggregator = new PeriodBasedAggregator ();
        final List <ProductActualsView> aggregateViews = periodBasedAggregator.aggregate(productActualsViews, 30);

        int temp = 0;
        for (ProductActualsView productActualsView: aggregateViews) {
            assertThat (productActualsView.getProductVersionId(), is (expectedAggregateViews.get(temp).getProductVersionId()));
            assertThat (productActualsView.getChurnedSubscriptions(), is (expectedAggregateViews.get(temp).getChurnedSubscriptions()));
            assertThat (productActualsView.getNewSubscriptions(), is (expectedAggregateViews.get(temp).getNewSubscriptions()));
            temp++;
        }
    }
}
