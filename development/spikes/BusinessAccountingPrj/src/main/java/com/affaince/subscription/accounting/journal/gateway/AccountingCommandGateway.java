package com.affaince.subscription.accounting.journal.gateway;

import com.affaince.subscription.accounting.journal.events.AccountingEventListener;
import com.affaince.subscription.accounting.journal.processor.factory.AccountingEventsRegistry;
import com.affaince.subscription.accounting.transactions.SourceDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountingCommandGateway {

    private final AccountingEventsRegistry accountingEventsRegistry;
    @Autowired
    public AccountingCommandGateway(AccountingEventsRegistry accountingEventsRegistry){
        this.accountingEventsRegistry = accountingEventsRegistry;
    }
    public void send(SourceDocument sourceDocument){
        List<AccountingEventListener> accountingEventListeners =
                accountingEventsRegistry.getAccountingEventListener(sourceDocument.getAccountingEvent());
        for(AccountingEventListener listener: accountingEventListeners) {
            listener.onEvent(sourceDocument);
        }
    }
}
