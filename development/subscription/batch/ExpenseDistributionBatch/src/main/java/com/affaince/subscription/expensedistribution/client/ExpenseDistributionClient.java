package com.affaince.subscription.expensedistribution.client;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.expensedistribution.query.view.DeliveryChargesRuleView;
import com.affaince.subscription.expensedistribution.query.view.DeliveryView;
import com.affaince.subscription.expensedistribution.query.view.ProductForecastMetricsView;
import com.affaince.subscription.expensedistribution.query.view.ProductView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

/**
 * Created by rbsavaliya on 08-07-2016.
 */
public class ExpenseDistributionClient {
    @Value("${subscription.expensedistribution.fetchdeliveries.url}")
    private String fetchDeliveriesUrl;

    @Value("${subscription.expensedistribution.fetchallproducts.url}")
    private String fetchAllProductsUrl;

    @Value ("${subscription.expensedistribution.fetchallforecastedpricebuckets.url}")
    private String fetchAllForecastedPriceBucketsUrl;

    @Value("${subscription.expensedistribution.fetchalldeliverychargesrules}")
    private String fetchAllDeliveryChargesRulesUrl;

    @Value("${subscription.expensedistribution.fetchforecastedpricebucketsbyproductid.url}")
    private String fetchForecastedPriceBucketsbyProductId;
    @Autowired
    private ObjectMapper objectMapper;

    public List<DeliveryView> fetchAllDeliveries () throws IOException {
        final RestTemplate restTemplate = new RestTemplate();
        String  result = restTemplate.getForObject(fetchDeliveriesUrl, String.class);
        System.out.println(result);
        final ObjectMapper objectMapper = new ObjectMapper();
        final DeliveryView [] deliveryViews = objectMapper.readValue(result, DeliveryView[].class);
        return Arrays.asList(deliveryViews);
    }

    public List <ProductView> fetchAllProducts () {
        final RestTemplate restTemplate = new RestTemplate();
        ProductView [] result = restTemplate.getForObject(fetchAllProductsUrl, ProductView[].class);
        return Arrays.asList(result);
    }

    public List <ProductForecastMetricsView> fetchAllProductForecastMetrics () throws IOException {
        final RestTemplate restTemplate = new RestTemplate();
        String  result = restTemplate.getForObject(fetchAllForecastedPriceBucketsUrl, String.class);
        //final ObjectMapper objectMapper = new ObjectMapper();
        //System.out.println(result);
        final ProductForecastMetricsView [] productForecastMetricsView = objectMapper.readValue(result, ProductForecastMetricsView[].class);
        return Arrays.asList(productForecastMetricsView);
    }

    public List <DeliveryChargesRuleView> fetchAllDeliveryChargesRules () {
        final RestTemplate restTemplate = new RestTemplate();
        DeliveryChargesRuleView result = restTemplate.getForObject(fetchAllDeliveryChargesRulesUrl+"/"+ DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT.getDeliveryChargesRuleTypeCode(),
                DeliveryChargesRuleView.class);
        return Arrays.asList(result);
    }

    public List <ProductForecastMetricsView> fetchProductForecastMetricsByProductId (final String productId) {
        final RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
        ArrayList<ProductForecastMetricsView> result =
                restTemplate.getForObject(fetchAllForecastedPriceBucketsUrl, ArrayList.class, params);
        return result;
    }
}
