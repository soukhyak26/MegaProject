package com.verifier.domains.product.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.product.view.ProductPseudoActualsView;
import com.verifier.domains.product.vo.ForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductPseudoActualsViewRepository extends CrudRepository<ProductPseudoActualsView, ForecastVersionId> {
    List<ProductPseudoActualsView> findByForecastVersionId_ProductId(String productId);

    List<ProductPseudoActualsView> findByForecastVersionId_ProductId(String productId, Sort sort);

    List<ProductPseudoActualsView> findByForecastVersionId_ProductIdAndForecastVersionId_StartDate(String productId, LocalDate startDate);

    List<ProductPseudoActualsView> findByForecastVersionId_ProductIdAndEndDate(String productId, LocalDate endDate);
    List<ProductPseudoActualsView> findByForecastVersionId_ProductIdAndForecastContentStatus(String productId, ForecastContentStatus forecastContentStatus);
    List<ProductPseudoActualsView> findByForecastVersionId_ProductIdAndForecastContentStatusAndForecastVersionId_ForecastDateLessThan(String productId, ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    List<ProductPseudoActualsView> findByForecastVersionId_ProductIdAndForecastContentStatusAndForecastVersionId_ForecastDate(String productId, ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
}
