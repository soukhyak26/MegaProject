package com.affaince.subscription.subscriptionableitem.registration.command.handler;

import com.affaince.subscription.subscriptionableitem.registration.command.CreateSubscriptionableItemCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.domain.SubscriptionableItem;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 25-07-2015.
 */

@Component
public class CreateSubscriptionableItemCommandHandler {

    private Repository<SubscriptionableItem> itemRepository;

    @Autowired
    public CreateSubscriptionableItemCommandHandler (Repository<SubscriptionableItem> itemRepository) {
        this.itemRepository = itemRepository;
    }

    @CommandHandler
    public void handle (CreateSubscriptionableItemCommand command) {
        SubscriptionableItem subscriptionableItem = new SubscriptionableItem(
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
        itemRepository.add(subscriptionableItem);
    }
}
