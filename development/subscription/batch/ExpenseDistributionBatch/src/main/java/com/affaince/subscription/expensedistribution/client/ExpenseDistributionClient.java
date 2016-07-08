package com.affaince.subscription.expensedistribution.client;

import com.affaince.subscription.expensedistribution.query.view.DeliveryView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 08-07-2016.
 */
public class ExpenseDistributionClient {
    @Value("${subscription.expensedistribution.fetchdeliveries.url}")
    private String fetchDeliveriesUrl;

    public List<DeliveryView> fetchAllDeliveries () {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<DeliveryView> result = restTemplate.getForObject(fetchDeliveriesUrl, ArrayList.class);
        return result;
    }
}
