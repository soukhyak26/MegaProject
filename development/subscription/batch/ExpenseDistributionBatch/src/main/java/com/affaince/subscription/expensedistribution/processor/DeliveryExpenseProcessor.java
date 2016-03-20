package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.query.DeliveryView;
import org.apache.camel.Exchange;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class DeliveryExpenseProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        final DeliveryView deliveryView = exchange.getIn().getBody(DeliveryView.class);
        exchange.getIn().setBody(deliveryView);
    }
}
