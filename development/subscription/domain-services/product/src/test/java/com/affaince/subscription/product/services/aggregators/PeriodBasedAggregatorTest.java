package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PeriodBasedAggregatorTest {

    private List<ProductActualsView> productActualsViews = new ArrayList<>(365);

    private List<ProductActualsView> expectedAggregateViews = new ArrayList<>(13);

    @Before
    public void init() throws Exception {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/actualViewSim.csv"))))) {
            List<List<String>> values = fileReader.lines().map(line -> Arrays.asList(line.trim().split(","))).collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
            for (List<String> value : values) {
                ProductActualsView actualView = new ProductActualsView(
                        new ProductVersionId(value.get(0), LocalDateTime.parse(value.get(1), formatter)),
                        LocalDateTime.parse(value.get(2), formatter));
                actualView.setNewSubscriptions(Long.parseLong(value.get(3)));
                actualView.setChurnedSubscriptions(Long.parseLong(value.get(4)));
                actualView.setTotalNumberOfExistingSubscriptions(Long.parseLong(value.get(5)));
                productActualsViews.add(actualView);
            }
        }


        int period = 30;
        for (int periodIndex = 1; periodIndex <= 12; periodIndex++) {
            ProductActualsView aggregateView = null;
            String productId = null;
            LocalDateTime startDate = null;
            LocalDateTime endDate = null;
            for (int index = period * (periodIndex - 1); index < period * periodIndex; index++) {
                ProductActualsView view = productActualsViews.get(index);
                if (null == productId) {
                    productId = view.getProductVersionId().getProductId();
                }

                if (null == aggregateView) {
                    startDate = view.getProductVersionId().getFromDate();
                    endDate = view.getEndDate();
                    aggregateView = new ProductActualsView(new ProductVersionId(productId, startDate), endDate.plusDays(period));
                }
                aggregateView.setChurnedSubscriptions(aggregateView.getChurnedSubscriptions() + view.getChurnedSubscriptions());
                aggregateView.setTotalNumberOfExistingSubscriptions(view.getTotalNumberOfExistingSubscriptions());
                aggregateView.setNewSubscriptions(aggregateView.getNewSubscriptions() + view.getNewSubscriptions());
            }
            expectedAggregateViews.add(aggregateView);
        }
    }

    @Test
    public void testAggregateForChunkPeriod30() {
        final MetricsAggregator<ProductActualsView> periodBasedAggregator = new PeriodBasedAggregator();
        final List<ProductActualsView> aggregateViews = periodBasedAggregator.aggregate(productActualsViews, 30);

        int temp = 0;
        for (ProductActualsView productActualsView : aggregateViews) {
            assertThat (productActualsView.getProductVersionId(), is (expectedAggregateViews.get(temp).getProductVersionId()));
            System.out.println("Checking churnedSubscriptions aggregation");
            assertThat(productActualsView.getChurnedSubscriptions(), is(expectedAggregateViews.get(temp).getChurnedSubscriptions()));
            System.out.println("Checking new Subscriptions aggregation");
            assertThat(productActualsView.getNewSubscriptions(), is(expectedAggregateViews.get(temp).getNewSubscriptions()));
            System.out.println("Checking TOTAL Subscriptions aggregation");
            assertThat(productActualsView.getTotalNumberOfExistingSubscriptions(), is(expectedAggregateViews.get(temp).getTotalNumberOfExistingSubscriptions()));
            temp++;
        }
    }
}
