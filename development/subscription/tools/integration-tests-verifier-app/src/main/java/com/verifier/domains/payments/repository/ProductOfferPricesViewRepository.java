package com.verifier.domains.payments.repository;

import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.verifier.domains.payments.view.ProductOfferPricesView;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/26/2017.
 */
public interface ProductOfferPricesViewRepository extends CrudRepository<ProductOfferPricesView,ProductwisePriceBucketId>{
    public List<ProductOfferPricesView> findByProductwisePriceBucketId_ProductId(String productId);
    public List<ProductOfferPricesView> findByProductwisePriceBucketId_ProductIdAndToDate(String productId, LocalDateTime toDate);
    public List<ProductOfferPricesView> findByProductwisePriceBucketId_ProductId(String productId, Sort sort);

}