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

//Giver is sale account and receiver(beneficiary) is subscriber account
//in case of on credit mode ->sales is credited and subscriber is debited
//in case of on payment mode ->bank is debited and sales is credited
public class GoodsDeliveryToSubscriberEventProcessor extends AbstractAccountingEventListener {
    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument) {
        double receiverAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        return new ParticipantAccount(null,null, AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_SALES_ACCOUNT).get(0).getAccountId(), AccountIdentifier.BUSINESS_SALES_ACCOUNT, receiverAmount);
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument) {
        double receiverAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        if(sourceDocument.getModeOfTransaction() == ModeOfTransaction.BY_PAYMENT){
            return new ParticipantAccount(null,null,AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT).get(0).getAccountId(), AccountIdentifier.BUSINESS_BANK_ACCOUNT, receiverAmount);
        }else{
            Party receiverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getReceiverParticipant().getPartyId());
            String giverAccountId = receiverParty.getAccountId();
            return new ParticipantAccount(receiverParty.getPartyId(), receiverParty.getPartyType(),giverAccountId,receiverParty.getPartyType().getAccountIdentifier(),receiverAmount);
        }
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }
    // Subscriber consumes the stock by equivalent amount.
    // So Deduct that amount from ClosingStockAccount
    public void onEvent(SourceDocument sourceDocument){
        double giverAmount = sourceDocument.getGiverParticipant().getAmountExchanged();
        ClosingStockAccount latestClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(sourceDocument.getMerchantId(), "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        if(latestClosingStockAccount.getClosureDate().isBefore(sourceDocument.getDateOfTransaction())){
            throw new RuntimeException("Wrong closing accoutn instance selected; closing stock account should be active at the time of accounting event");
        }
        latestClosingStockAccount.deductFromBalanceAccount(giverAmount);
    }

}
