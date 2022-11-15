package com.affaince.subscription.accounting.journal.events;

import com.affaince.subscription.accounting.db.*;
import com.affaince.subscription.accounting.journal.entity.ParticipantAccount;
import com.affaince.subscription.accounting.journal.processor.CashBookJournalizingProcessor;
import com.affaince.subscription.accounting.journal.processor.JournalizingProcessor;
import com.affaince.subscription.accounting.journal.processor.SubsidiaryJournalizingProcessor;
import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.ledger.processor.LedgerPostingProcessor;
import com.affaince.subscription.accounting.transactions.Party;
import com.affaince.subscription.accounting.transactions.SourceDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//The giver should be business bank acct and receiver(beneficiary) should be business services provider account
//this event is possible only when distribution services are availed on credit.. else it is taken care in dist. services availed event
@Component
public class DistributionServicesPaymentEventProcessor extends AbstractAccountingEventListener {
    private final PartyDatabaseSimulator partyDatabaseSimulator;
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    @Autowired
    public DistributionServicesPaymentEventProcessor(SubsidiaryJournalizingProcessor subsidiaryJournalizingProcessor, CashBookJournalizingProcessor cashBookJournalizingProcessor, JournalizingProcessor journalizingProcessor, LedgerPostingProcessor ledgerPostingProcessor, JournalDatabaseSimulator journalDatabaseSimulator, CashBookDatabaseSimulator cashBookDatabaseSimulator, PurchaseBookDatabaseSimulator purchaseBookDatabaseSimulator, PurchaseReturnBookDatabaseSimulator purchaseReturnBookDatabaseSimulator, SalesBookDatabaseSimulator salesBookDatabaseSimulator, SalesReturnBookDatabaseSimulator salesReturnBookDatabaseSimulator, PartyDatabaseSimulator partyDatabaseSimulator, AccountDatabaseSimulator accountDatabaseSimulator) {
        super(subsidiaryJournalizingProcessor, cashBookJournalizingProcessor, journalizingProcessor, ledgerPostingProcessor, journalDatabaseSimulator, cashBookDatabaseSimulator, purchaseBookDatabaseSimulator, purchaseReturnBookDatabaseSimulator, salesBookDatabaseSimulator, salesReturnBookDatabaseSimulator);
        this.partyDatabaseSimulator = partyDatabaseSimulator;
        this.accountDatabaseSimulator = accountDatabaseSimulator;
    }

    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        return new ParticipantAccount(null,null, accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,receivedAmount);
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        Party receiverParty = partyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getReceiverParticipant().getPartyId());
        AccountIdentifier receiverAccountIdentifier = receiverParty.getPartyType().getAccountIdentifier();
        return new ParticipantAccount(receiverParty.getPartyId(), receiverParty.getPartyType(),receiverParty.getAccountId(), receiverAccountIdentifier,receivedAmount);
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

}
