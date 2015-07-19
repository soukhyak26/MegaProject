package com.affaince.subscription.integration.command.processor;

import com.affaince.subscription.integration.command.FetchSubscriptonableItemCommand;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by mandark on 18-07-2015.
 */
public class FetchSubscriptonableItemCommandProcessor implements ItemProcessor<FetchSubscriptonableItemCommand, FetchSubscriptonableItemCommand> {

    @Override
    public FetchSubscriptonableItemCommand process(FetchSubscriptonableItemCommand fetchSubscriptonableItemCommand) throws Exception {
        return fetchSubscriptonableItemCommand;
    }
}
