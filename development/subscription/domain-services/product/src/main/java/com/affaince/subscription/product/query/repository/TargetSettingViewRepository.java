package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.TargetSettingView;
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

    TargetSettingView findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc(String productId);

    List<TargetSettingView> findByProductVersionId_ProductIdAndProductVersionId_FromDateGreaterThan(String productId, LocalDateTime fromDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndProductVersionId_FromDateLessThan(String productId, LocalDateTime fromDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndEndDateGreaterThan(String productId, LocalDateTime endDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDateTime endDate);

    List<TargetSettingView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);

}
