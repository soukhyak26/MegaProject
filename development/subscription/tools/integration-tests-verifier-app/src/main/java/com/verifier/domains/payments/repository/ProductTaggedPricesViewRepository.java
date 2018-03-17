package com.verifier.domains.payments.repository;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.verifier.domains.payments.view.ProductTaggedPricesView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/26/2017.
 */
public interface ProductTaggedPricesViewRepository extends CrudRepository<ProductTaggedPricesView, ProductwiseTaggedPriceVersionId> {
    public List<ProductTaggedPricesView> findByProductwiseTaggedPriceVersionId_ProductIdAndTaggedPriceEndaDate(String productId, LocalDate endDate);
    public List<ProductTaggedPricesView> findByProductwiseTaggedPriceVersionId_ProductId(String productId);
}
