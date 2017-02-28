package com.affaince.subscription.metrics.build;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.metrics.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.metrics.query.view.ProductActivationStatusView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 16-08-2016.
 */
public class ProductsRetriever {
    @Autowired
    private ProductActivationStatusViewRepository productActivationStatusViewRepository;

    public List<String> retrieveProductIds() {
        System.out.println("in ProductsRetriever###############");
        Iterable<ProductActivationStatusView> allProducts = productActivationStatusViewRepository.findAll();
        List<String> allActiveProductIds = new ArrayList<>();
        allProducts.forEach(productActivationStatusView -> {
            if (productActivationStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_ACTIVATED)) {
                allActiveProductIds.add(productActivationStatusView.getProductId());
            }
        });
        return allActiveProductIds;
    }
}
