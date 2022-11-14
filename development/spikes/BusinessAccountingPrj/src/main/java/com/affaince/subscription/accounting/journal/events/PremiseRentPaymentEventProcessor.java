package com.affaince.subscription.accounting.journal.events;

import com.affaince.accounting.db.*;
import com.affaince.subscription.accounting.db.*;
import com.affaince.subscription.accounting.journal.entity.ParticipantAccount;
import com.affaince.subscription.accounting.journal.processor.CashBookJournalizingProcessor;
import com.affaince.subscription.accounting.journal.processor.JournalizingProcessor;
import com.affaince.subscription.accounting.journal.processor.SubsidiaryJournalizingProcessor;
import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.ledger.processor.LedgerPostingProcessor;
import com.affaince.subscription.accounting.transactions.SourceDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//giver is bank account, receiver is rent account
@Component
public class PremiseRentPaymentEventProcessor extends AbstractAccountingEventListener {
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    @Autowired
    public PremiseRentPaymentEventProcessor(SubsidiaryJournalizingProcessor subsidiaryJournalizingProcessor, CashBookJournalizingProcessor cashBookJournalizingProcessor, JournalizingProcessor journalizingProcessor, LedgerPostingProcessor ledgerPostingProcessor, JournalDatabaseSimulator journalDatabaseSimulator, CashBookDatabaseSimulator cashBookDatabaseSimulator, PurchaseBookDatabaseSimulator purchaseBookDatabaseSimulator, PurchaseReturnBookDatabaseSimulator purchaseReturnBookDatabaseSimulator, SalesBookDatabaseSimulator salesBookDatabaseSimulator, SalesReturnBookDatabaseSimulator salesReturnBookDatabaseSimulator, AccountDatabaseSimulator accountDatabaseSimulator) {
        super(subsidiaryJournalizingProcessor, cashBookJournalizingProcessor, journalizingProcessor, ledgerPostingProcessor, journalDatabaseSimulator, cashBookDatabaseSimulator, purchaseBookDatabaseSimulator, purchaseReturnBookDatabaseSimulator, salesBookDatabaseSimulator, salesReturnBookDatabaseSimulator);
        this.accountDatabaseSimulator = accountDatabaseSimulator;
    }

    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        return new ParticipantAccount(null,null, accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,receivedAmount);
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        return new ParticipantAccount(null,null,accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(),AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT,receivedAmount);
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

}
