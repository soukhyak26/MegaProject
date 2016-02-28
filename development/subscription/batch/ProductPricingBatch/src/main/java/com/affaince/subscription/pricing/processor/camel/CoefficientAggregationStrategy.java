package com.affaince.subscription.pricing.processor.camel;

import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.processor.aggregate.CompletionAwareAggregationStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 28-02-2016.
 */
public class CoefficientAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Object newBody = newExchange.getIn().getBody();
        FunctionCoefficients coefficients1 = null;
        FunctionCoefficients coefficients2 = null;
        if (oldExchange == null) {
            return newExchange;
        }
        List<FunctionCoefficients> coefficientsList= new ArrayList<FunctionCoefficients>();
        coefficients1 = oldExchange.getIn().getBody(FunctionCoefficients.class);
        coefficients2 = newExchange.getIn().getBody(FunctionCoefficients.class);
/*
        if(coefficientsList.size()==2){
            return oldExchange;
        }
*/
        coefficientsList.add(coefficients1);
        coefficientsList.add(coefficients2);
        oldExchange.getIn().setBody(coefficientsList);
        return oldExchange;

    }
}
