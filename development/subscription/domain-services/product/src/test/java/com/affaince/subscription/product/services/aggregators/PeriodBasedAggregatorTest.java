package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
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

    private List<ProductForecastView> productForecastViews = new ArrayList<>(365);
    private List<ProductActualsView> productActualsViews = new ArrayList<>(365);

    private List<ProductForecastView> expectedAggregateViews = new ArrayList<>(13);
    private List<ProductActualsView> expectedAggregateActualsViews = new ArrayList<>(13);

    @Before
    public void init() throws Exception {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/actualViewSim.csv"))))) {
            List<List<String>> values = fileReader.lines().map(line -> Arrays.asList(line.trim().split(","))).collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-yy");
            for (List<String> value : values) {
                ProductForecastView productForecastView = new ProductForecastView(
                        new ForecastVersionId(value.get(0), LocalDate.parse(value.get(1), formatter),SysDate.now()),
                        LocalDate.parse(value.get(2), formatter),
                        Long.parseLong(value.get(3)),
                        Long.parseLong(value.get(4)),
                        Long.parseLong(value.get(5)));
                productForecastViews.add(productForecastView);

                ProductActualsView productActualsView = new ProductActualsView(
                        new ProductVersionId(value.get(0), LocalDate.parse(value.get(1), formatter)),
                        LocalDate.parse(value.get(2), formatter),
                        Long.parseLong(value.get(3)),
                        Long.parseLong(value.get(4)),
                        Long.parseLong(value.get(5)));
                productActualsViews.add(productActualsView);

            }
        }


        int period = 30;
        for (int periodIndex = 1; periodIndex <= 12; periodIndex++) {
            ProductForecastView firstView = productForecastViews.get(0);
            ProductForecastView aggregateView = new ProductForecastView((ForecastVersionId)firstView.getProductVersionId(), firstView.getEndDate(), 0, 0, 0);
            String productId = null;
            LocalDate startDate = null;
            LocalDate endDate = null;
            for (int index = period * (periodIndex - 1); index < period * periodIndex; index++) {
                ProductForecastView view = productForecastViews.get(index);
                startDate = view.getProductVersionId().getFromDate();
                endDate = view.getEndDate();
                aggregateView.getProductVersionId().setFromDate(startDate);
                aggregateView.setEndDate(endDate);
                aggregateView.setNewSubscriptions(aggregateView.getNewSubscriptions() + view.getNewSubscriptions());
                aggregateView.setChurnedSubscriptions(aggregateView.getChurnedSubscriptions() + view.getChurnedSubscriptions());
                aggregateView.setTotalNumberOfExistingSubscriptions(view.getTotalNumberOfExistingSubscriptions());
            }
            expectedAggregateViews.add(aggregateView);
        }

        for (int periodIndex = 1; periodIndex <= 12; periodIndex++) {
            ProductActualsView firstView = productActualsViews.get(0);
            ProductActualsView aggregateView = new ProductActualsView(firstView.getProductVersionId(), firstView.getEndDate(), 0, 0, 0);
            String productId = null;
            LocalDate startDate = null;
            LocalDate endDate = null;
            for (int index = period * (periodIndex - 1); index < period * periodIndex; index++) {
                ProductActualsView view = productActualsViews.get(index);
                startDate = view.getProductVersionId().getFromDate();
                endDate = view.getEndDate();
                aggregateView.getProductVersionId().setFromDate(startDate);
                aggregateView.setEndDate(endDate);
                aggregateView.setNewSubscriptions(aggregateView.getNewSubscriptions() + view.getNewSubscriptions());
                aggregateView.setChurnedSubscriptions(aggregateView.getChurnedSubscriptions() + view.getChurnedSubscriptions());
                aggregateView.setTotalNumberOfExistingSubscriptions(view.getTotalNumberOfExistingSubscriptions());
            }
            expectedAggregateActualsViews.add(aggregateView);
        }

    }

    @Test
    public void testAggregateForChunkPeriod30() {
        final PeriodBasedAggregator<ProductForecastView> periodBasedAggregator = new PeriodBasedAggregator();
        final List<ProductForecastView> aggregateViews = periodBasedAggregator.aggregate(productForecastViews, 30);

        int temp = 0;
        for (ProductForecastView productForecastView : aggregateViews) {
            assertThat(productForecastView.getProductVersionId(), is(expectedAggregateViews.get(temp).getProductVersionId()));
            System.out.println("Checking churnedSubscriptions aggregation");
            assertThat(productForecastView.getChurnedSubscriptions(), is(expectedAggregateViews.get(temp).getChurnedSubscriptions()));
            System.out.println("Checking new Subscriptions aggregation");
            assertThat(productForecastView.getNewSubscriptions(), is(expectedAggregateViews.get(temp).getNewSubscriptions()));
            System.out.println("Checking TOTAL Subscriptions aggregation");
            assertThat(productForecastView.getTotalNumberOfExistingSubscriptions(), is(expectedAggregateViews.get(temp).getTotalNumberOfExistingSubscriptions()));
            temp++;
        }
    }

    @Test
    public void testAggregateForChunkPeriod30ForActuals() {
        final PeriodBasedAggregator<ProductActualsView> periodBasedAggregator = new PeriodBasedAggregator();
        final List<ProductActualsView> aggregateViews = periodBasedAggregator.aggregate(productActualsViews, 30);

        int temp = 0;
        for (ProductActualsView productActualsView : aggregateViews) {
            assertThat(productActualsView.getProductVersionId(), is(expectedAggregateActualsViews.get(temp).getProductVersionId()));
            System.out.println("Checking churnedSubscriptions aggregation");
            assertThat(productActualsView.getChurnedSubscriptions(), is(expectedAggregateActualsViews.get(temp).getChurnedSubscriptions()));
            System.out.println("Checking new Subscriptions aggregation");
            assertThat(productActualsView.getNewSubscriptions(), is(expectedAggregateActualsViews.get(temp).getNewSubscriptions()));
            System.out.println("Checking TOTAL Subscriptions aggregation");
            assertThat(productActualsView.getTotalNumberOfExistingSubscriptions(), is(expectedAggregateActualsViews.get(temp).getTotalNumberOfExistingSubscriptions()));
            temp++;
        }
    }

}
