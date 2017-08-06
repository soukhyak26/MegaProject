package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.affaince.subscription.payments.command.event.ProductRegisteredEvent;
import com.affaince.subscription.payments.query.repository.ProductTaggedPricesViewRepository;
import com.affaince.subscription.payments.query.repository.ProductViewRepository;
import com.affaince.subscription.payments.query.view.ProductTaggedPricesView;
import com.affaince.subscription.payments.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/26/2017.
 */
@Component
public class ProductRegisteredEventListener {
    private ProductTaggedPricesViewRepository productTaggedPricesViewRepository;
    private ProductViewRepository productViewRepository;
    @Autowired
    public ProductRegisteredEventListener(ProductTaggedPricesViewRepository productTaggedPricesViewRepository,ProductViewRepository productViewRepository) {
        this.productTaggedPricesViewRepository = productTaggedPricesViewRepository;
        this.productViewRepository=productViewRepository;
    }

    @EventHandler
    public void on (ProductRegisteredEvent event){
        ProductTaggedPricesView productTaggedPricesView=new ProductTaggedPricesView(new ProductwiseTaggedPriceVersionId(event.getProductId(),event.getTaggedPriceVersionId()),event.getPurchasePrice(),event.getMrp(),event.getRegistrationDate());
        productTaggedPricesViewRepository.save(productTaggedPricesView);
        ProductView productView= new ProductView(event.getProductId(),event.getProductName(),event.getProductPricingCategory());
        productViewRepository.save(productView);
    }
}
