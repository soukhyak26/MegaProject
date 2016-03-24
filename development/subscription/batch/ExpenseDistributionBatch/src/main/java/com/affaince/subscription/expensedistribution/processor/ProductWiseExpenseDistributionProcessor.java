package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class ProductWiseExpenseDistributionProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        final List<ProductWiseDeliveryStats> productWiseDeliveryStats = exchange.getIn().getBody(ArrayList.class);
        //final double totalDeliveryExpenses = exchange.getIn().getBody(Double.class);
    }
}
