import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.JournalEntry;
import com.affaince.accounting.ledger.accounts.types.LedgerAccount;
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
        System.out.println("Hello World");
        PartyDatabaseSimulator.buildDatabase();
        AccountDatabaseSimulator.buildDatabase();
        Test test = new Test();
        System.out.println("Invest Capital");
        test.investCapital();
        System.out.println(" receive stock of goods");
        test.receiveStockOfGoods();
        System.out.println(" payment in liu of goods");
        test.paymentInLiuOfGoods();
        System.out.println(" receive invoice of distribution services");
        test.receiveInvoiceOfDistributionServiceAvailed();
        System.out.println(" payment in liu of distribution services");
        test.paymentInLiuOfDistributionService();
        System.out.println(" goods delivered to subscriber");
        test.goodsDeliveredToSubscriber();
        System.out.println(" payment received from subscriber");
        test.paymentReceivedFromSubscriber();
        System.out.println(" goods returned by subscriber");
        test.goodsReturnedFromSubscriber();

        test.printAccounts();

    }

    //capital investment
    public void investCapital(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("1")
                .transactionAmount(100000000)
                .dateOfTransaction(new LocalDateTime(2022,1,1,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.CAPITAL_INVESTMENT)
                .giverParticipant("merchant1", PartyTypes.MERCHANT, ExchangeableItems.MONEY,100000000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000000)
                .description("capital invested")
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry = journalizingProcessor.processJournalEntry(sourceDocument);
            System.out.println(journalEntry);

            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    //stock purchase.. by default on credit
    public void receiveStockOfGoods(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("2")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,1,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,1000000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .description("capital invested")
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry = journalizingProcessor.processJournalEntry(sourceDocument);
            System.out.println(journalEntry);
            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // supplier payment
    public void paymentInLiuOfGoods(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_MADE_TO_SUPPLIER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .receiverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.MONEY,1000000)
                .description("capital invested")
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry = journalizingProcessor.processJournalEntry(sourceDocument);
            System.out.println(journalEntry);
            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // distribution services availed.. on credit by default
    public void receiveInvoiceOfDistributionServiceAvailed(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER)
                .giverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.SERVICE,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("capital invested")
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry = journalizingProcessor.processJournalEntry(sourceDocument);
            System.out.println(journalEntry);
            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    //payment to distribution supplier
    public void paymentInLiuOfDistributionService(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("5")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_MADE_TO_SERVICE_PROVIDER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .receiverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.MONEY,1000000)
                .description("capital invested")
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry = journalizingProcessor.processJournalEntry(sourceDocument);
            System.out.println(journalEntry);
            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    //payment received from subscriber.. it may include advance as well as payment for the goods received.
    //as of now lets assume that it makes the business debtor
    public void paymentReceivedFromSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("6")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_RECEIVED_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER,ExchangeableItems.MONEY,1000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .description("capital invested")
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry = journalizingProcessor.processJournalEntry(sourceDocument);
            System.out.println(journalEntry);
            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsDeliveredToSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("7")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .receiverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("capital invested")
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry =journalizingProcessor.processJournalEntry(sourceDocument);
            System.out.println(journalEntry);
            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsReturnedFromSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.GOODS_RETURN_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .receiverParticipant("merchant1", PartyTypes.BUSINESS, ExchangeableItems.GOODS,1000)
                .description("capital invested")
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        try {
            JournalEntry journalEntry = journalizingProcessor.processJournalEntry(sourceDocument);
            System.out.println(journalEntry);
            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalEntry);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void printAccounts(){
        List<LedgerAccount> allAccounts= AccountDatabaseSimulator.getAllAccounts();
        for(LedgerAccount account : allAccounts){
            System.out.println(account);
        }
    }
}
