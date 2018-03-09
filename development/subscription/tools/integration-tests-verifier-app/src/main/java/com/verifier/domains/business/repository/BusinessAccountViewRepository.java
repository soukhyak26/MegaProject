package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BusinessAccountView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by anayonkar on 29/4/16.
 */
public interface BusinessAccountViewRepository extends CrudRepository<BusinessAccountView, String> {
    List<BusinessAccountView> findByEndDateGreaterThanEqual(LocalDate date);
}
