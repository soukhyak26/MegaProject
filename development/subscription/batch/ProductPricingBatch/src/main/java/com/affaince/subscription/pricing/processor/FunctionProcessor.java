package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.pricing.vo.FunctionCoefficients;

import java.util.List;

/**
 * Created by mandark on 21-02-2016.
 */
public interface FunctionProcessor<K, T> {
    public FunctionCoefficients processFunction(K productId, List<T> productStatistics);
}
