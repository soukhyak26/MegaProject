package com.affaince.subscription.delivery.client;

import com.affaince.subscription.common.vo.DeliveryId;
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
 * Created by rbsavaliya on 25-11-2016.
 */
@Component
public class DeliveryClient {

    @Value("${subscription.delivery.preparefordispatch.url}")
    private String deliveryPrepareForDispatchUrl;

    public void prepareDeliveryForDispatch(DeliveryId deliveryId) {
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("subscriberId", deliveryId.getSubscriberId());
        uriParams.put("subscriptionId", deliveryId.getSubscriptionId());
        uriParams.put("deliveryId", deliveryId.getDeliveryId());
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(deliveryPrepareForDispatchUrl);

        restTemplate.put(deliveryPrepareForDispatchUrl, null, uriParams);

    }
}
