package com.verifier.domains.inventory.repository;

import com.verifier.domains.inventory.view.SupplierView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierViewRepository extends CrudRepository<SupplierView, String> {
    List<SupplierView> findByProductId(String productId);
}
