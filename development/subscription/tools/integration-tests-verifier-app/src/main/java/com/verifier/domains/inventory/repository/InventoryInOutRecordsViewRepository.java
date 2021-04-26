package com.verifier.domains.inventory.repository;

import com.verifier.domains.inventory.view.InventoryInOutRecordsView;
import org.springframework.data.repository.CrudRepository;

public interface InventoryInOutRecordsViewRepository extends CrudRepository<InventoryInOutRecordsView,String > {
}
