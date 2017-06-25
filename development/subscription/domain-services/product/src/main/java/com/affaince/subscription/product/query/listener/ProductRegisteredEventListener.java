package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.event.ProductRegisteredEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.repository.TaggedPriceVersionsViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.query.view.TaggedPriceVersionsView;
import com.affaince.subscription.product.web.exception.ProductReadinessException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Component
public class ProductRegisteredEventListener {
    private final ProductViewRepository itemRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;
    @Autowired
    public ProductRegisteredEventListener(ProductViewRepository repository,
                                          ProductActivationStatusViewRepository productActivationStatusViewRepository,TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository) {
        this.itemRepository = repository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.taggedPriceVersionsViewRepository=taggedPriceVersionsViewRepository;
    }


    @EventHandler
    public void on(ProductRegisteredEvent event) throws ProductReadinessException {
        /*final List<ProductStatus> productStatuses = productActivationStatusViewRepository.
                findByProductId(event.getProductId()).getProductStatuses();
        if (ProductConfigurationValidator.getProductReadinessStatus(productStatuses).contains(
                ProductReadinessStatus.REGISTERABLE
        )) {*/
            final ProductView productView = new ProductView(
                    event.getProductId(),
                    event.getProductName(),
                    event.getCategoryId(),
                    event.getSubCategoryId(),
                    event.getQuantity(),
                    event.getQuantityUnit(),
                    event.getSubstitutes(),
                    event.getComplements(),
                    event.getSensitiveTo()
            );
            itemRepository.save(productView);

            TaggedPriceVersionsView taggedPriceVersionsView=new TaggedPriceVersionsView(event.getProductId(), SysDate.now().toString(),event.getPurchasePrice(),event.getMrp(),event.getRegistrationDate(),new LocalDate(999,12,31));
            taggedPriceVersionsViewRepository.save(taggedPriceVersionsView);
            final ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView(event.getProductId(), new ArrayList<>());
            productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
            productActivationStatusViewRepository.save(productActivationStatusView);
    } /*else {
            throw ProductReadinessException.client(event.getProductId(), ProductStatus.PRODUCT_REGISTERED);
        }*/
    //}
}
