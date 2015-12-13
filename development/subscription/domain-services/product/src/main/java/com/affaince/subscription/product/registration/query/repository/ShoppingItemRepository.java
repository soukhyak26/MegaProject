package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.ShoppingItemView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandark on 07-09-2015.
 */
public interface ShoppingItemRepository extends CrudRepository<ShoppingItemView, String> {
    ShoppingItemView findOneByShoppingItemId(String itemId);
}