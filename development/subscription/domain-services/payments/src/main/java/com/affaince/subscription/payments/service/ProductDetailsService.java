package com.affaince.subscription.payments.service;

import com.affaince.subscription.payments.query.repository.ProductViewRepository;
import com.affaince.subscription.payments.query.view.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/28/2017.
 */
@Component
public class ProductDetailsService {
    private ProductViewRepository productViewRepository;
    @Autowired
    public ProductDetailsService(ProductViewRepository productViewRepository) {
        this.productViewRepository = productViewRepository;
    }

    public ProductView findProductByProductId(String productId){
        return productViewRepository.findOne(productId);
    }
}
