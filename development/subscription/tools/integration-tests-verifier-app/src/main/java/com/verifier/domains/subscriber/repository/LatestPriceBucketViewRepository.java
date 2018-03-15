package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.LatestPriceBucketView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 04-09-2016.
 */
public interface LatestPriceBucketViewRepository extends CrudRepository<LatestPriceBucketView, String> {
}
