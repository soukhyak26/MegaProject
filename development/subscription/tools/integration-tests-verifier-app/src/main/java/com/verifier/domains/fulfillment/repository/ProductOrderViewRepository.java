package com.verifier.domains.fulfillment.repository;

import com.verifier.domains.fulfillment.view.ProductOrderView;
import com.verifier.domains.fulfillment.vo.ProductOrderId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductOrderViewRepository extends CrudRepository<ProductOrderView,ProductOrderId> {
    List<ProductOrderView> findByProductOrderId_OrderDate(LocalDate orderDate);
}
