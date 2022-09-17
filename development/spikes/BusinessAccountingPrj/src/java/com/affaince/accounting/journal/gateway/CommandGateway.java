package com.affaince.accounting.journal.gateway;

import com.affaince.accounting.journal.events.AccountingEventListener;
import com.affaince.accounting.journal.processor.factory.AccountingEventsRegistry;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.List;


public class CommandGateway {

    public void send(SourceDocument sourceDocument){
        List<AccountingEventListener> accountingEventListeners =
                AccountingEventsRegistry.getInstance().getAccountingEventListener(sourceDocument.getAccountingEvent());
        for(AccountingEventListener listener: accountingEventListeners) {
            listener.onEvent(sourceDocument);
        }
    }
}
