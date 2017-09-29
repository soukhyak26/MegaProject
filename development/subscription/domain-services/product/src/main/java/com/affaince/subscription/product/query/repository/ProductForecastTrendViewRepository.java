package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.product.query.view.ProductForecastTrendView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 9/29/2017.
 */
public interface ProductForecastTrendViewRepository extends CrudRepository<ProductForecastTrendView,ForecastVersionId> {

}
