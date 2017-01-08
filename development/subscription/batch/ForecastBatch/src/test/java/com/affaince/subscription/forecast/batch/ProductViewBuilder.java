package com.affaince.subscription.forecast.batch;

import com.affaince.subscription.forecast.product.ProductRegistrationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 18-09-2016.
 */
@Component
public class ProductViewBuilder {
    @Autowired
    //private ProductViewRepository productViewRepository;
    private ProductRegistrationClient productRegistrationClient;

    public void buildProductView() throws Exception {

//        final List<Map> productViews = new ArrayList<>();

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

                    Map<String, Object> productView = new HashMap<>();
                    //ProductView productView1 = new ProductView();
                    productView.put("productName", tokens[1]);
                    productView.put("categoryId", tokens[2]);
                    productView.put("subCategoryId", tokens[3]);
                    productView.put("quantityUnit", "gram");
                    productView.put("productPricingCategory", 1);
                    productView.put("quantity",tokens[4] );
                    productView.put("substitutes", new String[]{"2", "3"});
                    productView.put("complements", new String[]{"2", "3"});
                    productView.put("purchasePrice", 200);
                    productView.put("MRP", 300);
                    productRegistrationClient.registerProduct(productView);
                }
        );
        //  productViewRepository.save(productViews);

    }
    public void clearAll() {
/*
        reposit
        productViewRepository.deleteAll();
*/
    }
}
