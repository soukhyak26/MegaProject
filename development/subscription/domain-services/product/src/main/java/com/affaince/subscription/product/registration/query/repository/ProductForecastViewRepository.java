package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.ProductConfigurationView;
import com.affaince.subscription.product.registration.query.view.ProductForecastView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandark on 30-04-2016.
 */
public interface ProductForecastViewRepository  extends CrudRepository<ProductForecastView, String> {
}
