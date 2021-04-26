package com.verifier.domains.inventory.repository;

import com.verifier.domains.inventory.view.ProductInventoryView;
import com.verifier.domains.inventory.vo.InventoryVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductInventoryViewRepository extends CrudRepository<ProductInventoryView, InventoryVersionId> {
    public List<ProductInventoryView> findByInventoryVersionId_ProductInventoryIdAndDemandEndDateAfterAndIsActiveDemand(String productInventoryId,LocalDate sysDate,boolean isActiveDemand);
    public List<ProductInventoryView> findByInventoryVersionId_ProductInventoryIdAndIsActiveDemand(String productInventoryId,boolean isActiveDemand);
    public List<ProductInventoryView> findByInventoryVersionId_ProductIdAndIsActiveDemand(String productId,boolean isActiveDemand);
}
