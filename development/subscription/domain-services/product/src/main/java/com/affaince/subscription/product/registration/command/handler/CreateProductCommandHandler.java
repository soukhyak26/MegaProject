package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.CreateProductCommand;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 25-07-2015.
 */

@Component
public class CreateProductCommandHandler {

    private Repository<Product> itemRepository;

    @Autowired
    public CreateProductCommandHandler(Repository<Product> itemRepository) {
        this.itemRepository = itemRepository;
    }

    @CommandHandler
    public void handle(CreateProductCommand command) {
        Product product = new Product(
                command.getItemId(),
                command.getBatchId(),
                command.getCategoryId(),
                command.getCategoryName(),
                command.getSubCategoryId(),
                command.getGetSubCategoryNmae(),
                command.getProductId(),
                command.getCurrentPurchasePricePerUnit(),
                command.getCurrentMRP(),
                command.getCurrentOfferedPrice(),
                command.getCurrentStockInUnits(),
                command.getCurrentPriceDate()
        );
        itemRepository.add(product);
    }
}
