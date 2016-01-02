package com.affaince.subscription.integration.command.writer;

import com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by mandark on 19-07-2015.
 */
public class Writer implements ItemWriter<ProductStatusReceivedEvent> {

    @Override
    public void write(List<? extends ProductStatusReceivedEvent> list) throws Exception {

    }
}
