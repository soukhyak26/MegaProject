package com.affaince.subscription.delivery.vo;

import org.springframework.data.annotation.Id;

/**
 * Created by rbsavaliya on 29-11-2016.
 */
public class DeliveryVo {
    @Id
    private String deliveryId;
    private String subscriberId;
    private String subscriptionId;


}
