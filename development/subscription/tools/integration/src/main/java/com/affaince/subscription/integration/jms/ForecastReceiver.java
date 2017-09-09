package com.affaince.subscription.integration.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 9/9/2017.
 */
@Component
public class ForecastReceiver {

    @JmsListener(destination = "ForecastOutputQueue", containerFactory = "myFactory")
    public void receiveMessage(String forecastString) {
        System.out.println("Received <" + forecastString + ">");
    }
}
