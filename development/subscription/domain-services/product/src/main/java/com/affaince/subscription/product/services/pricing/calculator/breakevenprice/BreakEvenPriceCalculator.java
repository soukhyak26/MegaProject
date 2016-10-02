package com.affaince.subscription.product.services.pricing.calculator.breakevenprice;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by mandar on 26-06-2016.
 */

public class BreakEvenPriceCalculator {
    @Autowired
    public BreakEvenPriceCalculator(){}
    public double calculateBreakEvenPrice(Map<String,Double> breakEvenPriceContributors) {
        double breakEvenPrice=0.0;
        Iterator<Map.Entry<String,Double>> bePriceParamIterator= breakEvenPriceContributors.entrySet().iterator();
        while(bePriceParamIterator.hasNext()){
            breakEvenPrice+=bePriceParamIterator.next().getValue();
        }
        //final double breakEvenPrice = purchasePrice + fixedOperatingExpensePerUnit + variableExpensePerUnit;
        return breakEvenPrice;
    }

}