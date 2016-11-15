package com.affaince.subscription.forecast.build;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.forecast.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.forecast.query.view.ProductForecastView;
import org.joda.time.LocalDateTime;
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
    private LocalDateTime localDate;

    public void buildProductForecast() throws Exception {
        localDate = new LocalDateTime(2016, 1, 1, 0, 0, 0);
        ProductForecastView productForecastView1 = new ProductForecastView(
                new ProductVersionId("1", localDate),
                localDate.plusDays(30),
                100, 20, 110, ProductForecastStatus.ACTIVE
        );
        productForecastViewRepository.save(productForecastView1);

        ProductForecastView productForecastView2 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(31)),
                localDate.plusDays(51),
                100, 20, 110, ProductForecastStatus.ACTIVE
        );

        productForecastViewRepository.save(productForecastView2);

        ProductForecastView productForecastView3 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(52)),
                localDate.plusDays(74),
                100, 20, 110, ProductForecastStatus.ACTIVE
        );

        productForecastViewRepository.save(productForecastView3);

        for (int k = 0; k <= 1000; k++) {
            List<ProductForecastView> productForecastViewList = new ArrayList<>();

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

            for (int i = 0; i < readings.length; i++) {
                LocalDateTime newDate = localDate.plusDays(i + 1);
                ProductForecastView productForecastView = new ProductForecastView(new ProductVersionId("product" + k, newDate),
                        new LocalDateTime(9999, 12, 31, 0, 0, 0), readings[i][0], readings[i][1], 1000, ProductForecastStatus.ACTIVE);
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
