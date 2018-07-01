package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.ProductInventoryView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductInventoryViewRepository extends CrudRepository<ProductInventoryView,String> {
    List<ProductInventoryView> findByProductId(String productId);
}
