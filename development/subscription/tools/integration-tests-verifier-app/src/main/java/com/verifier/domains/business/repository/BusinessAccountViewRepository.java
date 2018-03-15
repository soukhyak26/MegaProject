package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BusinessAccountView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BusinessAccountViewRepository extends CrudRepository<BusinessAccountView, String> {
    List<BusinessAccountView> findByEndDateAfter(LocalDate date);
}
