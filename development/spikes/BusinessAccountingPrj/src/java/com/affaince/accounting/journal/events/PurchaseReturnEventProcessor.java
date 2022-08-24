package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.transactions.SourceDocument;

//in case of credit ->liability is reduced as the amount owed to the supplier is cancelled, this reduction is balanced by the increase in owners equity.
//sender->supplier,receiver-> purchase return
//in case of credit -> debit purchase return and credit supplier
//in case of cash -> supplier gives the cash and takes the goods back
//in case of cash-> debit purchase return credit bank account
public class PurchaseReturnEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        if (sourceDocument.getModeOfTransaction() == ModeOfTransaction.ON_CREDIT) {
            return new ParticipantAccount(null,null, AccountDatabaseSimulator.searchLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_PURCHASE_RETURN_ACCOUNT).get(0).getAccountId(), AccountIdentifier.BUSINESS_PURCHASE_RETURN_ACCOUNT, amountExchanged);
        }else {
            Party giverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(), sourceDocument.getGiverParticipant().getPartyId());
            String giverAccountId = giverParty.getAccountId();
            return new ParticipantAccount(giverParty.getPartyId(), giverParty.getPartyType(), giverAccountId, giverParty.getPartyType().getAccountIdentifier(), sourceDocument.getGiverParticipant().getAmountExchanged());
        }
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        if (sourceDocument.getModeOfTransaction() == ModeOfTransaction.ON_CREDIT) {
            Party giverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(), sourceDocument.getGiverParticipant().getPartyId());
            String giverAccountId = giverParty.getAccountId();
            return new ParticipantAccount(giverParty.getPartyId(), giverParty.getPartyType(), giverAccountId, giverParty.getPartyType().getAccountIdentifier(), sourceDocument.getGiverParticipant().getAmountExchanged());
        } else {
            return new ParticipantAccount(null,null,AccountDatabaseSimulator.searchLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT).get(0).getAccountId(), AccountIdentifier.BUSINESS_BANK_ACCOUNT, amountExchanged);
        }
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        return null;
    }

}
