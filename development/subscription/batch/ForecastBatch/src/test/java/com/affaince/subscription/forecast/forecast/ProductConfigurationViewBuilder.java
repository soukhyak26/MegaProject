package com.affaince.subscription.forecast.forecast;

import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.pricing.query.view.ProductConfigurationView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mandar on 25-09-2016.
 */
@Component
public class ProductConfigurationViewBuilder {

    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;

    public void buildProductConfigurationView() throws Exception {
        final List<ProductConfigurationView> productConfigurationViews = new ArrayList<>();
        final BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/product.csv"))));

        fileReader.lines().forEach(
                line -> {
                    String[] tokens = line.split(",");
                    List<String> substitutes = Arrays.asList(line.substring(
                            line.indexOf("[") + 1, line.indexOf("]")
                    ).split(","));
                    List<String> compliments = Arrays.asList(line.substring(
                            line.lastIndexOf("[") + 1, line.lastIndexOf("]")
                    ).split(","));
                    productConfigurationViews.add(new ProductConfigurationView(
                            tokens[0], 30, 0.05, false, false
                    ));
                }
        );
        for (ProductConfigurationView configurationView : productConfigurationViews) {
            configurationView.setNextForecastDate(LocalDate.now());
        }
        productConfigurationViewRepository.save(productConfigurationViews);
    }

    public void clearAll() {
        productConfigurationViewRepository.deleteAll();
    }
}
