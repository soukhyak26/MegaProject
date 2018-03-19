package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.BenefitView;
import org.joda.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
public interface BenefitViewRepository extends CrudRepository<BenefitView, String> {
    List<BenefitView> findAll();
    List<BenefitView> findByActivationStartTimeGreaterThanEqual(LocalDateTime localDateTime);
}
