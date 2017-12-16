package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.ProductActivateCommand;
import com.affaince.subscription.product.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
@Component
public class ProductActivateCommandHandler {

    private final Repository<Product> productRepository;

    @Autowired
    public ProductActivateCommandHandler(Repository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @CommandHandler
    public void handle(ProductActivateCommand command) {
        final Product product = productRepository.load(command.getProductId());
        product.activateProduct();
    }
}
