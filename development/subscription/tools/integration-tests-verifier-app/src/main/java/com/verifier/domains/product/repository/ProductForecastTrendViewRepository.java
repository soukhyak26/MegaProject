package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.ProductForecastTrendView;
import com.verifier.domains.product.vo.ForecastVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/29/2017.
 */
public interface ProductForecastTrendViewRepository extends CrudRepository<ProductForecastTrendView,ForecastVersionId> {
    public List<ProductForecastTrendView> findByForecastVersionId_ProductId(String productId);

}
