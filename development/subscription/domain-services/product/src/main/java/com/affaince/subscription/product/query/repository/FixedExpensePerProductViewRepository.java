package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.product.query.view.FixedExpensePerProductView;
import com.affaince.subscription.product.vo.ProductwiseFixedExpenseId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 4/26/2017.
 */
public interface FixedExpensePerProductViewRepository extends CrudRepository<FixedExpensePerProductView, ProductwiseFixedExpenseId>{
        public List<FixedExpensePerProductView> findFirstByProductwiseFixedExpenseId_ProductIdOrderByEndDateDesc(String productId);
        public List<FixedExpensePerProductView> findByProductwiseFixedExpenseId_ProductIdAndProductwiseFixedExpenseId_FromDateBetween(String productId, LocalDate startDate, LocalDate endDate);
}
