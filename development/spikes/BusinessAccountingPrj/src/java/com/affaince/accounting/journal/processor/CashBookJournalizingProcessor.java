package com.affaince.accounting.journal.processor;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.events.*;
import com.affaince.accounting.journal.processor.factory.AccountIdentificationRulesProcessorFactory;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.journal.subsidiaries.AbstractCashBookEntry;
import com.affaince.accounting.journal.subsidiaries.CashBookEntryAccountType;
import com.affaince.accounting.journal.subsidiaries.CreditCashBookEntry;
import com.affaince.accounting.journal.subsidiaries.DebitCashBookEntry;
import com.affaince.accounting.transactions.SourceDocument;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

//At the time of transaction involving immediate payments/receipts
// or transactions corresponding to payments/receipts towards credit transactions
// entry should be made in cash book
//receipts are debited and payments are credited
// in case of receipts discount is allowed
//in case of payments discount is offered
public class CashBookJournalizingProcessor {

    public List<AbstractCashBookEntry> processCashBookEntry(SourceDocument sourceDocument) {
        if (sourceDocument.getModeOfTransaction() == ModeOfTransaction.BY_PAYMENT) {
            AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
            requireNonNull(accountIdentificationRulesProcessor);
            List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
            List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);
            TransactionEvents transactionEvent = sourceDocument.getTransactionEvent();
            switch (transactionEvent) {
                case CAPITAL_INVESTMENT:
                    return processCapitalInvestment(sourceDocument);
                case SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER:
                    return processServiceAvailedOnPayment(sourceDocument);
                case GOODS_PURCHASE_BY_BUSINESS:
                    return processGoodsPurchaseOnPayment(sourceDocument);
                case GOODS_DELIVERY_TO_SUBSCRIBER:
                    return processGoodsDeliveryOnPayment(sourceDocument);
                case PAYMENT_MADE_TO_SERVICE_PROVIDER:
                    return processPaymentToServiceProvider(sourceDocument);
                case PAYMENT_MADE_TO_SUPPLIER:
                    return processPaymentToSupplier(sourceDocument);
                case PAYMENT_RECEIVED_FROM_SUBSCRIBER:
                    return processPaymentReceiptFromSubscriber(sourceDocument);
                case TAX_PAYMENT:
                    return processTaxPayment(sourceDocument);
                case PAYMENT_OF_RENT:
                    return processRentPayment(sourceDocument);
                case GOODS_RETURN_FROM_SUBSCRIBER:
                    return processGoodsReturnBySubscriber(sourceDocument);
                case PURCHASE_RETURN_BY_BUSINESS:
                    return processPurchaseReturnToSupplier(sourceDocument);

            }
        }
        return null;
    }
    // it is a money receipt so should be on the debit side
    private List<AbstractCashBookEntry> processCapitalInvestment(SourceDocument sourceDocument){
        AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
        requireNonNull(accountIdentificationRulesProcessor);
        List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
        List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);

        DebitCashBookEntry debitCashBookEntry = new DebitCashBookEntry(sourceDocument.getMerchantId(),
                sourceDocument.getDateOfTransaction(),
                giverAccounts.get(0).getAccountId(),
                giverAccounts.get(0).getAccountIdentifier(),
                "XX",
                sourceDocument.getTransactionAmount(),
                receiverAccounts.get(0).getAccountId(),
                CashBookEntryAccountType.BANK
                );
        List<AbstractCashBookEntry> debitCashBookEntries = new ArrayList<>();
        debitCashBookEntries.add(debitCashBookEntry);
        return debitCashBookEntries;
    }
    // its a payment entry hence to be made on credit side
    private List<AbstractCashBookEntry> processServiceAvailedOnPayment(SourceDocument sourceDocument){
        AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
        requireNonNull(accountIdentificationRulesProcessor);
        List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
        List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);

        CreditCashBookEntry creditCashBookEntry = new CreditCashBookEntry(sourceDocument.getMerchantId(),
                sourceDocument.getDateOfTransaction(),
                giverAccounts.get(0).getAccountId(),
                giverAccounts.get(0).getAccountIdentifier(),
                "XX",
                sourceDocument.getTransactionAmount(),
                receiverAccounts.get(0).getAccountId(),
                CashBookEntryAccountType.BANK);

        List<AbstractCashBookEntry> creditCashBookEntries = new ArrayList<>();
        creditCashBookEntries.add(creditCashBookEntry);
        return creditCashBookEntries;
    }

    //Its a payment entry hence should be on credit side
    private List<AbstractCashBookEntry> processGoodsPurchaseOnPayment(SourceDocument sourceDocument){
        AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
        requireNonNull(accountIdentificationRulesProcessor);
        List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
        List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);

        CreditCashBookEntry creditCashBookEntry = new CreditCashBookEntry(sourceDocument.getMerchantId(),
                sourceDocument.getDateOfTransaction(),
                giverAccounts.get(0).getAccountId(),
                giverAccounts.get(0).getAccountIdentifier(),
                "XX",
                sourceDocument.getTransactionAmount(),
                receiverAccounts.get(0).getAccountId(),
                CashBookEntryAccountType.BANK);

        List<AbstractCashBookEntry> creditCashBookEntries = new ArrayList<>();
        creditCashBookEntries.add(creditCashBookEntry);
        return creditCashBookEntries;

    }
    private List<AbstractCashBookEntry> processGoodsDeliveryOnPayment(SourceDocument sourceDocument){

    }
    public List<AbstractCashBookEntry> processPaymentToServiceProvider(SourceDocument sourceDocument){

    }
    public List<AbstractCashBookEntry>  processPaymentToSupplier(SourceDocument sourceDocument){

    }
    public List<AbstractCashBookEntry> processPaymentReceiptFromSubscriber(SourceDocument sourceDocument){

    }

    public List<AbstractCashBookEntry> processTaxPayment(SourceDocument sourceDocument){

    }

    public List<AbstractCashBookEntry> processGoodsReturnBySubscriber(SourceDocument sourceDocument){

    }
    public List<AbstractCashBookEntry> processPurchaseReturnToSupplier(SourceDocument sourceDocument){

    }
    public List<AbstractCashBookEntry> processRentPayment(SourceDocument sourceDocument){

    }
}
