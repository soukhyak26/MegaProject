package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.DonateToNodalAccountCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 30-01-2017.
 */
@Component
public class DonateToNodalAccountCommandHandler {
    private Repository<Product> repository;
    @Autowired
    public DonateToNodalAccountCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DonateToNodalAccountCommand command){
        Product product= repository.load(command.getProductId());
        product.donateToNodalAccount(command.getWeight());
    }
}
