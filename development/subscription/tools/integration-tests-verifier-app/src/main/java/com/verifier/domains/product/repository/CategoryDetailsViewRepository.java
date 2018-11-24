package com.verifier.domains.product.repository;

import com.verifier.domains.product.view.CategoryDetailsView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 10/21/2017.
 */
public interface CategoryDetailsViewRepository extends CrudRepository<CategoryDetailsView, String> {
    public List<CategoryDetailsView> findAll();
    public List<CategoryDetailsView> findByCategoryName(String categoryName);
}
