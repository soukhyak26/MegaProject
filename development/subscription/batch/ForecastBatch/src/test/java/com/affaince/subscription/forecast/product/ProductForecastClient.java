package com.affaince.subscription.forecast.product;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 20-11-2016.
 */
public class ProductForecastClient {
    @Value("${subscription.forecast.forecastproducts.url}")
    private String forecastProductsUrl;

    public void configureProduct(String productId,Map<String,String> productAttributesMap){
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
/*
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
*/
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("productId", productId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(forecastProductsUrl);

        System.out.println("$$$$$$$$$$$$$$forecastProductsUrl: " + forecastProductsUrl);
        restTemplate.put(builder.buildAndExpand(uriParams).toUri().toString(), null, productAttributesMap);

    }

}
