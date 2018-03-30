package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.SubscriptionForecastTrendView;
import com.verifier.domains.subscriber.vo.SubscriptionVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/30/2017.
 */
public interface SubscriptionForecastTrendViewRepository extends CrudRepository<SubscriptionForecastTrendView, SubscriptionVersionId> {
    public List<SubscriptionForecastTrendView> findAll();
}