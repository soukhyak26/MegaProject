package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.ClosingStockDatabaseSimulator;
import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.transactions.SourceDocument;

//subscriber account is giver and sales return is default receiver
//debit sales return in case of cash or on credit transaction
//in case of credit transaction credit receiver subscriber,in case of cash transaction credit bank
public class SalesReturnEventProcessor extends AbstractAccountingEventListener {

    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        if(sourceDocument.getModeOfTransaction() == ModeOfTransaction.ON_CREDIT){
            Party giverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getGiverParticipant().getPartyId());
            String giverAccountId = giverParty.getAccountId();
            return new ParticipantAccount(giverParty.getPartyId(),giverParty.getPartyType(),giverAccountId,giverParty.getPartyType().getAccountIdentifier(),receivedAmount);
        }else{
            return new ParticipantAccount(null,null, AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,receivedAmount);
        }
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        return new ParticipantAccount(null,null,AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_SALES_RETURN_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(),AccountIdentifier.BUSINESS_SALES_RETURN_ACCOUNT,receivedAmount);
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public void onEvent(SourceDocument sourceDocument){
        super.onEvent(sourceDocument);
        double giverAmount = sourceDocument.getGiverParticipant().getAmountExchanged();
        ClosingStockAccount latestClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(sourceDocument.getMerchantId(), "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        if(latestClosingStockAccount.getClosureDate().isBefore(sourceDocument.getDateOfTransaction())){
            throw new RuntimeException("Wrong closing accoutn instance selected; closing stock account should be active at the time of accounting event");
        }
        latestClosingStockAccount.addToBalanceAmount(giverAmount);
    }

}
