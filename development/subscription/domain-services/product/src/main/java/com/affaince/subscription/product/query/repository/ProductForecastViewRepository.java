package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductForecastViewRepository extends CrudRepository<ProductForecastView, ForecastVersionId> {
    List<ProductForecastView> findByForecastVersionId_ProductId(String productId);

    List<ProductForecastView> findByForecastVersionId_ProductId(String productId, Sort sort);

    ProductForecastView findFirstByForecastVersionId_ProductIdOrderByForecastVersionId_FromDateDesc(String productId);

    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastVersionId_FromDateDesc(String productId, ForecastContentStatus forecastContentStatus);

    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastVersionId_FromDateAsc(String productId, ForecastContentStatus forecastContentStatus);

    List<ProductForecastView> findByForecastVersionId_ProductIdAndEndDateLessThan(String productId, LocalDate endDate, Sort sort);

    List<ProductForecastView> findByForecastVersionId_ProductIdAndEndDateBetween(String productId, LocalDate startDate, LocalDate endDate);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatus(String productId, ForecastContentStatus forecastContentStatus);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastDateDesc(String productId, ForecastContentStatus forecastContentStatus);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusAndForecastDateLessThan(String productId, ForecastContentStatus forecastContentStatus, LocalDate newForecastDate);
    void deleteAll();
}
