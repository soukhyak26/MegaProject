package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductPseudoActualsView;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductPseudoActualsViewRepository extends CrudRepository<ProductPseudoActualsView, ProductVersionId> {
    public List<ProductPseudoActualsView> findByProductVersionId_ProductId(String productId);
    public List<ProductPseudoActualsView> findByProductVersionId_ProductId(String productId, Sort sort);
    public List<ProductPseudoActualsView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

    public List<ProductPseudoActualsView> findByProductVersionId_ProductIdAndProductVersionId_FromDateGreaterThan(String productId, LocalDateTime fromDate);

    public List<ProductPseudoActualsView> findByProductVersionId_ProductIdAndProductVersionId_FromDateLessThan(String productId, LocalDateTime fromDate);

    public List<ProductPseudoActualsView> findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);

    public List<ProductPseudoActualsView> findByProductVersionId_ProductIdAndEndDateGreaterThan(String productId, LocalDateTime endDate);

    public List<ProductPseudoActualsView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDateTime endDate);

    public List<ProductPseudoActualsView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);
}
