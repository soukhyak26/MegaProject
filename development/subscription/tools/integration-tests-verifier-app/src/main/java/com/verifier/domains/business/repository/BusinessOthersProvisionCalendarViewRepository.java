package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.OthersProvisionCalendarView;
import com.verifier.domains.business.vo.ProvisionCalendarVersionId;
import org.springframework.data.repository.CrudRepository;

public interface BusinessOthersProvisionCalendarViewRepository extends CrudRepository<OthersProvisionCalendarView,ProvisionCalendarVersionId> {
}
