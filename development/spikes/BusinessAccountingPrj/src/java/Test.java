import com.affaince.accounting.journal.entity.SourceDocument;
import com.affaince.accounting.journal.processor.DefaultJournalizingProcessor;
import com.affaince.accounting.journal.processor.JournalizingProcessor;
import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import org.joda.time.LocalDateTime;

public class Test {
    public static void main(String [] args){
        System.out.println("Hello World");
    }

    public void investCapital(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("1")
                .transactionAmount(100000000)
                .dateOfTransaction(new LocalDateTime(2022,1,1,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .natureOfTransaction(TransactionEvents.CAPITAL_INVESTMENT)
                .giverParty("1", PartyTypes.MERCHANT, ExchangeableItems.MONEY,100000000)
                .receiverParty("1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }

    public void receiveStockOfGoods(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("2")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,1,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .natureOfTransaction(TransactionEvents.GOODS_PURCHASE_BY_BUSINESS)
                .giverParty("1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,1000000)
                .receiverParty("1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }

    public void paymentInLiuOfGoods(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("3")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .natureOfTransaction(TransactionEvents.PAYMENT_MADE_TO_SUPPLIER)
                .giverParty("1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.MONEY,1000000)
                .receiverParty("1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }

    public void receiveInvoiceOfDistributionServiceAvailed(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("4")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .natureOfTransaction(TransactionEvents.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER)
                .giverParty("1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.SERVICE,100000)
                .receiverParty("1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }

    public void paymentInLiuOfDistributionService(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("5")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .natureOfTransaction(TransactionEvents.PAYMENT_MADE_TO_SERVICE_PROVIDER)
                .giverParty("1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.MONEY,1000000)
                .receiverParty("1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }
    //?
    public void paymentReceivedFromSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("6")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .natureOfTransaction(TransactionEvents.PAYMENT_RECEIVED_FROM_SUBSCRIBER)
                .giverParty("1", PartyTypes.SUBSCRIBER, ExchangeableItems.MONEY,1000)
                .receiverParty("1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }
    //?
    public void goodsDeliveredToSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("7")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .natureOfTransaction(TransactionEvents.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParty("1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .receiverParty("1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }
}
