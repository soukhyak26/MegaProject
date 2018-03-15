package com.verifier.domains.product.repository;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.verifier.domains.product.view.TaggedPriceVersionsView;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 31-12-2016.
 */
public interface TaggedPriceVersionsViewRepository extends CrudRepository<TaggedPriceVersionsView, ProductwiseTaggedPriceVersionId> {
    TaggedPriceVersionsView findOne(ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId);
    List<TaggedPriceVersionsView> findByProductwiseTaggedPriceVersionId_ProductId(String productId);
    List<TaggedPriceVersionsView> findByProductwiseTaggedPriceVersionId_ProductIdAndTaggedStartDateBetween(String productId, LocalDate startDate, LocalDate endDate);
    List<TaggedPriceVersionsView> findByProductwiseTaggedPriceVersionId_ProductId(String productId, Sort sort);
}
