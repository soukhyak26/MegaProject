package com.affaince.subscription.forecast.batch;

import com.affaince.subscription.forecast.product.ProductConfigurationClient;
import com.affaince.subscription.repository.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 25-09-2016.
 */
@Component
public class ProductConfigurationViewBuilder {

    @Autowired
    //private ProductConfigurationViewRepository productConfigurationViewRepository;
    private ProductConfigurationClient productConfigurationClient;

    @Autowired
    IdGenerator defaultIdGenerator;

    public void buildProductConfigurationView() throws Exception {
        //final List<ProductConfigurationView> productConfigurationViews = new ArrayList<>();
        final BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/product.csv"))));

        fileReader.lines().forEach(
                line -> {
                    String[] tokens = line.split(",");
/*
                    List<String> substitutes = Arrays.asList(line.substring(
                            line.indexOf("[") + 1, line.indexOf("]")
                    ).split(","));
                    List<String> compliments = Arrays.asList(line.substring(
                            line.lastIndexOf("[") + 1, line.lastIndexOf("]")
                    ).split(","));
*/

  /*                  productConfigurationViews.add(new ProductConfigurationView(
                            tokens[0], 30, 0.05, false, false
                    ));
  */
                    Map<String, Object> productConfigView = new HashMap<>();
                    productConfigView.put("actualsAggregationPeriodForTargetForecast", 30);
                    productConfigView.put("targetChangeThresholdForPriceChange", 0.05);
                    productConfigView.put("isCrossPriceElasticityConsidered", false);
                    productConfigView.put("isAdvertisingExpensesConsidered", false);
                    productConfigView.put("pricingOptions","1");
                    productConfigView.put("pricingStrategyType","0");
                    String IDString = tokens[1] + "$" + tokens[2] + "$" + tokens[3] + "$" + tokens[4];
                    final String productId = defaultIdGenerator.generator(IDString);
                    productConfigurationClient.configureProduct(productId,productConfigView);
                }
        );
/*
        for (ProductConfigurationView configurationView : productConfigurationViews) {
            configurationView.setNextForecastDate(SysDate.now());
        }
*/
//        productConfigurationViewRepository.save(productConfigurationViews);
    }

    public void clearAll() {
        //productConfigurationViewRepository.deleteAll();
    }
}
