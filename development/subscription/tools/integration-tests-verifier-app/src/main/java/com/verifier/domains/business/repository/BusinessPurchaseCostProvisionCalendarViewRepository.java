package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.PurchaseCostProvisionCalendarView;
import com.verifier.domains.business.vo.ProvisionCalendarVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessPurchaseCostProvisionCalendarViewRepository extends CrudRepository<PurchaseCostProvisionCalendarView,ProvisionCalendarVersionId> {
    public List<PurchaseCostProvisionCalendarView> findAll();
}
