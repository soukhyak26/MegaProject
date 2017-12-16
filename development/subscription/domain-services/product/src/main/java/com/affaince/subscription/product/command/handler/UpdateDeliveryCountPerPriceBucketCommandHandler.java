package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateDeliveryCountPerPriceBucketCommand;
import com.affaince.subscription.product.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 31-12-2016.
 */
@Component
public class UpdateDeliveryCountPerPriceBucketCommandHandler {

    private final Repository <Product> productRepository;

    @Autowired
    public UpdateDeliveryCountPerPriceBucketCommandHandler(Repository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @CommandHandler
    public void handle (UpdateDeliveryCountPerPriceBucketCommand command) {
        final Product product = productRepository.load(command.getProductId());
        product.getProductAccount().addDeliveredSubscriptionCountToRespectivePriceBucket(command.getProductId(),command.getPriceBucketId(),command.getDispatchDate());
        product.getProductAccount().calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(command.getProductId(),command.getPriceBucketId(),1,command.getDispatchDate());
    }
}
