package com.verifier.domains.product.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.verifier.domains.product.view.ProductActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualsViewRepository extends CrudRepository<ProductActualsView, ProductVersionId> {
    List<ProductActualsView> findByProductVersionId_ProductId(String productId);
    List<ProductActualsView> findByProductVersionId_StartDateAfter(LocalDate startDate);
    List<ProductActualsView> findByProductVersionId_ProductId(String productId, Sort sort);
    List<ProductActualsView> findByProductVersionId(ProductVersionId productVersionId);
    //@Query("{productVersionId.productId:?0,productVersionId.startDate:{$gte:?1},endDate:{$lte:?2}}")
    List<ProductActualsView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDate startDate, LocalDate endDate);
    List<ProductActualsView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDate startDate, LocalDate endDate, Sort sort);
    List<ProductActualsView> findByProductVersionId_ProductIdAndProductVersionId_StartDateBetween(String productId, LocalDate startDate, LocalDate endDate);
    List<ProductActualsView> findByProductVersionId_ProductIdAndEndDate(String productId, LocalDate endDate);
    ProductActualsView findFirstByProductVersionId_ProductIdOrderByProductVersionId_StartDateDesc(String productId);
}
