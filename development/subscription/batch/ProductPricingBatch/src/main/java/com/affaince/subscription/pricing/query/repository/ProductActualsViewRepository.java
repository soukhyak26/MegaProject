package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.view.ProductActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualsViewRepository extends CrudRepository<ProductActualsView, ProductVersionId> {
    public List<ProductActualsView> findByProductVersionId_ProductId(String productId);

    public List<ProductActualsView> findByProductVersionId_ProductId(String productId, Sort sort);

    public List<ProductActualsView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

    @Query("{productVersionId.productId:?0,productVersionId.startDate:{$gte:?1},endDate:{$lte:?2}}")
    public List<ProductActualsView> findByProductVersionId_ProductIdAndDateBetween(String productId, LocalDate startDate, LocalDate endDate);


}
