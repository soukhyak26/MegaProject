package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.events.*;
import com.affaince.accounting.journal.processor.factory.AccountingEventsRegistry;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.AccountingEvent;
import com.affaince.accounting.journal.subsidiaries.CashBookEntry;
import com.affaince.accounting.journal.subsidiaries.CashBookEntryAccountType;
import com.affaince.accounting.journal.subsidiaries.CreditCashBookEntry;
import com.affaince.accounting.journal.subsidiaries.DebitCashBookEntry;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.ArrayList;
import java.util.List;


//At the time of transaction involving immediate payments/receipts
// or transactions corresponding to payments/receipts towards credit transactions
// entry should be made in cash book
//receipts are debited and payments are credited
// in case of receipts discount is allowed
//in case of payments discount is offered
public class CashBookJournalizingProcessor {

    public List<CashBookEntry> processCashBookEntry(String journalFolioNumber, SourceDocument sourceDocument) {
        if (sourceDocument.getModeOfTransaction() == ModeOfTransaction.BY_PAYMENT) {
            AccountingEvent transactionEvent = sourceDocument.getAccountingEvent();
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
        List<AccountingEventListener> accountingEventListeners = AccountingEventsRegistry.getInstance().getAccountingEventListener(sourceDocument.getAccountingEvent());
        List<CashBookEntry> debitCashBookEntries = new ArrayList<>();
        for(AccountingEventListener listener: accountingEventListeners ) {
            List<ParticipantAccount> giverAccounts = listener.identifyParticipatingGiverAccounts(sourceDocument);
            List<ParticipantAccount> receiverAccounts = listener.identifyParticipatingReceiverAccounts(sourceDocument);
            DebitCashBookEntry debitCashBookEntry = new DebitCashBookEntry(sourceDocument.getMerchantId(),
                    sourceDocument.getDateOfTransaction(),
                    giverAccounts.get(0).getAccountId(),
                    giverAccounts.get(0).getAccountIdentifier(),
                    journalFolioNumber,
                    sourceDocument.getTransactionAmount(),
                    receiverAccounts.get(0).getAccountId(),
                    CashBookEntryAccountType.BANK
            );
            debitCashBookEntries.add(debitCashBookEntry);
        }
        return debitCashBookEntries;
    }
    public List<CashBookEntry> processPayments(String journalFolioNumber, SourceDocument sourceDocument){
        List<AccountingEventListener> accountingEventListeners = AccountingEventsRegistry.getInstance().getAccountingEventListener(sourceDocument.getAccountingEvent());
        List<CashBookEntry> creditCashBookEntries = new ArrayList<>();
        for(AccountingEventListener listener: accountingEventListeners) {
            List<ParticipantAccount> giverAccounts = listener.identifyParticipatingGiverAccounts(sourceDocument);
            List<ParticipantAccount> receiverAccounts = listener.identifyParticipatingReceiverAccounts(sourceDocument);
            CreditCashBookEntry creditCashBookEntry = new CreditCashBookEntry(sourceDocument.getMerchantId(),
                    sourceDocument.getDateOfTransaction(),
                    receiverAccounts.get(0).getAccountId(),
                    receiverAccounts.get(0).getAccountIdentifier(),
                    journalFolioNumber,
                    sourceDocument.getTransactionAmount(),
                    giverAccounts.get(0).getAccountId(),
                    CashBookEntryAccountType.BANK);
            creditCashBookEntries.add(creditCashBookEntry);
        }
        return creditCashBookEntries;
    }
}
