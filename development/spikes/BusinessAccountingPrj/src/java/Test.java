import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.entity.JournalEntry;
import com.affaince.accounting.journal.processor.CashBookJournalizingProcessor;
import com.affaince.accounting.journal.processor.SubsidiaryJournalizingProcessor;
import com.affaince.accounting.journal.subsidiaries.CashBookEntry;
import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.processor.DefaultLedgerPostingProcessor;
import com.affaince.accounting.ledger.processor.LedgerPostingProcessor;
import com.affaince.accounting.transactions.SourceDocument;
import com.affaince.accounting.journal.processor.DefaultJournalizingProcessor;
import com.affaince.accounting.journal.processor.JournalizingProcessor;
import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import org.joda.time.LocalDateTime;

import java.util.List;

public class Test {
    public static void main(String [] args){
        PartyDatabaseSimulator.buildDatabase();
        AccountDatabaseSimulator.buildDatabase();
        Test test = new Test();
        test.investCapital();
        test.receiveStockOfGoodsOnCredit();
        test.receiveStockOfGoodsOnPayment();
        test.returnOfGoodsPurchaseOnCredit();
        test.paymentToSupplierInLiuOfGoods();
        test.paymentReturnBySupplierInLiuOfReturnOfGoodsPurchaseO();
        test.goodsDeliveredToSubscriberOnCredit();
        test.goodsDeliveredToSubscriberOnPayment();
        test.goodsReturnedFromSubscriber();
        test.paymentReceivedFromSubscriber();
        test.receiveInvoiceOfDistributionServiceAvailed();
        test.paymentInLiuOfDistributionService();


        System.out.println("###########JOURNAL################");
        test.printJournal();
        System.out.println("###########END - JOURNAL################");
        System.out.println("###########LEDGER################");
        test.printAccounts();
        System.out.println("###########END - LEDGER################");
        System.out.println("###########PURCHASE BOOK################");
        test.printPurchaseBook();
        System.out.println("###########END - PURCHASE BOOK################");
        System.out.println("###########PURCHASE RETURN BOOK################");
        test.printPurchaseReturnBook();
        System.out.println("###########END - PURCHASE RETURN BOOK################");
        System.out.println("###########SALES BOOK################");
        test.printSalesBook();
        System.out.println("###########END - SALES BOOK################");
        System.out.println("###########SALES RETURN BOOK################");
        test.printSalesReturnBook();
        System.out.println("###########END - SALES RETURN BOOK################");
        System.out.println("###########CASH BOOK################");
        test.printCashBook();
        System.out.println("###########END - CASH BOOK################");

    }

    private void processJournalLedgerAndSubsidiaryBooks(SourceDocument sourceDocument){
        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry = journalizingProcessor.processJournalEntry(sourceDocument);
            JournalDatabaseSimulator.addJournalEntry(journalEntry);

            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);

            SubsidiaryJournalizingProcessor subsidiaryJournalizingProcessor = new SubsidiaryJournalizingProcessor();
            List<SubsidiaryBookEntry> subsidiaryBookEntries =  subsidiaryJournalizingProcessor.processJournalEntry(sourceDocument);
            if(null != subsidiaryBookEntries && sourceDocument.getTransactionEvent()== TransactionEvents.GOODS_PURCHASE_BY_BUSINESS ){
                PurchaseBookDatabaseSimulator.addJournalEntries(subsidiaryBookEntries);
            }else if(null != subsidiaryBookEntries && sourceDocument.getTransactionEvent()== TransactionEvents.GOODS_DELIVERY_TO_SUBSCRIBER){
                SalesBookDatabaseSimulator.addJournalEntries(subsidiaryBookEntries);
            }else if(null != subsidiaryBookEntries && sourceDocument.getTransactionEvent() == TransactionEvents.PURCHASE_RETURN_BY_BUSINESS){
                PurchaseReturnBookDatabaseSimulator.addJournalEntries(subsidiaryBookEntries);
            } else if(null != subsidiaryBookEntries && sourceDocument.getTransactionEvent() == TransactionEvents.GOODS_RETURN_FROM_SUBSCRIBER){
                SalesReturnBookDatabaseSimulator.addJournalEntries(subsidiaryBookEntries);
            }

