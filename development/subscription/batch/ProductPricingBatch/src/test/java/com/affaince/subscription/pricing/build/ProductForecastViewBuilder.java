package com.affaince.subscription.pricing.build;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.pricing.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by mandar on 18-09-2016.
 */
@Component
public class ProductForecastViewBuilder {
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;
    private LocalDate localDate;

    public void buildProductForecast() throws Exception {
        localDate = new LocalDate(2016, 1, 1);
        ProductForecastView productForecastView1 = new ProductForecastView(
                new ProductVersionId("1", localDate),
                localDate.plusDays(30),
                100, 20, 110, ForecastContentStatus.ACTIVE
        );
        productForecastViewRepository.save(productForecastView1);

        ProductForecastView productForecastView2 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(31)),
                localDate.plusDays(51),
                100, 20, 110, ForecastContentStatus.ACTIVE
        );

        productForecastViewRepository.save(productForecastView2);

        ProductForecastView productForecastView3 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(52)),
                localDate.plusDays(74),
                100, 20, 110, ForecastContentStatus.ACTIVE
        );

        productForecastViewRepository.save(productForecastView3);

        for (int k = 0; k <= 1000; k++) {
            List<ProductForecastView> productForecastViewList = new ArrayList<>();

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

            for (int i = 0; i < readings.length; i++) {
                LocalDate newDate = localDate.plusDays(i + 1);
                ProductForecastView productForecastView = new ProductForecastView(new ProductVersionId("product" + k, newDate),
                        new LocalDate(9999, 12, 31), readings[i][0], readings[i][1], 1000, ForecastContentStatus.ACTIVE);
                productForecastViewList.add(productForecastView);
                //productForecastMetricsViewRepository.save(actualMetrics);
            }
            productForecastViewRepository.save(productForecastViewList);
        }
    }

    public void clearAll() {
        productForecastViewRepository.deleteAll();
    }
}
