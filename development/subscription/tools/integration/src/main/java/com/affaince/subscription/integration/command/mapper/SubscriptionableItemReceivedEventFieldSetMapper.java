package com.affaince.subscription.integration.command.mapper;

import com.affaince.subscription.integration.command.event.SubscriptionableItemReceivedEvent;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Created by mandark on 19-07-2015.
 */
public class SubscriptionableItemReceivedEventFieldSetMapper implements FieldSetMapper<SubscriptionableItemReceivedEvent> {

    @Override
    public SubscriptionableItemReceivedEvent mapFieldSet(FieldSet fieldSet) throws BindException {
        SubscriptionableItemReceivedEvent subscriptionableItemReceivedEvent = new SubscriptionableItemReceivedEvent();
        subscriptionableItemReceivedEvent.setBatchId(fieldSet.readString("BATCH_ID"));
        subscriptionableItemReceivedEvent.setCategoryId(fieldSet.readString("CATEGORY_ID"));
        subscriptionableItemReceivedEvent.setCategoryName(fieldSet.readString("CATEGORY_NAME"));
        subscriptionableItemReceivedEvent.setSubCategoryId(fieldSet.readString("SUBCATEGORY_ID"));
        subscriptionableItemReceivedEvent.setSubCategoryName(fieldSet.readString("SUBCATEGORY_NAME"));
        subscriptionableItemReceivedEvent.setCurrentMRP(fieldSet.readDouble("CURRENT_MRP"));
        subscriptionableItemReceivedEvent.setCurrentPurchasePricePerUnit(fieldSet.readDouble("CURRENT_PURCHASE_PRICE_PER_UNIT"));
        subscriptionableItemReceivedEvent.setCurrentStockInUnits(fieldSet.readInt("CURRENT_STOCK"));
        subscriptionableItemReceivedEvent.setProductId(fieldSet.readString("PRODUCT_ID"));
        return subscriptionableItemReceivedEvent;

    }
}
