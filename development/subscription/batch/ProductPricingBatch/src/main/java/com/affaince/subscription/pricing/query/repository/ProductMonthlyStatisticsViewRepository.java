package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.common.vo.ProductMonthlyVersionId;
import com.affaince.subscription.pricing.query.view.ProductMonthlyStatisticsView;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by mandark on 21-02-2016.
 */
public interface ProductMonthlyStatisticsViewRepository extends PagingAndSortingRepository<ProductMonthlyStatisticsView, ProductMonthlyVersionId> {
    public List<ProductMonthlyStatisticsView> findByProductMonthlyVersionId_ProductId(String productId);
}
