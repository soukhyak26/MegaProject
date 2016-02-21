package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.vo.Product;

/**
 * Created by mandark on 21-02-2016.
 */
public interface CostProcessor {
    public void determineCostOfAProduct(ProductStatisticsView productStatisticsView);
}
