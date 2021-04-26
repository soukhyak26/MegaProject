package com.verifier.domains.inventory.repository;

import com.verifier.domains.inventory.view.ItemDispatchRecordView;
import com.verifier.domains.inventory.vo.ItemDispatchRecordId;
import org.springframework.data.repository.CrudRepository;

public interface ItemDispatchRecordViewRepository extends CrudRepository<ItemDispatchRecordView, ItemDispatchRecordId> {
}
