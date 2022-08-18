package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.events.*;
import com.affaince.accounting.journal.processor.factory.AccountIdentificationRulesProcessorFactory;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.journal.subsidiaries.CashBookEntry;
import com.affaince.accounting.journal.subsidiaries.CashBookEntryAccountType;
import com.affaince.accounting.journal.subsidiaries.CreditCashBookEntry;
import com.affaince.accounting.journal.subsidiaries.DebitCashBookEntry;
import com.affaince.accounting.transactions.SourceDocument;

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

    public List<CashBookEntry> processCashBookEntry(String journalFolioNumber, SourceDocument sourceDocument) {
        if (sourceDocument.getModeOfTransaction() == ModeOfTransaction.BY_PAYMENT) {
            TransactionEvents transactionEvent = sourceDocument.getTransactionEvent();
            switch (transactionEvent) {
                case CAPITAL_INVESTMENT:
                case GOODS_DELIVERY_TO_SUBSCRIBER:
                case PAYMENT_RECEIVED_FROM_SUBSCRIBER:
                case PURCHASE_RETURN_BY_BUSINESS:
                    return processReceipts(journalFolioNumber,sourceDocument);
                case SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER:
                case GOODS_PURCHASE_BY_BUSINESS:
                case PAYMENT_MADE_TO_SERVICE_PROVIDER:
                case PAYMENT_MADE_TO_SUPPLIER:
                case TAX_PAYMENT:
                case PAYMENT_OF_RENT:
                case GOODS_RETURN_FROM_SUBSCRIBER:
                    return processPayments(journalFolioNumber,sourceDocument);
            }
        }
        return null;
    }

    public List<CashBookEntry> processReceipts(String journalFolioNumber, SourceDocument sourceDocument){
        AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
        requireNonNull(accountIdentificationRulesProcessor);
        List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
        List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);

        DebitCashBookEntry debitCashBookEntry = new DebitCashBookEntry(sourceDocument.getMerchantId(),
                sourceDocument.getDateOfTransaction(),
                receiverAccounts.get(0).getAccountId(),
                receiverAccounts.get(0).getAccountIdentifier(),
                journalFolioNumber,
                sourceDocument.getTransactionAmount(),
                giverAccounts.get(0).getAccountId(),
                CashBookEntryAccountType.BANK
        );
        List<CashBookEntry> debitCashBookEntries = new ArrayList<>();
        debitCashBookEntries.add(debitCashBookEntry);
        return debitCashBookEntries;
    }
    public List<CashBookEntry> processPayments(String journalFolioNumber, SourceDocument sourceDocument){
        AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
        requireNonNull(accountIdentificationRulesProcessor);
        List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
        List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);

        CreditCashBookEntry creditCashBookEntry = new CreditCashBookEntry(sourceDocument.getMerchantId(),
                sourceDocument.getDateOfTransaction(),
                giverAccounts.get(0).getAccountId(),
                giverAccounts.get(0).getAccountIdentifier(),
                journalFolioNumber,
                sourceDocument.getTransactionAmount(),
                receiverAccounts.get(0).getAccountId(),
                CashBookEntryAccountType.BANK);

        List<CashBookEntry> creditCashBookEntries = new ArrayList<>();
        creditCashBookEntries.add(creditCashBookEntry);
        return creditCashBookEntries;
    }
}
