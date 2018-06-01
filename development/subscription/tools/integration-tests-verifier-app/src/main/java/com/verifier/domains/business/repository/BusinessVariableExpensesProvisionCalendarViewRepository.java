package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.VariableExpensesProvisionCalendarView;
import com.verifier.domains.business.vo.ProvisionCalendarVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessVariableExpensesProvisionCalendarViewRepository extends CrudRepository<VariableExpensesProvisionCalendarView,ProvisionCalendarVersionId> {
    public List<VariableExpensesProvisionCalendarView> findAllByOrderByProvisionCalendarVersionId_StartDateAsc();
}
