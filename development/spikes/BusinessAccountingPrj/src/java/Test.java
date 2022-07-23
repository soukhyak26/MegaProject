import com.affaince.accounting.journal.entity.SourceDocument;
import com.affaince.accounting.journal.processor.DefaultJournalizingProcessor;
import com.affaince.accounting.journal.processor.contract.JournalizingProcessor;
import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import org.joda.time.LocalDateTime;

public class Test {
    public static void main(String [] args){
        System.out.println("Hello World");
    }

    //capital investment
    public void investCapital(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("1")
                .transactionAmount(100000000)
                .dateOfTransaction(new LocalDateTime(2022,1,1,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.CAPITAL_INVESTMENT)
                .giverParticipant("1", PartyTypes.MERCHANT, ExchangeableItems.MONEY,100000000)
                .receiverParticipant(null,PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }
    //stock purchase.. by default on credit
    public void receiveStockOfGoods(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("2")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,1,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,1000000)
                .receiverParticipant(null,PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }

    // supplier payment
    public void paymentInLiuOfGoods(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("3")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_MADE_TO_SUPPLIER)
                .giverParticipant(null,PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .receiverParticipant("1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.MONEY,1000000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }

    // distribution services availed.. on credit by default
    public void receiveInvoiceOfDistributionServiceAvailed(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("4")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER)
                .giverParticipant("1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.SERVICE,100000)
                .receiverParticipant(null,PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }

    //payment to distribution supplier
    public void paymentInLiuOfDistributionService(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("5")
                .transactionAmount(1000000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_MADE_TO_SERVICE_PROVIDER)
                .giverParticipant(null,PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000000)
                .receiverParticipant("1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.MONEY,1000000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }
    //payment received from subscriber.. it may include advance as well as payment for the goods received.
    //as of now lets assume that it makes the business debtor
    public void paymentReceivedFromSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("6")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_RECEIVED_FROM_SUBSCRIBER)
                .giverParticipant("1", PartyTypes.SUBSCRIBER,ExchangeableItems.MONEY,1000)
                .receiverParticipant(null,PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }
    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsDeliveredToSubscriber(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .transactionReferenceNumber("7")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2022,2,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant(null,PartyTypes.BUSINESS,ExchangeableItems.MONEY,1000)
                .receiverParticipant("1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .build();

        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        journalizingProcessor.processJournalEntry(sourceDocument);
    }
}
