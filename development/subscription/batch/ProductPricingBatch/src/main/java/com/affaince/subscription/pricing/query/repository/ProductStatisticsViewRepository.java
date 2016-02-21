package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.pricing.query.view.ProductMonthlyVersionId;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by mandark on 21-02-2016.
 */
public interface ProductStatisticsViewRepository extends PagingAndSortingRepository<ProductStatisticsView, ProductMonthlyVersionId> {

}
