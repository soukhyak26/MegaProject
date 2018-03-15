package com.verifier.domains.product.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.verifier.domains.product.view.TargetSettingView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 21-10-2016.
 */
public interface TargetSettingViewRepository extends CrudRepository<TargetSettingView, ProductVersionId> {
    List<TargetSettingView> findByProductVersionId_ProductId(String productId);

    List<TargetSettingView> findByProductVersionId_ProductId(String productId, Sort sort);

    List<TargetSettingView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

    TargetSettingView findFirstByProductVersionId_ProductIdOrderByProductVersionId_StartDateDesc(String productId);

    List<TargetSettingView> findByProductVersionId_ProductIdAndProductVersionId_StartDateGreaterThan(String productId, LocalDateTime fromDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndProductVersionId_StartDateLessThan(String productId, LocalDateTime fromDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndProductVersionId_StartDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndEndDateGreaterThan(String productId, LocalDateTime endDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDateTime endDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDate startDate, LocalDate endDate);

}
