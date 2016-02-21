package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.pricing.query.view.ProductConfigurationView;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface ProductConfigurationViewRepository extends PagingAndSortingRepository<ProductConfigurationView, String> {
}
