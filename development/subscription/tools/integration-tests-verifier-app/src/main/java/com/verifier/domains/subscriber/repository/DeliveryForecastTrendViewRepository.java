package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.DeliveryForecastTrendView;
import com.verifier.domains.subscriber.vo.DeliveryForecastVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/30/2017.
 */
public interface DeliveryForecastTrendViewRepository extends CrudRepository<DeliveryForecastTrendView,DeliveryForecastVersionId> {
    public List<DeliveryForecastTrendView> findAll();
}
