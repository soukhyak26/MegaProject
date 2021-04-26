package com.verifier.domains.inventory.repository;

import com.verifier.domains.inventory.view.ProductStockOrderView;
import org.springframework.data.repository.CrudRepository;

public interface ProductStockOrderViewRepository extends CrudRepository<ProductStockOrderView, String> {
}
