package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.ProductsStatisticsView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 15-01-2016.
 */

public interface BusinessProductsStatisticsViewRepository extends CrudRepository<ProductsStatisticsView, String> {
}
