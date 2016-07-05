package com.affaince.subscription.pricing.forecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by mandar on 05-07-2016.
 */
public class ForecastingClient {
    @Autowired
    RestTemplate restTemplate;
    public void initiateForecast(){
        ResponseEntity<List<String>> productListResponse = restTemplate.exchange("${subscription.forecast.findproducts.url}", HttpMethod.GET,null, new ParameterizedTypeReference<List<String>>() {
        });
        List<String> listOfProducts=productListResponse.getBody();
        for(String productId: listOfProducts){
            restTemplate.put("${subscription.forecast.forecast.url}",new HttpEntity<String>(productId));
        }

    }
}
