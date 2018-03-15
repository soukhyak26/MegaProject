package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.ProductView;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductViewPagingRepository extends PagingAndSortingRepository<ProductView, String> {
}
