package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.product.query.view.ForecastedPriceBucketsView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandark on 30-04-2016.
 */
public interface ForecastedPriceBucketViewRepository extends CrudRepository<ForecastedPriceBucketsView, String> {
}
