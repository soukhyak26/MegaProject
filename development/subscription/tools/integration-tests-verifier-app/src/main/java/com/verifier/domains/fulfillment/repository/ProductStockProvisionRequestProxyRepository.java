package com.verifier.domains.fulfillment.repository;

import com.verifier.domains.fulfillment.view.ProductStockProvisionRequestProxy;
import com.verifier.domains.fulfillment.vo.ProductInventoryUpdateId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductStockProvisionRequestProxyRepository extends CrudRepository<ProductStockProvisionRequestProxy,ProductInventoryUpdateId> {
    List<ProductStockProvisionRequestProxy> findByProductInventoryUpdateId_InventoryUpdateDateBetween(LocalDate startDate, LocalDate endDate);
}
