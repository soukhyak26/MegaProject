import com.affaince.accounting.db.*;
import com.affaince.accounting.endofperiod.PeriodReconciliationProcessor;
import com.affaince.accounting.journal.entity.JournalEntry;
import com.affaince.accounting.journal.processor.CashBookJournalizingProcessor;
import com.affaince.accounting.journal.processor.SubsidiaryJournalizingProcessor;
import com.affaince.accounting.journal.qualifiers.*;
import com.affaince.accounting.journal.subsidiaries.CashBookEntry;
import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.processor.DefaultLedgerPostingProcessor;
import com.affaince.accounting.ledger.processor.LedgerPostingProcessor;
import com.affaince.accounting.trading.DefaultTradingAccountPostingProcessor;
import com.affaince.accounting.trading.TradingAccount;
import com.affaince.accounting.trading.TradingAccountPostingProcessor;
import com.affaince.accounting.trading.TradingFrequency;
import com.affaince.accounting.transactions.SourceDocument;
import com.affaince.accounting.journal.processor.DefaultJournalizingProcessor;
import com.affaince.accounting.journal.processor.JournalizingProcessor;
import com.affaince.accounting.trials.DefaultTrialBalanceProcessor2;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceProcessor;
import org.joda.time.LocalDateTime;
import java.util.List;

public class Test {
    public static void main(String [] args){
        PartyDatabaseSimulator.buildDatabase();
        AccountDatabaseSimulator.buildDatabase(new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(9999,12,31,23,59,59));

        Test test = new Test();
        PeriodReconciliationProcessor periodReconciliationProcessor = new PeriodReconciliationProcessor();

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(2023,1,1,23,59,59),TradingFrequency.DAILY);
        test.investCapital();   //does not impact trading acct --1 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(2023,1,1,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,10,0,0,0),new LocalDateTime(2023,1,10,23,59,59),TradingFrequency.DAILY);
        test.receiveStockOfGoodsOnCredit(); // debit trading account 10 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,10,0,0,0),new LocalDateTime(2023,1,10,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,20,0,0,0),new LocalDateTime(2023,1,20,23,59,59),TradingFrequency.DAILY);
        test.receiveStockOfGoodsOnPayment();    // debit trading account 20 Jan 2023
        test.paymentToSupplierInLiuOfGoods();   // no impact ton trading acct 20 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,20,0,0,0),new LocalDateTime(2023,1,20,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,22,0,0,0),new LocalDateTime(2023,1,22,23,59,59),TradingFrequency.DAILY);
        test.returnOfGoodsPurchaseOnCredit();   // credit trading account 22 Jan 2023
        test.goodsDeliveredToSubscriberOnCredit();  //impact on trading account 22 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,22,0,0,0),new LocalDateTime(2023,1,22,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,25,0,0,0),new LocalDateTime(2023,1,25,23,59,59),TradingFrequency.DAILY);
        test.goodsDeliveredToSubscriberOnPayment(); //impact on trading account 25 Jan 2023
        test.goodsReturnedFromSubscriber(); //impact on trading account 25 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,25,0,0,0),new LocalDateTime(2023,1,25,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,27,0,0,0),new LocalDateTime(2023,1,27,23,59,59),TradingFrequency.DAILY);
        test.paymentReceivedFromSubscriber();// no impact on trading account 27 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,27,0,0,0),new LocalDateTime(2023,1,27,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,2,4,0,0,0),new LocalDateTime(2023,2,4,23,59,59),TradingFrequency.DAILY);
        test.receiveInvoiceOfDistributionServiceAvailed(); // impact on trading account 4 Feb 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,2,4,0,0,0),new LocalDateTime(2023,2,4,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,2,20,0,0,0),new LocalDateTime(2023,2,20,23,59,59),TradingFrequency.DAILY);
        test.paymentInLiuOfDistributionService();// no impact on trading account.20 Feb 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,2,20,0,0,0),new LocalDateTime(2023,2,20,23,59,59),TradingFrequency.DAILY);

/*
        System.out.println("###########LEDGER################");
        test.printAccounts("merchant1");
        System.out.println("###########END - LEDGER################");
*/


  /*      TrialBalance trialBalance = test.processTrialBalance("merchant1",new LocalDateTime(2023,2,21,23,59,59));

        System.out.println("trial Balance :::############");
        System.out.println(trialBalance);
        System.out.println("trial balance :: ############");

        test.processTradingAccount("merchant1",new LocalDateTime(2023,2,21,0,0,0),new LocalDateTime(2023,2,21,23,59,59));
*/
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

    public TrialBalance processTrialBalance(String merchantId, LocalDateTime trialBalanceDate){
        //TrialBalanceProcessor trialBalanceProcessor = new DefaultTrialBalanceProcessor();
        TrialBalanceProcessor trialBalanceProcessor = new DefaultTrialBalanceProcessor2();
        TrialBalance trialBalance= trialBalanceProcessor.processTrialBalance(merchantId,trialBalanceDate.toLocalDate());
        System.out.println(" is trial balance tallied? :: " + trialBalance.isTrialBalanceTallied());
        TrialBalanceDatabaseSimulator.addTrialBalance(trialBalance);
        return trialBalance;
    }

    public TradingAccount processTradingAccount(String merchantId,LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod){
        TradingAccountPostingProcessor tradingAccountPostingProcessor = new DefaultTradingAccountPostingProcessor();
        TradingAccount tradingAccount = tradingAccountPostingProcessor.postToTradingAccount(merchantId, startDateOfPeriod, closureDateOfPeriod, TradingFrequency.DAILY);
        System.out.println("Trading Account{}} " + tradingAccount);
        return tradingAccount;
    }
    //capital investment
    public void investCapital(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("1")
                .transactionAmount(5000000)
                .dateOfTransaction(new LocalDateTime(2023,1,1,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.CAPITAL_INVESTMENT)
                .giverParticipant("merchant1", PartyTypes.MERCHANT, ExchangeableItems.MONEY,5000000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,5000000)
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
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,20000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,20000)
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
    public void receiveInvoiceOfDistributionServiceAvailed(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(600)
                .dateOfTransaction(new LocalDateTime(2022,2,4,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER)
                .giverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.SERVICE,600)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,600)
                .description("capital invested")
                .build();

        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //payment to distribution supplier
    public void paymentInLiuOfDistributionService(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("5")
                .transactionAmount(600)
                .dateOfTransaction(new LocalDateTime(2022,2,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_MADE_TO_SERVICE_PROVIDER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,600)
                .receiverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.MONEY,600)
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
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.GOODS,800)
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
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.GOODS,800)
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
                .transactionAmount(200)
                .dateOfTransaction(new LocalDateTime(2022,1,25,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(TransactionEvents.GOODS_RETURN_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,200)
                .receiverParticipant("merchant1", PartyTypes.BUSINESS, ExchangeableItems.GOODS,200)
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
                .transactionAmount(800)
                .dateOfTransaction(new LocalDateTime(2022,1,27,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PAYMENT_RECEIVED_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER,ExchangeableItems.MONEY,800)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,800)
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
    public void printAccounts(String merchantId){
        List<LedgerAccount> allAccounts= AccountDatabaseSimulator.getAllAccounts(merchantId);
        for(LedgerAccount account : allAccounts){
            if( (null != account.getDebits() && account.getDebits().size()>0)  || (null !=account.getCredits() && account.getCredits().size()>0) ) {
                System.out.println(account);
            }
        }
    }


}
