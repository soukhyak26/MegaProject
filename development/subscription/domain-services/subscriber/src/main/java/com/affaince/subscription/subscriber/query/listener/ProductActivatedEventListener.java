package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.ProductActivatedEvent;
import com.affaince.subscription.subscriber.query.repository.ProductViewRepository;
import com.affaince.subscription.subscriber.query.view.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-10-2016.
 */
@Component
public class ProductActivatedEventListener {

    private final ProductViewRepository productViewRepository;

    @Autowired
    public ProductActivatedEventListener(ProductViewRepository productViewRepository) {
        this.productViewRepository = productViewRepository;
    }

    public void on(ProductActivatedEvent event) {
        final ProductView productView = new ProductView(event.getProductId(),
                event.getProductName(), event.getCategoryId(), event.getSubCategoryId(),
                event.getNetQuantity(), event.getQuantityUnit(), event.getSubstitutes(), event.getComplements());
        productViewRepository.save(productView);
    }
}
