package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.affaince.subscription.payments.command.event.ProductRegisteredEvent;
import com.affaince.subscription.payments.command.event.ProductStatusUpdatedEvent;
import com.affaince.subscription.payments.exception.MultipleActiveTaggedPricesException;
import com.affaince.subscription.payments.query.repository.ProductTaggedPricesViewRepository;
import com.affaince.subscription.payments.query.view.ProductTaggedPricesView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 5/27/2017.
 */
@Component
public class ProductStatusUpdatedEventListener {
    private ProductTaggedPricesViewRepository productTaggedPricesViewRepository;
    @Autowired
    public ProductStatusUpdatedEventListener(ProductTaggedPricesViewRepository productTaggedPricesViewRepository) {
        this.productTaggedPricesViewRepository = productTaggedPricesViewRepository;
    }

    @EventHandler
    public void on (ProductStatusUpdatedEvent event){
        List<ProductTaggedPricesView> activeTaggedPriceVersionsOfAProduct=productTaggedPricesViewRepository.findByProductwiseTaggedPriceVersionId_ProductIdAndTaggedPriceEndaDate(event.getProductId(),new LocalDate(9999,12,31));
        if(activeTaggedPriceVersionsOfAProduct.size()==1){
            activeTaggedPriceVersionsOfAProduct.get(0).setTaggedPriceEndaDate(event.getNewTaggedPrice().getTaggedStartDate());
        }else{
            throw MultipleActiveTaggedPricesException.build(event.getProductId());
        }
        ProductTaggedPricesView productTaggedPricesView=new ProductTaggedPricesView(new ProductwiseTaggedPriceVersionId(event.getProductId(),event.getNewTaggedPrice().getTaggedPriceVersionId()),event.getNewTaggedPrice().getPurchasePricePerUnit(),event.getNewTaggedPrice().getMRP(),event.getNewTaggedPrice().getTaggedStartDate());
        productTaggedPricesViewRepository.save(productTaggedPricesView);
    }

}