            CashBookJournalizingProcessor cashBookJournalizingProcessor = new CashBookJournalizingProcessor();
            List<CashBookEntry> cashBookEntries = cashBookJournalizingProcessor.processCashBookEntry(journalEntry.getJournalFolioNumber(),sourceDocument);
            if(null != cashBookEntries && !cashBookEntries.isEmpty()){
                CashBookDatabaseSimulator.addJournalEntries(cashBookEntries);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    //capital investment
    public void investCapital(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("1")
                .transactionAmount(10000000)
                .dateOfTransaction(new LocalDateTime(2023,1,1,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.CAPITAL_INVESTMENT)
                .giverParticipant("merchant1", PartyTypes.MERCHANT, ExchangeableItems.MONEY,10000000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,10000000)
                .description("capital invested")
                .build();
        processJournalLedgerAndSubsidiaryBooks(sourceDocument);

    }
    //stock purchase.. by default on credit
    public void receiveStockOfGoodsOnCredit(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("2")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2023,1,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased on credit from supplierOfProduct1")
                .build();
        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //stock purchase.. on payment
    public void receiveStockOfGoodsOnPayment(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2023,1,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased on payment from supplierOfProduct1")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //return of goods purchase on credit
    public void returnOfGoodsPurchaseOnCredit(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(20000)
                .dateOfTransaction(new LocalDateTime(2023,1,22,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.PURCHASE_RETURN_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased from supplierOfProduct1 is returned")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    // supplier payment
    public void paymentToSupplierInLiuOfGoods(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(80000)
                .dateOfTransaction(new LocalDateTime(2022,1,30,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_MADE_TO_SUPPLIER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,80000)
                .receiverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.MONEY,80000)
                .description("payment to the supplier")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //return of goods purchase on credit
    public void paymentReturnBySupplierInLiuOfReturnOfGoodsPurchaseO(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(20000)
                .dateOfTransaction(new LocalDateTime(2023,2,2,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PURCHASE_RETURN_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased from supplierOfProduct1 is returned")
                .build();
        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //distribution services availed.. on credit by default
    //beneficiary is business,giver is service provider
    public void receiveInvoiceOfDistributionServiceAvailed(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2022,2,4,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER)
                .giverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.SERVICE,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("capital invested")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //payment to distribution supplier
    public void paymentInLiuOfDistributionService(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("5")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,2,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_MADE_TO_SERVICE_PROVIDER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .receiverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.MONEY,1000000)
                .description("capital invested")
                .build();
        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsDeliveredToSubscriberOnCredit(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("7")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,1,22,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .receiverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("goods delivery to subscriber on credit")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsDeliveredToSubscriberOnPayment(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,1,25,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .receiverParticipant("subscriber2", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("goods delivery to subscriber on payment")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsReturnedFromSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,1,22,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.GOODS_RETURN_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .receiverParticipant("merchant1", PartyTypes.BUSINESS, ExchangeableItems.GOODS,1000)
                .description("goods returned by subscriber (purchased on credit) ")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //payment received from subscriber.. it may include advance as well as payment for the goods received.
    //as of now lets assume that it makes the business debtor
    public void paymentReceivedFromSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("6")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,1,27,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_RECEIVED_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER,ExchangeableItems.MONEY,1000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .description("payment made by subscriber1")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    public void printJournal(){
        List<JournalEntry> journalEntries = JournalDatabaseSimulator.getJournalEntries();
        for(JournalEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printPurchaseBook(){
        List<SubsidiaryBookEntry> journalEntries = PurchaseBookDatabaseSimulator.getPurchaseBookJournalEntries();
        for(SubsidiaryBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printPurchaseReturnBook(){
        List<SubsidiaryBookEntry> journalEntries = PurchaseReturnBookDatabaseSimulator.getPurchaseReturnBookJournalEntries();
        for(SubsidiaryBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printSalesBook(){
        List<SubsidiaryBookEntry> journalEntries = SalesBookDatabaseSimulator.getSalesBookJournalEntries();
        for(SubsidiaryBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printSalesReturnBook(){
        List<SubsidiaryBookEntry> journalEntries = SalesReturnBookDatabaseSimulator.getSalesReturnBookJournalEntries();
        for(SubsidiaryBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printCashBook(){
        List<CashBookEntry> journalEntries = CashBookDatabaseSimulator.getCashBookEntries();
        for(CashBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printAccounts(){
        List<LedgerAccount> allAccounts= AccountDatabaseSimulator.getAllAccounts();
        for(LedgerAccount account : allAccounts){
            System.out.println(account);
        }
    }


}
