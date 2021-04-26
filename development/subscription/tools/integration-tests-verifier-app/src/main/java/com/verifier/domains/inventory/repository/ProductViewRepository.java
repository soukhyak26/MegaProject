package com.verifier.domains.inventory.repository;

import com.verifier.domains.inventory.view.ProductView;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductViewRepository extends CrudRepository<ProductView, String> {
    @Query("{ 'productName' : { '$regex' : ?0 , $options: 'i'}}")
    List<ProductView> findByProductNameRegex(String productName);
}
