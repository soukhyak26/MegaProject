package com.affaince.subscription.product.services.pricing.aggregate;

import com.affaince.subscription.product.vo.FunctionCoefficients;
import com.affaince.subscription.product.vo.PriceDeterminationCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 28-02-2016.
 */
public class CoefficientAggregationStrategy {

    public PriceDeterminationCriteria aggregate(FunctionCoefficients coefficients1, FunctionCoefficients coefficients2) {
        List<FunctionCoefficients> coefficientsList= new ArrayList<FunctionCoefficients>();
        coefficientsList.add(coefficients1);
        coefficientsList.add(coefficients2);
        PriceDeterminationCriteria criteria= new PriceDeterminationCriteria(coefficientsList);
        return criteria;

    }
}
