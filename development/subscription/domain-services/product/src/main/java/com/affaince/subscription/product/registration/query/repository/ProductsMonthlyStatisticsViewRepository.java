package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.ProductMonthlyStatisticsView;
import com.affaince.subscription.product.registration.vo.ProductMonthlyVersionId;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductsMonthlyStatisticsViewRepository extends PagingAndSortingRepository<ProductMonthlyStatisticsView, ProductMonthlyVersionId> {
    public List<ProductMonthlyStatisticsView> findByProductMonthlyVersionId_ProductId(String productId);
    public List<ProductMonthlyStatisticsView> findByProductMonthlyVersionId_ProductId(String productId,Sort sort);
    public List<ProductMonthlyStatisticsView> findByProductMonthlyVersionId(ProductMonthlyVersionId productMonthlyVersionId, Sort sort);
}
