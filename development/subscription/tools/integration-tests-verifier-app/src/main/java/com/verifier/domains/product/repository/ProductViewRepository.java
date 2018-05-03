package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.ProductView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public interface ProductViewRepository extends CrudRepository<ProductView, String> {
    public List<ProductView> findAll();
    public List<ProductView> findByProductName(String productName);
}
