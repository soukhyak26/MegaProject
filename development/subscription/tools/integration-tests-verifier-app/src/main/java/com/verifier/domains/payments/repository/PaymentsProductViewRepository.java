package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.ProductView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 5/28/2017.
 */
public interface PaymentsProductViewRepository extends CrudRepository<ProductView,String>{
}
