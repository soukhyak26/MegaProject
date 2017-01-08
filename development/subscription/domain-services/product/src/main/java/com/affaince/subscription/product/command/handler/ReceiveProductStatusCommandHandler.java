package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 02-04-2016.
 */
@Component
public class ReceiveProductStatusCommandHandler {
    private final Repository<Product> repository;
    private final OperatingExpenseService operatingExpenseService;

    @Autowired
    public ReceiveProductStatusCommandHandler(Repository<Product> repository, OperatingExpenseService operatingExpenseService) {
        this.repository = repository;
        this.operatingExpenseService = operatingExpenseService;
    }

    @CommandHandler
    public void handle(ReceiveProductStatusCommand command) {
        Product product = repository.load(command.getProductId());
        product.receiveProductStatus(command, operatingExpenseService);

    }
}
