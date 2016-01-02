package com.affaince.subscription.integration.command.mapper;

import com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Created by mandark on 19-07-2015.
 */
public class SubscriptionableItemReceivedEventFieldSetMapper implements FieldSetMapper<ProductStatusReceivedEvent> {

    @Override
    public ProductStatusReceivedEvent mapFieldSet(FieldSet fieldSet) throws BindException {
        ProductStatusReceivedEvent productStatusReceivedEvent = new ProductStatusReceivedEvent();
        productStatusReceivedEvent.setCategoryId(fieldSet.readString("CATEGORY_ID"));
        productStatusReceivedEvent.setSubCategoryId(fieldSet.readString("SUBCATEGORY_ID"));
        productStatusReceivedEvent.setCurrentMRP(fieldSet.readDouble("CURRENT_MRP"));
        productStatusReceivedEvent.setCurrentPurchasePricePerUnit(fieldSet.readDouble("CURRENT_PURCHASE_PRICE_PER_UNIT"));
        productStatusReceivedEvent.setCurrentStockInUnits(fieldSet.readInt("CURRENT_STOCK"));
        productStatusReceivedEvent.setProductId(fieldSet.readString("PRODUCT_ID"));
        return productStatusReceivedEvent;

    }
}
