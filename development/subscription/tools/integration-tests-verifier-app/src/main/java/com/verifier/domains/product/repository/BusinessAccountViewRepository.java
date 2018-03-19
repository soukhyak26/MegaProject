package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.BusinessAccountView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessAccountViewRepository extends CrudRepository<BusinessAccountView, Integer> {
    List<BusinessAccountView> findByEndDateAfter(LocalDate date);
}
