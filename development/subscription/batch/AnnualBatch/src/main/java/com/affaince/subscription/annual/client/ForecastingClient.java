package com.affaince.subscription.annual.client;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 05-07-2016.
 */
public class ForecastingClient {
    @Value("${subscription.forecast.findproducts.url}")
    private String findProductsUrl;
    @Value("${subscription.forecast.predictforecast.url}")
    private String forecastUrl;
    @Value("${subscription.forecast.predictpseudoactual.url}")
    private String pseudoActualUrl;


    public void initiateForecast(String productId) {
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        Map<String, Object> params = new HashMap<>();
        params.put("productid", productId);
        System.out.println("$$$$$$$$$$$$$$forecastUrl: " + forecastUrl);
        restTemplate.put(forecastUrl, null, params);
    }

    public void initiatePseudoActual(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
        restTemplate.put(pseudoActualUrl, null, params);
    }
}
