package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by mandark on 28-01-2016.
 */
public interface PriceBucketViewRepository extends PagingAndSortingRepository<PriceBucketView, ProductwisePriceBucketId> {

    @Query("{ entityStatus:'ACTIVE' }")
    PriceBucketView findOne(ProductwisePriceBucketId productwisePriceBucketId);

    PriceBucketView findByProductwisePriceBucketIdAndEntityStatus (ProductwisePriceBucketId productwisePriceBucketId, EntityStatus entityStatus);
    @Query("{ entityStatus:'ACTIVE' }")
    List<PriceBucketView> findAll(Sort sort);

    List<PriceBucketView> findAllByProductwisePriceBucketIdAndEntityStatus (EntityStatus entityStatus);

    @Query("{ entityStatus:'ACTIVE' }")
    List<PriceBucketView> findByProductwisePriceBucketId_ProductId(String productId);

    List<PriceBucketView> findByProductwisePriceBucketId_ProductIdAndEntityStatus(String productId,EntityStatus entityStatus);

    List<PriceBucketView> findByProductwisePriceBucketId_ProductIdAndToDateGreaterThan(String productId,LocalDate firstDayOfMonth);
    @Query("{ entityStatus:'ACTIVE' }")
    List<PriceBucketView> findByProductwisePriceBucketId_ProductId(String productId, Sort sort);

    List<PriceBucketView> findByProductwisePriceBucketId_ProductIdAndTaggedPriceVersion_PurchasePricePerUnit(String productId, double purchasePricePerUnit);
    List<PriceBucketView> findByProductwisePriceBucketIdAndEntityStatus(ProductwisePriceBucketId productwisePriceBucketId, int entityStatus);

}
