package com.affaince.subscription.integration.command.mapper;

import com.affaince.subscription.integration.command.FetchSubscriptonableItemCommand;
import com.affaince.subscription.integration.command.processor.FetchSubscriptonableItemCommandProcessor;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Created by mandark on 19-07-2015.
 */
public class FetchSubscriptonableItemCommandFieldSetMapper implements FieldSetMapper<FetchSubscriptonableItemCommand> {

    @Override
    public FetchSubscriptonableItemCommand mapFieldSet(FieldSet fieldSet) throws BindException {
        FetchSubscriptonableItemCommand fetchSubscriptonableItemCommand = new FetchSubscriptonableItemCommand();
        fetchSubscriptonableItemCommand.setBatchId(fieldSet.readString("BATCH_ID"));
        fetchSubscriptonableItemCommand.setCategroyId(fieldSet.readString("CATEGORY_ID"));
        fetchSubscriptonableItemCommand.setCategroyName(fieldSet.readString("CATEGORY_NAME"));
        fetchSubscriptonableItemCommand.setSubCategoryId(fieldSet.readString("SUBCATEGORY_ID"));
        fetchSubscriptonableItemCommand.setSubCategoryName(fieldSet.readString("SUBCATEGORY_NAME"));
        fetchSubscriptonableItemCommand.setCurrentMRP(fieldSet.readDouble("CURRENT_MRP"));
        fetchSubscriptonableItemCommand.setCurrentPurchasePricePerUnit(fieldSet.readDouble("CURRENT_PURCHASE_PRICE_PER_UNIT"));
        fetchSubscriptonableItemCommand.setCurrentStockInUnits(fieldSet.readInt("CURRENT_STOCK"));
        fetchSubscriptonableItemCommand.setProductId(fieldSet.readString("PRODUCT_ID"));
        return fetchSubscriptonableItemCommand;

    }
}
