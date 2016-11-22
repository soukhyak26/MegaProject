package com.affaince.subscription.forecast.product;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 20-11-2016.
 */
@Component
public class ProductForecastClient {
    @Value("${subscription.forecast.forecastproducts.url}")
    private String forecastProductsUrl;

    @Value("${subscription.stepforecast.forecastproducts.url}")
    private String stepForecastProductsUrl;

    public void addForecast(String productId,Map<String,String> productAttributesMap){
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

    public void addStepForecast(String productId,Map<String,String> productAttributesMap){
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
/*
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
*/
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("productId", productId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(stepForecastProductsUrl);

        System.out.println("$$$$$$$$$$$$$$forecastProductsUrl: " + stepForecastProductsUrl);
        restTemplate.put(builder.buildAndExpand(uriParams).toUri().toString(), null, productAttributesMap);
    }


}
