package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
@Ignore
public class ProductForecastViewRepositoryTest {
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;
    private LocalDate localDate;

    @Before
    public void init() throws FileNotFoundException {
        // productForecastViewRepository.deleteAll();

        localDate = new LocalDate(2016, 1, 1);
        ProductForecastView productForecastView1 = new ProductForecastView(
                new ProductVersionId("1", localDate),
                localDate.plusDays(30),
                100, 20, 110, SysDate.now()
        );
        productForecastViewRepository.save(productForecastView1);

        ProductForecastView productForecastView2 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(31)),
                localDate.plusDays(51),
                100, 20, 110, SysDate.now()
        );

        productForecastViewRepository.save(productForecastView2);

        ProductForecastView productForecastView3 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(52)),
                localDate.plusDays(74),
                100, 20, 110, SysDate.now()
        );

        productForecastViewRepository.save(productForecastView3);

        List<ProductForecastView> productForecastViewList = new ArrayList<>();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

        for (int i = 0; i < readings.length; i++) {
            LocalDate newDate = localDate.plusDays(i + 1);
            ProductForecastView productForecastView = new ProductForecastView(new ProductVersionId("product" + i, newDate),
                    new LocalDate(9999, 12, 31), readings[i][0], readings[i][1], 1000, SysDate.now());
            productForecastViewList.add(productForecastView);
        }
        productForecastViewRepository.save(productForecastViewList);
    }

    @Test
    public void testFindFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc() {
        ProductForecastView productForecastView =
                productForecastViewRepository.
                        findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc("1");
        ProductVersionId productVersionId = new ProductVersionId("1", localDate.plusDays(52));
        assertThat(productForecastView.getProductVersionId(), is(productVersionId));
    }

    @Test
    public void testFindByProductVersionId_ProductIdAndForecastContentStatusOrderByProductVersionId_FromDateDesc() {
        List<ProductForecastView> productForecastViewList = productForecastViewRepository.
                findByProductVersionId_ProductIdAndForecastContentStatusOrderByProductVersionId_FromDateDesc("1", ForecastContentStatus.ACTIVE);
        assertThat(productForecastViewList.size(), is(3));
        ProductVersionId productVersionId = new ProductVersionId("1", localDate.plusDays(52));
        assertThat(productForecastViewList.get(0).getProductVersionId(), is(productVersionId));
    }

    @After
    public void shutdown() {
        productForecastViewRepository.deleteAll();
    }
}
