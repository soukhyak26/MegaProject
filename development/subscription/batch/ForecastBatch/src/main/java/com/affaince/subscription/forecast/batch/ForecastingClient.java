package com.affaince.subscription.forecast.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 05-07-2016.
 */
public class ForecastingClient {
    @Value("${subscription.batch.findproducts.url}")
    private String findProductsUrl;
    @Value("${subscription.batch.predictforecast.url}")
    private String forecastUrl;
    @Value("${subscription.batch.predictpseudoactual.url}")
    private String pseudoActualUrl;


    public void initiateForecast(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
        restTemplate.put(forecastUrl, null, params);
    }

    public void initiatePseudoActual(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
        restTemplate.put(pseudoActualUrl, null, params);
    }
}
