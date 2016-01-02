package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.ShoppingItemReceivedEvent;
import com.affaince.subscription.product.registration.query.repository.ShoppingItemViewRepository;
import com.affaince.subscription.product.registration.query.view.ShoppingItemView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 07-09-2015.
 */
@Component
public class ShoppingItemReceiptListener {
    private final ShoppingItemViewRepository shoppingItemViewRepository;

    @Autowired
    public ShoppingItemReceiptListener(ShoppingItemViewRepository shoppingItemViewRepository) {
        this.shoppingItemViewRepository = shoppingItemViewRepository;
    }

    @EventHandler
    public void on(ShoppingItemReceivedEvent shoppingItemReceivedEvent) {
        ShoppingItemView shoppingItemView = new ShoppingItemView();
        shoppingItemView.setProductId(shoppingItemReceivedEvent.getProductId());
        shoppingItemView.setCategroyId(shoppingItemReceivedEvent.getCategroyId());
        shoppingItemView.setCategoryName(shoppingItemReceivedEvent.getCategoryName());
        shoppingItemView.setSubCategoryId(shoppingItemReceivedEvent.getSubCategoryId());
        shoppingItemView.setSubCategoryName(shoppingItemReceivedEvent.getSubCategoryName());
        shoppingItemView.setShoppingItemId(shoppingItemReceivedEvent.getShoppingItemId());
    }
}
