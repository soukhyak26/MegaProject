package com.affaince.subscription.expensedistribution.query.repository;

import com.affaince.subscription.expensedistribution.query.view.ForecastPriceBucketsView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rsavaliya on 2/4/16.
 */
public interface ForecastPriceBucketsViewRepository extends CrudRepository<ForecastPriceBucketsView, String> {
}