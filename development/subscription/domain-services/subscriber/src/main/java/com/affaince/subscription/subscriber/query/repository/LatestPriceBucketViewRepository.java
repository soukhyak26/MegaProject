package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.LatestPriceBucketView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 04-09-2016.
 */
public interface LatestPriceBucketViewRepository extends CrudRepository<LatestPriceBucketView, String> {
}
