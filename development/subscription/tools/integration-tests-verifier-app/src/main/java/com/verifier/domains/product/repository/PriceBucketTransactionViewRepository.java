package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.PriceBucketTransactionView;
import com.verifier.domains.product.vo.PriceBucketTransactionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/2/2017.
 */
public interface PriceBucketTransactionViewRepository extends CrudRepository<PriceBucketTransactionView,PriceBucketTransactionId> {
    public List<PriceBucketTransactionView> findByPriceBucketTransactionId_ProductIdAndPriceBucketTransactionId_PriceBucketId(String productId, String priceBucketId);
    public List<PriceBucketTransactionView> findByPriceBucketTransactionId_ProductIdAndPriceBucketTransactionId_PriceBucketIdAndPriceBucketTransactionId_TransactionDateBetween(String productId, String priceBucketId, LocalDate startDate, LocalDate endDate);
}
