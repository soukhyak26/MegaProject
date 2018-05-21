package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BenefitsProvisionCalendarView;
import com.verifier.domains.business.vo.ProvisionCalendarVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessBenefitsProvisionCalendarViewRepository extends CrudRepository<BenefitsProvisionCalendarView,ProvisionCalendarVersionId> {
    public List<BenefitsProvisionCalendarView> findAll();
}
