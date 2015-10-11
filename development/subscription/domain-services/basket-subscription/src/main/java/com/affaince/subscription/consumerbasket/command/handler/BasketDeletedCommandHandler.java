package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.consumerbasket.command.BasketDeletedCommand;
import com.affaince.subscription.consumerbasket.command.domain.Basket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 11-10-2015.
 */
@Component
public class BasketDeletedCommandHandler {

    private final Repository<Basket> basketRepository;

    @Autowired
    public BasketDeletedCommandHandler(Repository<Basket> basketRepository) {
        this.basketRepository = basketRepository;
    }

    @CommandHandler
    public void handle(BasketDeletedCommand command) {
        final Basket basket = basketRepository.load(command.getBasketId());
        basket.deleteBasket();
    }
}
