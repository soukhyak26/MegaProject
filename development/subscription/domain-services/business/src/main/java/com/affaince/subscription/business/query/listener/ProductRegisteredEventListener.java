package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.AddToRegisteredProductCountCommand;
import com.affaince.subscription.business.command.event.ProductRegisteredEvent;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.common.type.ProductStatus;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Component
public class ProductRegisteredEventListener {
    private final ProductViewRepository itemRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public ProductRegisteredEventListener(ProductViewRepository repository,SubscriptionCommandGateway commandGateway) {
        this.itemRepository = repository;
        this.commandGateway=commandGateway;
    }


    //The product registered with Product domain should also get registered with BusinessAccount domain.
    @EventHandler
    public void on(ProductRegisteredEvent event) throws Exception{
        final ProductView productView = new ProductView(
                event.getProductId(),
                event.getProductName(),
                event.getCategoryId(),
                event.getSubCategoryId(),
                event.getSensitiveTo(),
                event.getPurchasePrice(),
                event.getMRP(),
                ProductStatus.PRODUCT_REGISTERED
        );
        itemRepository.save(productView);
        Integer id = YearMonth.now().getYear();
        AddToRegisteredProductCountCommand command = new AddToRegisteredProductCountCommand(id,1);
        commandGateway.executeAsync(command);
    }
}
