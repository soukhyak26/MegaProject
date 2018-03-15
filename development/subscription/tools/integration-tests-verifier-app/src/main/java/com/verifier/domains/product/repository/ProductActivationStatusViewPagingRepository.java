package com.verifier.domains.product.repository;

import com.affaince.subscription.common.type.ProductStatus;
import com.verifier.domains.product.view.ProductActivationStatusView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductActivationStatusViewPagingRepository extends PagingAndSortingRepository<ProductActivationStatusView, String> {
    Page<ProductActivationStatusView> findAllByProductStatusesIn (ProductStatus productStatus, Pageable pageable);
}
