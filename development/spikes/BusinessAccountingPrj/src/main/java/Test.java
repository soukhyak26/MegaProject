import com.affaince.accounting.db.*;
import com.affaince.accounting.endofperiod.PeriodReconciliationProcessor;
import com.affaince.accounting.journal.gateway.CommandGateway;
import com.affaince.accounting.journal.qualifiers.*;
import com.affaince.accounting.trading.TradingFrequency;
import com.affaince.accounting.transactions.SourceDocument;
import org.joda.time.LocalDateTime;

public class Test {
/*
    public static void main(String [] args){
        PartyDatabaseSimulator.buildDatabase();
        //AccountDatabaseSimulator.buildDatabase(new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(9999,12,31,23,59,59));

        Test test = new Test();
        CommandGateway commandGateway = new CommandGateway();
        PeriodReconciliationProcessor periodReconciliationProcessor = new PeriodReconciliationProcessor();

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(2023,1,1,23,59,59),TradingFrequency.DAILY);
        test.investCapital(commandGateway);   //does not impact trading acct --1 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(2023,1,1,23,59,59),TradingFrequency.DAILY);


        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,10,0,0,0),new LocalDateTime(2023,1,10,23,59,59),TradingFrequency.DAILY);
        test.receiveStockOfGoodsOnCredit(commandGateway); // debit trading account 10 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,10,0,0,0),new LocalDateTime(2023,1,10,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,20,0,0,0),new LocalDateTime(2023,1,20,23,59,59),TradingFrequency.DAILY);
        test.receiveStockOfGoodsOnPayment(commandGateway);    // debit trading account 20 Jan 2023
        test.paymentToSupplierInLiuOfGoods(commandGateway);   // no impact ton trading acct 20 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,20,0,0,0),new LocalDateTime(2023,1,20,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,22,0,0,0),new LocalDateTime(2023,1,22,23,59,59),TradingFrequency.DAILY);
        test.returnOfGoodsPurchaseOnCredit(commandGateway);   // credit trading account 22 Jan 2023
        test.goodsDeliveredToSubscriberOnCredit(commandGateway);  //impact on trading account 22 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,22,0,0,0),new LocalDateTime(2023,1,22,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,25,0,0,0),new LocalDateTime(2023,1,25,23,59,59),TradingFrequency.DAILY);
        test.goodsDeliveredToSubscriberOnPayment(commandGateway); //impact on trading account 25 Jan 2023
        test.goodsReturnedFromSubscriber(commandGateway); //impact on trading account 25 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,25,0,0,0),new LocalDateTime(2023,1,25,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,27,0,0,0),new LocalDateTime(2023,1,27,23,59,59),TradingFrequency.DAILY);
        test.paymentReceivedFromSubscriber(commandGateway);// no impact on trading account 27 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,27,0,0,0),new LocalDateTime(2023,1,27,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,2,4,0,0,0),new LocalDateTime(2023,2,4,23,59,59),TradingFrequency.DAILY);
        test.receiveInvoiceOfDistributionServiceAvailed(commandGateway); // impact on trading account 4 Feb 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,2,4,0,0,0),new LocalDateTime(2023,2,4,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,2,20,0,0,0),new LocalDateTime(2023,2,20,23,59,59),TradingFrequency.DAILY);
        test.paymentInLiuOfDistributionService(commandGateway);// no impact on trading account.20 Feb 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,2,20,0,0,0),new LocalDateTime(2023,2,20,23,59,59),TradingFrequency.DAILY);

    }


*/

    //capital investment
    public void investCapital(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("1")
                .transactionAmount(5000000)
                .dateOfTransaction(new LocalDateTime(2023,1,1,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.CAPITAL_INVESTMENT)
                .giverParticipant("merchant1", PartyTypes.MERCHANT, ExchangeableItems.MONEY,5000000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,5000000)
                .description("capital invested")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);

    }
    //stock purchase.. by default on credit
    public void receiveStockOfGoodsOnCredit(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("2")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2023,1,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased on credit from supplierOfProduct1")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //stock purchase.. on payment
    public void receiveStockOfGoodsOnPayment(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2023,1,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased on payment from supplierOfProduct1")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //return of goods purchase on credit
    public void returnOfGoodsPurchaseOnCredit(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(20000)
                .dateOfTransaction(new LocalDateTime(2023,1,22,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.PURCHASE_RETURN_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,20000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,20000)
                .description("product X purchased from supplierOfProduct1 is returned")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    // supplier payment
    public void paymentToSupplierInLiuOfGoods(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(80000)
                .dateOfTransaction(new LocalDateTime(2023,1,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_MADE_TO_SUPPLIER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,80000)
                .receiverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.MONEY,80000)
                .description("payment to the supplier")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

/*
    //return of goods purchase on credit
    public void paymentReturnBySupplierInLiuOfReturnOfGoodsPurchaseO(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(20000)
                .dateOfTransaction(new LocalDateTime(2023,2,2,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PURCHASE_RETURN_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,20000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,20000)
                .description("product X purchased from supplierOfProduct1 is returned.Supplier has returned amount of returned goods")
                .build();
        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
*/

    //distribution services availed.. on credit by default
    //beneficiary is business,giver is service provider
    public void receiveInvoiceOfDistributionServiceAvailed(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(600)
                .dateOfTransaction(new LocalDateTime(2023,2,4,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER)
                .giverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.SERVICE,600)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,600)
                .description("capital invested")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //payment to distribution supplier
    public void paymentInLiuOfDistributionService(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("5")
                .transactionAmount(600)
                .dateOfTransaction(new LocalDateTime(2023,2,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_MADE_TO_SERVICE_PROVIDER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,600)
                .receiverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.MONEY,600)
                .description("capital invested")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsDeliveredToSubscriberOnCredit(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("7")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2023,1,22,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.GOODS,800)
                .receiverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("goods delivery to subscriber on credit")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsDeliveredToSubscriberOnPayment(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2023,1,25,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.GOODS,800)
                .receiverParticipant("subscriber2", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("goods delivery to subscriber on payment")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsReturnedFromSubscriber(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(200)
                .dateOfTransaction(new LocalDateTime(2023,1,25,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,200)
                .receiverParticipant("merchant1", PartyTypes.BUSINESS, ExchangeableItems.GOODS,200)
                .description("goods returned by subscriber (purchased on credit) ")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //payment received from subscriber.. it may include advance as well as payment for the goods received.
    //as of now lets assume that it makes the business debtor
    public void paymentReceivedFromSubscriber(CommandGateway commandGateway){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("6")
                .transactionAmount(800)
                .dateOfTransaction(new LocalDateTime(2023,1,27,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_RECEIVED_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER,ExchangeableItems.MONEY,800)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,800)
                .description("payment made by subscriber1")
                .build();
        commandGateway.send(sourceDocument);
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }


}
