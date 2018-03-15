package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.SubscriptionActualsView;
import com.verifier.domains.subscriber.vo.SubscriptionActualsVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 8/30/2017.
 */
public interface SubscriptionActualsViewRepoitory extends CrudRepository<SubscriptionActualsView, SubscriptionActualsVersionId> {
    public List<SubscriptionActualsView> findAllByOrderBySubscriptionActualsVersionId_StartDateDesc();
}
