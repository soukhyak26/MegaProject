package com.verifier.domains.product.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.product.view.InventoryProvisionCalendarView;
import com.verifier.domains.product.vo.InventoryProvisionCalendarVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryProvisionCalendarViewRepository extends CrudRepository<InventoryProvisionCalendarView,InventoryProvisionCalendarVersionId> {
    public List<InventoryProvisionCalendarView> findByInventoryProvisionCalendarVersionId_ProductIdAndForecastContentStatus(String productId, ForecastContentStatus forecastContentStatus);
}
