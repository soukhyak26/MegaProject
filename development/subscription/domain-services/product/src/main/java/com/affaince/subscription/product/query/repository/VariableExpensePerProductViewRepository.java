package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.product.query.view.FixedExpensePerProductView;
import com.affaince.subscription.product.query.view.VariableExpensePerProductView;
import com.affaince.subscription.product.vo.ProductwiseVariableExpenseId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 4/26/2017.
 */
public interface VariableExpensePerProductViewRepository extends CrudRepository<VariableExpensePerProductView, ProductwiseVariableExpenseId> {
    public List<VariableExpensePerProductView> findFirstByProductwiseVariableExpenseId_ProductIdOrderByEndDateDesc(String productId);
    public List<VariableExpensePerProductView> findByProductwiseVariableExpenseId_ProductIdAndProductwiseVariableExpenseId_FromDateBetween(String productId, LocalDate startDate, LocalDate endDate);

}
