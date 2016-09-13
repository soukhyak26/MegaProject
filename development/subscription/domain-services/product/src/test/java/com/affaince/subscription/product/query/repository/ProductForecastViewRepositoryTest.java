package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 16-08-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ProductForecastViewRepositoryTest {
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;

    @Before
    public void init() throws FileNotFoundException {
        productForecastViewRepository.deleteAll();

        LocalDate localDate = SysDate.now();
        ProductForecastView productForecastView1 = new ProductForecastView(
                new ProductVersionId("1", localDate),
                localDate.plusDays(30),
                100, 20, 110, ProductForecastStatus.ORIGINAL
        );
        productForecastViewRepository.save(productForecastView1);

        ProductForecastView productForecastView2 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(31)),
                localDate.plusDays(51),
                100, 20, 110, ProductForecastStatus.CORRECTED
        );

        productForecastViewRepository.save(productForecastView2);

        ProductForecastView productForecastView3 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(52)),
                localDate.plusDays(74),
                100, 20, 110, ProductForecastStatus.CORRECTED
        );

        productForecastViewRepository.save(productForecastView3);

        for (int k = 0; k <= 1000; k++) {
            List<ProductForecastView> productActualMetricsViewList = new ArrayList<>();

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

            ProductForecastMetricsView forecastView = new ProductForecastMetricsView(new ProductVersionId("product" + k, new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
            forecastView.setTotalNumberOfExistingSubscriptions(1250);
            localDate = new LocalDate(2016, 1, 1);
            for (int i = 0; i < readings.length; i++) {
                localDate = localDate.plusDays(1);
                ProductForecastView productForecastView = new ProductForecastView(new ProductVersionId("product" + k, localDate),
                        new LocalDate(9999, 12, 31), readings[i][0], readings[i][1], 1000, ProductForecastStatus.ORIGINAL);
                productActualMetricsViewList.add(productForecastView);
                //productForecastMetricsViewRepository.save(actualMetrics);
            }
            productForecastViewRepository.save(productActualMetricsViewList);
        }
    }

    @Test
    public void testFindFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc () {
        ProductForecastView productForecastView =
                productForecastViewRepository.
                        findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc("1");
        ProductVersionId productVersionId = new ProductVersionId("1", SysDate.now().plusDays(52));
        assertThat (productForecastView.getProductVersionId(), is (productVersionId));
    }

    @Test
    public void testFindByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc () {
        List<ProductForecastView> productForecastViewList = productForecastViewRepository.
                findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc("1", ProductForecastStatus.CORRECTED);
        assertThat(productForecastViewList.size(), is (2));
        ProductVersionId productVersionId = new ProductVersionId("1", SysDate.now().plusDays(52));
        assertThat (productForecastViewList.get(0).getProductVersionId(), is (productVersionId));
    }

    /*@After
    public void shutdown () {
        ProductVersionId productVersionId = new ProductVersionId("1", SysDate.now().plusDays(52));
        productForecastViewRepository.delete(productVersionId);
    }*/
}
