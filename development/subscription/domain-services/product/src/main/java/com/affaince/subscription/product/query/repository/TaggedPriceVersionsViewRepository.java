package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.TaggedPriceVersionsView;
import com.affaince.subscription.product.vo.ProductwiseTaggedPriceVersionId;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 31-12-2016.
 */
public interface TaggedPriceVersionsViewRepository extends CrudRepository<TaggedPriceVersionsView, ProductwiseTaggedPriceVersionId> {
    TaggedPriceVersionsView findOne(ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId);
    List<TaggedPriceVersionsView> findByProductwiseTaggedPriceVersionId_ProductId(String productId);
    List<TaggedPriceVersionsView> findByProductwiseTaggedPriceVersionId_ProductId(String productId,Sort sort);
}
