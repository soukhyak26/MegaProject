package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.pricing.query.view.ProductView;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public interface ProductViewRepository extends PagingAndSortingRepository<ProductView, String> {
}
