package com.affaince.subscription.product.build;

import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 6/25/2017.
 */
@Component
public class ProductActualsViewBuilder {
    @Autowired
    ProductActualsViewRepository productActualsViewRepository;
}
