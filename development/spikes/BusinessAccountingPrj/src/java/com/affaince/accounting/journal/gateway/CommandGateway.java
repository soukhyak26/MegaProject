package com.affaince.accounting.journal.gateway;

import com.affaince.accounting.journal.events.AccountingEventListener;
import com.affaince.accounting.journal.processor.factory.AccountIdentificationRulesProcessorFactory;
import com.affaince.accounting.transactions.SourceDocument;

import static java.util.Objects.requireNonNull;

public class CommandGateway {

    public void send(SourceDocument sourceDocument){
        AccountingEventListener accountingEventListener = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
        requireNonNull(accountingEventListener);
        accountingEventListener.onEvent(sourceDocument);
    }
}
