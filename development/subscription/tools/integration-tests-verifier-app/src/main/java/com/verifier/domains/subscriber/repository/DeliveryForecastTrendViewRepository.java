package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
import com.verifier.domains.subscriber.view.DeliveryForecastTrendView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/30/2017.
 */
public interface DeliveryForecastTrendViewRepository extends CrudRepository<DeliveryForecastTrendView,DeliveryForecastVersionId> {
    public List<DeliveryForecastTrendView> findAll();
}
