package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.pricing.query.repository.ProductViewRepository;
import com.affaince.subscription.pricing.query.view.ProductView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 16-08-2016.
 */
public class ProductsRetriever {
    @Autowired
    private ProductViewRepository productViewRepository;

    public List<String> retrieveProductIds() {
        System.out.println("in ProductsRetriever###############");
        Iterable<ProductView> allProducts = productViewRepository.findAll();
        List<String> allProductIds = new ArrayList<>();
        allProducts.forEach(productView -> allProductIds.add(productView.getProductId()));
        return allProductIds;
    }
}
