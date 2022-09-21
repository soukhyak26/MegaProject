package com.affaince.accounting.web.controller;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.JournalDatabaseSimulator;
import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.Journal;
import com.affaince.accounting.journal.entity.JournalRecord;
import com.affaince.accounting.journal.events.*;
import com.affaince.accounting.journal.gateway.AccountingCommandGateway;
import com.affaince.accounting.journal.processor.factory.AccountingEventsRegistry;
import com.affaince.accounting.journal.qualifiers.AccountingEvent;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.reconcile.PeriodReconciliationProcessor;
import com.affaince.accounting.web.request.AccountingTransactionRequest;
import com.affaince.accounting.web.request.FinancialPeriodRequest;
import com.affaince.accounting.web.request.PrintAccountsRequest;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "business/accounting")
public class BusinessAccountingController {
    private final PartyDatabaseSimulator partyDatabaseSimulator;
    private final AccountingCommandGateway accountingCommandGateway;
    private final AccountingEventsRegistry accountingEventsRegistry;
    private final SourceDocumentTransformer sourceDocumentTransformer;
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    private final PeriodReconciliationProcessor periodReconciliationProcessor;
    private final JournalDatabaseSimulator journalDatabaseSimulator;
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
    public BusinessAccountingController(PartyDatabaseSimulator partyDatabaseSimulator,
                                        AccountingCommandGateway accountingCommandGateway,
                                        AccountingEventsRegistry accountingEventsRegistry,
                                        SourceDocumentTransformer sourceDocumentTransformer,
                                        AccountDatabaseSimulator accountDatabaseSimulator,
                                        PeriodReconciliationProcessor periodReconciliationProcessor,
                                        JournalDatabaseSimulator journalDatabaseSimulator,
                                        CapitalInvestmentEventProcessor capitalInvestmentEventProcessor,
                                        DistributionServicesAvailedEventProcessor distributionServicesAvailedEventProcessor,
                                        GoodsPurchaseEventProcessor goodsPurchaseEventProcessor,
                                        GoodsDeliveryToSubscriberEventProcessor goodsDeliveryToSubscriberEventProcessor,
                                        DistributionServicesPaymentEventProcessor distributionServicesPaymentEventProcessor,
                                        GoodsPaymentToSupplierEventProcessor goodsPaymentToSupplierEventProcessor,
                                        PaymentReceiptFromSubscriberEventProcessor paymentReceiptFromSubscriberEventProcessor,
                                        TaxPaymentEventProcessor taxPaymentEventProcessor,
                                        PremiseRentPaymentEventProcessor premiseRentPaymentEventProcessor,
                                        SalesReturnEventProcessor salesReturnEventProcessor,
                                        PurchaseReturnEventProcessor purchaseReturnEventProcessor,
                                        SupplierPaymentTowardsPurchaseReturnEventProcessor supplierPaymentTowardsPurchaseReturnEventProcessor) {
        this.partyDatabaseSimulator = partyDatabaseSimulator;
        this.accountingCommandGateway = accountingCommandGateway;
        this.accountingEventsRegistry = accountingEventsRegistry;
        this.sourceDocumentTransformer = sourceDocumentTransformer;
        this.accountDatabaseSimulator = accountDatabaseSimulator;
        this.periodReconciliationProcessor = periodReconciliationProcessor;
        this.journalDatabaseSimulator = journalDatabaseSimulator;
        this.capitalInvestmentEventProcessor = capitalInvestmentEventProcessor;
        this.distributionServicesAvailedEventProcessor= distributionServicesAvailedEventProcessor;
        this.goodsPurchaseEventProcessor = goodsPurchaseEventProcessor;
        this. goodsDeliveryToSubscriberEventProcessor= goodsDeliveryToSubscriberEventProcessor;
        this.distributionServicesPaymentEventProcessor= distributionServicesPaymentEventProcessor;
        this.goodsPaymentToSupplierEventProcessor = goodsPaymentToSupplierEventProcessor;
        this.paymentReceiptFromSubscriberEventProcessor = paymentReceiptFromSubscriberEventProcessor;
        this.taxPaymentEventProcessor = taxPaymentEventProcessor;
        this.premiseRentPaymentEventProcessor = premiseRentPaymentEventProcessor;
        this.salesReturnEventProcessor = salesReturnEventProcessor ;
        this.purchaseReturnEventProcessor = purchaseReturnEventProcessor ;
        this.supplierPaymentTowardsPurchaseReturnEventProcessor = supplierPaymentTowardsPurchaseReturnEventProcessor ;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/listeners")
    public ResponseEntity<Object> registerDefaultEventListeners(){
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.CAPITAL_INVESTMENT,capitalInvestmentEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER,distributionServicesAvailedEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS,goodsPurchaseEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER,goodsDeliveryToSubscriberEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.PAYMENT_MADE_TO_SERVICE_PROVIDER,distributionServicesPaymentEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.PAYMENT_MADE_TO_SUPPLIER,goodsPaymentToSupplierEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.PAYMENT_RECEIVED_FROM_SUBSCRIBER,paymentReceiptFromSubscriberEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.TAX_PAYMENT,taxPaymentEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.PAYMENT_OF_RENT,premiseRentPaymentEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER,salesReturnEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.PURCHASE_RETURN_BY_BUSINESS,purchaseReturnEventProcessor);
        accountingEventsRegistry.registerAccountingEventListener(AccountingEvent.SUPPLIER_PAYMENT_TOWARDS_PURCHASE_RETURN,supplierPaymentTowardsPurchaseReturnEventProcessor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/period/start")
    public ResponseEntity<Object> startFinancialPeriod(FinancialPeriodRequest request) {
        periodReconciliationProcessor.processStartOfPeriodOperations(request.getMerchant(),request.getStartDate(),request.getClosureDate(),request.getAccountingPeriod());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/event")
    public ResponseEntity<Object> accountingEvent(AccountingTransactionRequest request) {
        accountingCommandGateway.send(sourceDocumentTransformer.transform(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/period/end")
    public ResponseEntity<Object> endFinancialPeriod(FinancialPeriodRequest request) {
        periodReconciliationProcessor.processEndOfPeriodOperations(request.getMerchant(),request.getStartDate(),request.getClosureDate(),request.getAccountingPeriod());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/print")
    public ResponseEntity<Object> printAccounts(PrintAccountsRequest request) {
        System.out.println("###########LEDGER################");
        printAccounts(request.getMerchantId(), request.getStartDate(), request.getEndDate());
        System.out.println("###########END - LEDGER################");
        System.out.println();
        System.out.println();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void printAccounts(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
        List<LedgerAccount> allAccounts = accountDatabaseSimulator.getAllActiveAccounts(merchantId, startDate, closureDate);
        for (LedgerAccount account : allAccounts) {
            if ((null != account.getDebits() && account.getDebits().size() > 0) || (null != account.getCredits() && account.getCredits().size() > 0)) {
                System.out.println(account);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/journal/print")
    public ResponseEntity<Object> printJournal(PrintAccountsRequest request) {
        Journal journal  = journalDatabaseSimulator.searchBYMerchantIdAndPeriod(request.getMerchantId(),request.getStartDate(),request.getEndDate());
        for(JournalRecord journalRecord : journal.getJournalEntries() ){
            System.out.println(journalRecord);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
