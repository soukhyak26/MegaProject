package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.view.ProductPseudoActualsView;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */
@Deprecated
public interface ProductPseudoActualsViewRepository extends CrudRepository<ProductPseudoActualsView, ProductVersionId> {
    public List<ProductPseudoActualsView> findByProductVersionId_ProductId(String productId);

    public List<ProductPseudoActualsView> findByProductVersionId_ProductId(String productId, Sort sort);

    public List<ProductPseudoActualsView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

}
