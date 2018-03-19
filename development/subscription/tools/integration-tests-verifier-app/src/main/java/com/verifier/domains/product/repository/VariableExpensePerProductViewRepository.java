package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.VariableExpensePerProductView;
import com.verifier.domains.product.vo.ProductwiseVariableExpenseId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 4/26/2017.
 */
public interface VariableExpensePerProductViewRepository extends CrudRepository<VariableExpensePerProductView, ProductwiseVariableExpenseId> {

    public List<VariableExpensePerProductView> findFirstByProductwiseVariableExpenseId_ProductIdOrderByEndDateDesc(String productId);
    public List<VariableExpensePerProductView> findByProductwiseVariableExpenseId_ProductIdAndProductwiseVariableExpenseId_StartDateBetween(String productId, LocalDate startDate, LocalDate endDate);

}
