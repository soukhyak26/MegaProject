package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.SubscriberForecastTrendView;
import com.verifier.domains.subscriber.vo.SubscriberVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/30/2017.
 */
public interface SubscriberForecastTrendViewRepository extends CrudRepository<SubscriberForecastTrendView,SubscriberVersionId> {
    List<SubscriberForecastTrendView> findAll();
}
