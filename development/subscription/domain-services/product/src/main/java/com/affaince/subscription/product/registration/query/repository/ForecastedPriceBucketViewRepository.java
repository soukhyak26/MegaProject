package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.ForecastedPriceBucketsView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandark on 30-04-2016.
 */
public interface ForecastedPriceBucketViewRepository extends CrudRepository<ForecastedPriceBucketsView, String> {
}