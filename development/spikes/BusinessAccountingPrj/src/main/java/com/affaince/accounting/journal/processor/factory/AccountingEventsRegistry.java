package com.affaince.accounting.journal.processor.factory;

import com.affaince.accounting.journal.events.AccountingEventListener;
import com.affaince.accounting.journal.events.*;
import com.affaince.accounting.journal.qualifiers.AccountingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class AccountingEventsRegistry {
    private final Map<AccountingEvent, List<AccountingEventListener>> accountingEventListenersRegistry;
    private final CapitalInvestmentEventProcessor capitalInvestmentEventProcessor;
    private final DistributionServicesAvailedEventProcessor distributionServicesAvailedEventProcessor;
    private final GoodsPurchaseEventProcessor goodsPurchaseEventProcessor;
    private final GoodsDeliveryToSubscriberEventProcessor goodsDeliveryToSubscriberEventProcessor;
    private final DistributionServicesPaymentEventProcessor distributionServicesPaymentEventProcessor;
    private final GoodsPaymentToSupplierEventProcessor goodsPaymentToSupplierEventProcessor;
    private final PaymentReceiptFromSubscriberEventProcessor paymentReceiptFromSubscriberEventProcessor;
    private final TaxPaymentEventProcessor taxPaymentEventProcessor;
    private final PremiseRentPaymentEventProcessor premiseRentPaymentEventProcessor;
    private final SalesReturnEventProcessor salesReturnEventProcessor;
    private final PurchaseReturnEventProcessor purchaseReturnEventProcessor;
    private final SupplierPaymentTowardsPurchaseReturnEventProcessor supplierPaymentTowardsPurchaseReturnEventProcessor;

    @Autowired
    public AccountingEventsRegistry(Map<AccountingEvent, List<AccountingEventListener>> accountingEventListenersRegistry, CapitalInvestmentEventProcessor capitalInvestmentEventProcessor, DistributionServicesAvailedEventProcessor distributionServicesAvailedEventProcessor, GoodsPurchaseEventProcessor goodsPurchaseEventProcessor, GoodsDeliveryToSubscriberEventProcessor goodsDeliveryToSubscriberEventProcessor, DistributionServicesPaymentEventProcessor distributionServicesPaymentEventProcessor, GoodsPaymentToSupplierEventProcessor goodsPaymentToSupplierEventProcessor, PaymentReceiptFromSubscriberEventProcessor paymentReceiptFromSubscriberEventProcessor, TaxPaymentEventProcessor taxPaymentEventProcessor, PremiseRentPaymentEventProcessor premiseRentPaymentEventProcessor, SalesReturnEventProcessor salesReturnEventProcessor, PurchaseReturnEventProcessor purchaseReturnEventProcessor, SupplierPaymentTowardsPurchaseReturnEventProcessor supplierPaymentTowardsPurchaseReturnEventProcessor) {
        this.accountingEventListenersRegistry = accountingEventListenersRegistry;
        this.capitalInvestmentEventProcessor = capitalInvestmentEventProcessor;
        this.distributionServicesAvailedEventProcessor = distributionServicesAvailedEventProcessor;
        this.goodsPurchaseEventProcessor = goodsPurchaseEventProcessor;
        this.goodsDeliveryToSubscriberEventProcessor = goodsDeliveryToSubscriberEventProcessor;
        this.distributionServicesPaymentEventProcessor = distributionServicesPaymentEventProcessor;
        this.goodsPaymentToSupplierEventProcessor = goodsPaymentToSupplierEventProcessor;
        this.paymentReceiptFromSubscriberEventProcessor = paymentReceiptFromSubscriberEventProcessor;
        this.taxPaymentEventProcessor = taxPaymentEventProcessor;
        this.premiseRentPaymentEventProcessor = premiseRentPaymentEventProcessor;
        this.salesReturnEventProcessor = salesReturnEventProcessor;
        this.purchaseReturnEventProcessor = purchaseReturnEventProcessor;
        this.supplierPaymentTowardsPurchaseReturnEventProcessor = supplierPaymentTowardsPurchaseReturnEventProcessor;

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


/*
    public AccountingEventsRegistry(){
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
*/
/*
    public static AccountingEventsRegistry getInstance(){
        if(null == accountingEventsRegistry){
            accountingEventsRegistry= new AccountingEventsRegistry();
            accountingEventsRegistry.registerDefaults();
        }
        return accountingEventsRegistry;
    }
*/

    public List<AccountingEventListener> getAccountingEventListener(AccountingEvent accountingEvent, AccountingEventListener accountingEventListener){
        if( accountingEventListenersRegistry.containsKey(accountingEvent)) {
            accountingEventListenersRegistry.get(accountingEvent).add(accountingEventListener);
        }
        return null;
    }
    private void registerDefaults(){
        this.getAccountingEventListener(AccountingEvent.CAPITAL_INVESTMENT,capitalInvestmentEventProcessor);
        this.getAccountingEventListener(AccountingEvent.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER,distributionServicesAvailedEventProcessor);
        this.getAccountingEventListener(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS,goodsPurchaseEventProcessor);
        this.getAccountingEventListener(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER,goodsDeliveryToSubscriberEventProcessor);
        this.getAccountingEventListener(AccountingEvent.PAYMENT_MADE_TO_SERVICE_PROVIDER,distributionServicesPaymentEventProcessor);
        this.getAccountingEventListener(AccountingEvent.PAYMENT_MADE_TO_SUPPLIER,goodsPaymentToSupplierEventProcessor);
        this.getAccountingEventListener(AccountingEvent.PAYMENT_RECEIVED_FROM_SUBSCRIBER,paymentReceiptFromSubscriberEventProcessor);
        this.getAccountingEventListener(AccountingEvent.TAX_PAYMENT,taxPaymentEventProcessor);
        this.getAccountingEventListener(AccountingEvent.PAYMENT_OF_RENT,premiseRentPaymentEventProcessor);
        this.getAccountingEventListener(AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER,salesReturnEventProcessor);
        this.getAccountingEventListener(AccountingEvent.PURCHASE_RETURN_BY_BUSINESS,purchaseReturnEventProcessor);
        this.getAccountingEventListener(AccountingEvent.SUPPLIER_PAYMENT_TOWARDS_PURCHASE_RETURN,supplierPaymentTowardsPurchaseReturnEventProcessor);
    }
    public List<AccountingEventListener> getAccountingEventListener(AccountingEvent accountingEvent){
        return this.accountingEventListenersRegistry.get(accountingEvent);
    }
}
