package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import com.affaince.subscription.pricing.vo.Product;

import java.util.List;

/**
 * Created by mandark on 21-02-2016.
 */
public interface FunctionProcessor<T> {
    public FunctionCoefficients processFunction(List<T> productStatistics);
}
