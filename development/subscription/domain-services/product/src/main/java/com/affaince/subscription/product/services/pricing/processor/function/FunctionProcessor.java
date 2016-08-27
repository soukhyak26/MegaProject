package com.affaince.subscription.product.services.pricing.processor.function;


import com.affaince.subscription.product.vo.FunctionCoefficients;

/**
 * Created by mandark on 21-02-2016.
 */
@Deprecated
public interface FunctionProcessor<ProductView> {
    public FunctionCoefficients processFunction(ProductView productView);
}
