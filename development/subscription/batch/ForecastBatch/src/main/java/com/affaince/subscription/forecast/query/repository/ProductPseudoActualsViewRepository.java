package com.affaince.subscription.forecast.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.forecast.query.view.ProductPseudoActualsView;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductPseudoActualsViewRepository extends CrudRepository<ProductPseudoActualsView, ProductVersionId> {
    public List<ProductPseudoActualsView> findByProductVersionId_ProductId(String productId);

    public List<ProductPseudoActualsView> findByProductVersionId_ProductId(String productId, Sort sort);

    public List<ProductPseudoActualsView> findByProductVersionId(ProductVersionId productVersionId);

}
