package com.affaince.subscription.product.registration.services.pricing.processor.function;


import com.affaince.subscription.product.registration.vo.FunctionCoefficients;

/**
 * Created by mandark on 21-02-2016.
 */
public interface FunctionProcessor<ProductView> {
    public FunctionCoefficients processFunction(ProductView productView);
}
