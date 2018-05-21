package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.TaxesProvisionCalendarView;
import com.verifier.domains.business.vo.ProvisionCalendarVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessTaxesProvisionCalendarViewRepository extends CrudRepository<TaxesProvisionCalendarView,ProvisionCalendarVersionId> {
    public List<TaxesProvisionCalendarView> findAll();
}
