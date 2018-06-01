package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.CommonExpenseProvisionCalendarView;
import com.verifier.domains.business.vo.ProvisionCalendarVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessCommonExpensesProvisionCalendarViewRepository extends CrudRepository<CommonExpenseProvisionCalendarView,ProvisionCalendarVersionId> {
    public List<CommonExpenseProvisionCalendarView> findAllByOrderByProvisionCalendarVersionId_StartDateAsc();
}
