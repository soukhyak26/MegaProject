package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.build.ProductActualsViewBuilder;
import com.affaince.subscription.product.query.view.ProductSubscriptionMetricsView;
import org.apache.spark.SparkContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by mandar on 02-10-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@Ignore
public class ProductDemandForecastBuilderTest {
    @Autowired
    ProductDemandForecastBuilder builder;
    @Autowired
    ProductActualsViewBuilder productActualsViewBuilder;
    @Autowired
    SparkContext sparkContext;
    @Before
    public void setup() throws Exception {;
/*
        List<ProductActualsView> productActualsViews = new ArrayList<>(365);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/actualViewSim.csv"))))) {
            List<List<String>> values = fileReader.lines().map(line -> Arrays.asList(line.trim().split(","))).collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
            for (int i=0;i<11;i++) {
                List<String> value=values.get(i);
                ProductVersionId productVersionId = new ProductVersionId(value.get(0), LocalDate.parse(value.get(1), formatter));
                LocalDate endDate = LocalDate.parse(value.get(2), formatter);
                long newSubscriptionCount = Long.parseLong(value.get(3));
                long churnedSubscriptionCount = Long.parseLong(value.get(4));
                long totalNumberOfExistingSubscriptionCount = Long.parseLong(value.get(5));
                ProductActualsView productActualsView = new ProductActualsView(productVersionId, endDate, newSubscriptionCount, churnedSubscriptionCount, totalNumberOfExistingSubscriptionCount);
                productActualsViews.add(productActualsView);

            }
            productActualsViewRepository.save(productActualsViews);
        }
*/
        productActualsViewBuilder.buildProductActualsView();
        sparkContext.getOrCreate();
    }

    @After
    public void shutdown() {
        productActualsViewBuilder.deleteProductActualsView();
        sparkContext.stop();
    }

    @Test
    public void testForeast() {
        List<List<? extends ProductSubscriptionMetricsView>> listOfPseudoActualsAndForecasts = builder.buildForecast("1", SysDate.now(), 1, 730);
        List<? extends ProductSubscriptionMetricsView> forecasts=listOfPseudoActualsAndForecasts.get(1);
        for (int i=0;i<forecasts.size();i++) {
            System.out.println("****New subscriptions: " + forecasts.get(i).getNewSubscriptions());
            System.out.println("churned subscriptions: " + forecasts.get(i).getChurnedSubscriptions());
            System.out.println("total subscriptions: " + forecasts.get(i).getTotalNumberOfExistingSubscriptions());
            System.out.println("start date: " + forecasts.get(i).getProductVersionId().getFromDate());
            System.out.println("end date: " + forecasts.get(i).getEndDate());
            System.out.println("___________________________________________________________________");
        }
    }
}
