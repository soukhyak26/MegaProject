package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 02-10-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ProductDemandForecastBuilderTest {
    @Autowired
    ProductDemandForecastBuilder builder;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    @Before
    public void setup() throws Exception {
        List<ProductActualsView> productActualsViews = new ArrayList<>(365);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/actualViewSim.csv"))))) {
            List<List<String>> values = fileReader.lines().map(line -> Arrays.asList(line.trim().split(","))).collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
            for (List<String> value : values) {
                ProductVersionId productVersionId = new ProductVersionId(value.get(0), LocalDateTime.parse(value.get(1), formatter));
                LocalDateTime endDate = LocalDateTime.parse(value.get(2), formatter);
                long newSubscriptionCount = Long.parseLong(value.get(3));
                long churnedSubscriptionCount = Long.parseLong(value.get(4));
                long totalNumberOfExistingSubscriptionCount = Long.parseLong(value.get(5));
                ProductActualsView productActualsView = new ProductActualsView(productVersionId, endDate, newSubscriptionCount, churnedSubscriptionCount, totalNumberOfExistingSubscriptionCount);
                productActualsViews.add(productActualsView);

            }
            productActualsViewRepository.save(productActualsViews);
        }
    }

    @After
    public void shutdown() {
        productActualsViewRepository.deleteAll();
    }

    @Test
    public void testForeast() {
        List<ProductForecastView> forecasts = builder.buildForecast("1", SysDate.now(), 30, 730);
        for (ProductForecastView forecast : forecasts) {
            System.out.println("New subscriptions: " + forecast.getNewSubscriptions());
            System.out.println("churned subscriptions: " + forecast.getChurnedSubscriptions());
            System.out.println("total subscriptions: " + forecast.getTotalNumberOfExistingSubscriptions());
            System.out.println("start date: " + forecast.getProductVersionId().getFromDate());
            System.out.println("end date: " + forecast.getEndDate());
            System.out.println("___________________________________________________________________");
        }
    }
}
