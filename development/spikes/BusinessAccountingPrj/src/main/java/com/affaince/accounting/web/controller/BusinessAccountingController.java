package com.affaince.accounting.web.controller;

import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.gateway.CommandGateway;
import com.affaince.accounting.journal.qualifiers.AccountingEvent;
import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import com.affaince.accounting.transactions.SourceDocument;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "business/accounting")
public class BusinessAccountingController {
    private final PartyDatabaseSimulator partyDatabaseSimulator;
    private final CommandGateway commandGateway;
    @Autowired
    public BusinessAccountingController(PartyDatabaseSimulator partyDatabaseSimulator,CommandGateway commandGateway) {
        this.partyDatabaseSimulator = partyDatabaseSimulator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/capital")
    public ResponseEntity<Object> investCapital(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("1")
                .transactionAmount(5000000)
                .dateOfTransaction(new LocalDateTime(2023,1,1,0,0,0))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.CAPITAL_INVESTMENT)
                .giverParticipant("merchant1", PartyTypes.MERCHANT, ExchangeableItems.MONEY,5000000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,5000000)
                .description("capital invested")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/purchase/credit")
    public ResponseEntity<Object> receiveStockOfGoodsOnCredit(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("2")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2023,1,10,0,0,0))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased on credit from supplierOfProduct1")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/purchase/payment")
    public ResponseEntity<Object> receiveStockOfGoodsOnPayment(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2023,1,20,0,0,0))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased on payment from supplierOfProduct1")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //return of goods purchase on credit
    @RequestMapping(method = RequestMethod.POST, value = "/purchase/return")
    public ResponseEntity<Object> returnOfGoodsPurchaseOnCredit(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(20000)
                .dateOfTransaction(new LocalDateTime(2023,1,22,0,0,0))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.PURCHASE_RETURN_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,20000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,20000)
                .description("product X purchased from supplierOfProduct1 is returned")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    // supplier payment
    @RequestMapping(method = RequestMethod.POST, value = "/payment/supplier")
    public ResponseEntity<Object> paymentToSupplierInLiuOfGoods(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(80000)
                .dateOfTransaction(new LocalDateTime(2023,1,20,0,0,0))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_MADE_TO_SUPPLIER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,80000)
                .receiverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.MONEY,80000)
                .description("payment to the supplier")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/distribution")
    public ResponseEntity<Object> receiveInvoiceOfDistributionServiceAvailed(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(600)
                .dateOfTransaction(new LocalDateTime(2023,2,4,0,0,0))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER)
                .giverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.SERVICE,600)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,600)
                .description("capital invested")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //payment to distribution supplier
    @RequestMapping(method = RequestMethod.POST, value = "/payment/distribution")
    public ResponseEntity<Object> paymentInLiuOfDistributionService(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("5")
                .transactionAmount(600)
                .dateOfTransaction(new LocalDateTime(2023,2,20,0,0,0))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_MADE_TO_SERVICE_PROVIDER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,600)
                .receiverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.MONEY,600)
                .description("capital invested")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    @RequestMapping(method = RequestMethod.POST, value = "/sales/credit")
    public ResponseEntity<Object> goodsDeliveredToSubscriberOnCredit(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("7")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2023,1,22,0,0,0))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.GOODS,800)
                .receiverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("goods delivery to subscriber on credit")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    @RequestMapping(method = RequestMethod.POST, value = "/sales/payment")
    public ResponseEntity<Object> goodsDeliveredToSubscriberOnPayment(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2023,1,25,0,0,0))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.GOODS,800)
                .receiverParticipant("subscriber2", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("goods delivery to subscriber on payment")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    @RequestMapping(method = RequestMethod.POST, value = "/sales/return")
    public ResponseEntity<Object> goodsReturnedFromSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(200)
                .dateOfTransaction(new LocalDateTime(2023,1,25,0,0,0))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,200)
                .receiverParticipant("merchant1", PartyTypes.BUSINESS, ExchangeableItems.GOODS,200)
                .description("goods returned by subscriber (purchased on credit) ")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //payment received from subscriber.. it may include advance as well as payment for the goods received.
    //as of now lets assume that it makes the business debtor
    @RequestMapping(method = RequestMethod.POST, value = "/payment/subscriber")
    public ResponseEntity<Object> paymentReceivedFromSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("6")
                .transactionAmount(800)
                .dateOfTransaction(new LocalDateTime(2023,1,27,0,0,0))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_RECEIVED_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER,ExchangeableItems.MONEY,800)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,800)
                .description("payment made by subscriber1")
                .build();
        commandGateway.send(sourceDocument);
        return new ResponseEntity<>(HttpStatus.OK);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }


}
