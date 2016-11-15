package com.affaince.subscription.forecast.product;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.forecast.query.repository.ProductViewRepository;
import com.affaince.subscription.forecast.query.view.ProductView;
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
 * Created by mandar on 18-09-2016.
 */
@Component
public class ProductViewBuilder {
    @Autowired
    private ProductViewRepository productViewRepository;

    public void buildProductView() throws Exception {

        final List<ProductView> productViews = new ArrayList<>();

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
                    productViews.add(new ProductView(
                            tokens[0], tokens[1], tokens[2], tokens[3],
                            Long.parseLong(tokens[4]), QuantityUnit.GM,
                            substitutes, compliments, null
                    ));
                }
        );
        productViewRepository.save(productViews);

    }

    public void clearAll() {
        productViewRepository.deleteAll();
    }
}
