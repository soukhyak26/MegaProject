package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.view.ProductForecastView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductForecastViewRepository extends CrudRepository<ProductForecastView, ProductVersionId> {
    public List<ProductForecastView> findByProductVersionId_ProductIdAndForecastContentStatusOrderByProductVersionId_FromDateDesc(String productId, ForecastContentStatus forecastContentStatus);
    List<ProductForecastView> findByProductVersionId_ProductIdAndForecastContentStatusOrderByProductVersionId_FromDateAsc(String productId, ForecastContentStatus forecastContentStatus);
}
