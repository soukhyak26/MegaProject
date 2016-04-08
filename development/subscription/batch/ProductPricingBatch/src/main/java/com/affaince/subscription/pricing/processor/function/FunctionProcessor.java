package com.affaince.subscription.pricing.processor.function;

import com.affaince.subscription.pricing.vo.FunctionCoefficients;

import java.util.List;

/**
 * Created by mandark on 21-02-2016.
 */
public interface FunctionProcessor<ProductView> {
    public FunctionCoefficients processFunction(ProductView productView);
}
