package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.affaince.subscription.payments.query.view.ProductTaggedPricesView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 5/26/2017.
 */
public interface ProductTaggedPricesViewRepository extends CrudRepository<ProductTaggedPricesView, ProductwiseTaggedPriceVersionId> {
}
