package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.view.ProductActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualsViewRepository extends CrudRepository<ProductActualsView, ProductVersionId> {

    public List<ProductActualsView> findByProductVersionId_ProductId(String productId, Sort sort);
    public List<ProductActualsView> findByProductVersionId(ProductVersionId productVersionId);
}
