package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.processor.CashBookJournalizingProcessor;
import com.affaince.accounting.journal.processor.JournalizingProcessor;
import com.affaince.accounting.journal.processor.SubsidiaryJournalizingProcessor;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.processor.LedgerPostingProcessor;
import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.transactions.SourceDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//SourceDocument assumes that any transaction either brings money to the business or sends money by the business to some external stakeholder.
//So it should either have a giver or the receiver as the business. It is system's responsibility to identify the representative business account.
// representative business account can be cash, bank account, discount, profit/loss account, reward etc.
// For each type of event the possible BUSINESS REPRESENTATIVES should be provided by the respective Event Processor.
//When one of the party is BUSINESS, the opposite Party will be an external stakeholder.
//Stakeholders are identified as supplier,subscriber,service provider etc.
//The party except business should have the identifier of the stakeholder.


// The giver should be merchant account and receiver should be Business Capital account
@Component
public class CapitalInvestmentEventProcessor extends AbstractAccountingEventListener {
    private final PartyDatabaseSimulator partyDatabaseSimulator;
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    @Autowired
    public CapitalInvestmentEventProcessor(SubsidiaryJournalizingProcessor subsidiaryJournalizingProcessor, CashBookJournalizingProcessor cashBookJournalizingProcessor, JournalizingProcessor journalizingProcessor, LedgerPostingProcessor ledgerPostingProcessor, JournalDatabaseSimulator journalDatabaseSimulator, CashBookDatabaseSimulator cashBookDatabaseSimulator, PurchaseBookDatabaseSimulator purchaseBookDatabaseSimulator, PurchaseReturnBookDatabaseSimulator purchaseReturnBookDatabaseSimulator, SalesBookDatabaseSimulator salesBookDatabaseSimulator, SalesReturnBookDatabaseSimulator salesReturnBookDatabaseSimulator, PartyDatabaseSimulator partyDatabaseSimulator, AccountDatabaseSimulator accountDatabaseSimulator) {
        super(subsidiaryJournalizingProcessor, cashBookJournalizingProcessor, journalizingProcessor, ledgerPostingProcessor, journalDatabaseSimulator, cashBookDatabaseSimulator, purchaseBookDatabaseSimulator, purchaseReturnBookDatabaseSimulator, salesBookDatabaseSimulator, salesReturnBookDatabaseSimulator);
        this.partyDatabaseSimulator = partyDatabaseSimulator;
        this.accountDatabaseSimulator = accountDatabaseSimulator;
    }
    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument) {
        double receiverAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        Party giverParty = partyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getGiverParticipant().getPartyId());
        String giverAccountId = giverParty.getAccountId();
        return new ParticipantAccount(giverParty.getPartyId(),giverParty.getPartyType(),giverAccountId,giverParty.getPartyType().getAccountIdentifier(),receiverAmount);
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument) {
        double receiverAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        return new ParticipantAccount(null,null, accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,receiverAmount);
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

}
