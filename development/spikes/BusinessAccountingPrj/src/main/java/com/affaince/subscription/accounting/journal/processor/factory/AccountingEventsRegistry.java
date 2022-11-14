package com.affaince.subscription.accounting.journal.processor.factory;

import com.affaince.subscription.accounting.journal.events.AccountingEventListener;
import com.affaince.subscription.accounting.journal.qualifiers.AccountingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class AccountingEventsRegistry {
    private final Map<AccountingEvent, List<AccountingEventListener>> accountingEventListenersRegistry;

    @Autowired
    public AccountingEventsRegistry() {
        accountingEventListenersRegistry = new HashMap<>();
        accountingEventListenersRegistry.put(AccountingEvent.CAPITAL_INVESTMENT,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.PAYMENT_MADE_TO_SERVICE_PROVIDER,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.PAYMENT_MADE_TO_SUPPLIER,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.PAYMENT_RECEIVED_FROM_SUBSCRIBER,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.TAX_PAYMENT,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.PAYMENT_OF_RENT,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.PURCHASE_RETURN_BY_BUSINESS,new ArrayList<>());
        accountingEventListenersRegistry.put(AccountingEvent.SUPPLIER_PAYMENT_TOWARDS_PURCHASE_RETURN,new ArrayList<>());
        registerDefaults();
    }


    public List<AccountingEventListener> registerAccountingEventListener(AccountingEvent accountingEvent, AccountingEventListener accountingEventListener){
        if( accountingEventListenersRegistry.containsKey(accountingEvent)) {
            accountingEventListenersRegistry.get(accountingEvent).add(accountingEventListener);
        }
        return null;
    }
    private void registerDefaults(){
    }
    public List<AccountingEventListener> getAccountingEventListener(AccountingEvent accountingEvent){
        return this.accountingEventListenersRegistry.get(accountingEvent);
    }
}
