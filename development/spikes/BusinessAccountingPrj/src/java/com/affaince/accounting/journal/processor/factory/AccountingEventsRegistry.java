package com.affaince.accounting.journal.processor.factory;

import com.affaince.accounting.journal.events.AccountingEventListener;
import com.affaince.accounting.journal.events.*;
import com.affaince.accounting.journal.qualifiers.AccountingEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountingEventsRegistry {
    private static AccountingEventsRegistry accountingEventsRegistry;
    private Map<AccountingEvent, List<AccountingEventListener>> accountingEventListenersRegistry;

    private AccountingEventsRegistry(){
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
    }
    public static AccountingEventsRegistry getInstance(){
        if(null == accountingEventsRegistry){
            accountingEventsRegistry= new AccountingEventsRegistry();
            accountingEventsRegistry.registerDefaults();
        }
        return accountingEventsRegistry;
    }

    public static List<AccountingEventListener> getAccountingEventListener(AccountingEvent accountingEvent, AccountingEventListener accountingEventListener){
        if( accountingEventsRegistry.accountingEventListenersRegistry.containsKey(accountingEvent)) {
            accountingEventsRegistry.accountingEventListenersRegistry.get(accountingEvent).add(accountingEventListener);
        }
        return null;
    }
    private void registerDefaults(){
        this.getAccountingEventListener(AccountingEvent.CAPITAL_INVESTMENT,new CapitalInvestmentEventProcessor());
        this.getAccountingEventListener(AccountingEvent.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER,new DistributionServicesAvailedEventProcessor());
        this.getAccountingEventListener(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS,new GoodsPurchaseEventProcessor());
        this.getAccountingEventListener(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER,new GoodsDeliveryToSubscriberEventProcessor());
        this.getAccountingEventListener(AccountingEvent.PAYMENT_MADE_TO_SERVICE_PROVIDER,new DistributionServicesPaymentEventProcessor());
        this.getAccountingEventListener(AccountingEvent.PAYMENT_MADE_TO_SUPPLIER,new GoodsPaymentToSupplierEventProcessor());
        this.getAccountingEventListener(AccountingEvent.PAYMENT_RECEIVED_FROM_SUBSCRIBER,new PaymentReceiptFromSubscriberEventProcessor());
        this.getAccountingEventListener(AccountingEvent.TAX_PAYMENT,new TaxPaymentEventProcessor());
        this.getAccountingEventListener(AccountingEvent.PAYMENT_OF_RENT,new PremiseRentPaymentEventProcessor());
        this.getAccountingEventListener(AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER,new SalesReturnEventProcessor());
        this.getAccountingEventListener(AccountingEvent.PURCHASE_RETURN_BY_BUSINESS,new PurchaseReturnEventProcessor());
        this.getAccountingEventListener(AccountingEvent.SUPPLIER_PAYMENT_TOWARDS_PURCHASE_RETURN,new SupplierPaymentTowardsPurchaseReturnEventProcessor());
    }
    public List<AccountingEventListener> getAccountingEventListener(AccountingEvent accountingEvent){
        return this.accountingEventListenersRegistry.get(accountingEvent);
    }
}
