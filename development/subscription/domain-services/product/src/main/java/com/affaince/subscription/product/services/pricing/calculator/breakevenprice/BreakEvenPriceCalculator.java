package com.affaince.subscription.product.services.pricing.calculator.breakevenprice;

import com.affaince.subscription.product.vo.CostHeader;
import com.affaince.subscription.product.vo.CostHeaderApplicability;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 26-06-2016.
 */

public class BreakEvenPriceCalculator {
    @Autowired
    public BreakEvenPriceCalculator() {
    }

    public double calculateBreakEvenPrice(List<CostHeader> costHeaders) {
        double breakEvenPrice = 0.0;
        for (CostHeader costHeader : costHeaders) {
            if (costHeader.getCostHeaderApplicability() == CostHeaderApplicability.ABSOLUTE) {
                breakEvenPrice += costHeader.getValue();
            }
        }
        List<CostHeader> costHeadersWithPercentApplicability = costHeaders.stream().filter(costHeader -> costHeader.getCostHeaderApplicability() == CostHeaderApplicability.PERCENT_OF_TOTAL_COST).collect(Collectors.toList());
        double percentValue = 0.0;
        for (CostHeader costHeaderWithPercentApplicability : costHeadersWithPercentApplicability) {
            percentValue += costHeaderWithPercentApplicability.getValue();
        }
        breakEvenPrice = breakEvenPrice + (breakEvenPrice * percentValue);
        return breakEvenPrice;
    }

}
