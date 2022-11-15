package com.affaince.subscription.accounting.journal.events;

import com.affaince.subscription.accounting.db.*;
import com.affaince.subscription.accounting.journal.entity.ParticipantAccount;
import com.affaince.subscription.accounting.journal.processor.CashBookJournalizingProcessor;
import com.affaince.subscription.accounting.journal.processor.JournalizingProcessor;
import com.affaince.subscription.accounting.journal.processor.SubsidiaryJournalizingProcessor;
import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.subscription.accounting.ledger.processor.LedgerPostingProcessor;
import com.affaince.subscription.accounting.stock.ClosingStockAccount;
import com.affaince.subscription.accounting.transactions.Party;
import com.affaince.subscription.accounting.transactions.SourceDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Giver is sale account and receiver(beneficiary) is subscriber account
//in case of on credit mode ->sales is credited and subscriber is debited
//in case of on payment mode ->bank is debited and sales is credited
@Component
public class GoodsDeliveryToSubscriberEventProcessor extends AbstractAccountingEventListener {
    private final PartyDatabaseSimulator partyDatabaseSimulator;
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    private final ClosingStockDatabaseSimulator closingStockDatabaseSimulator;
    @Autowired
    public GoodsDeliveryToSubscriberEventProcessor(SubsidiaryJournalizingProcessor subsidiaryJournalizingProcessor, CashBookJournalizingProcessor cashBookJournalizingProcessor, JournalizingProcessor journalizingProcessor, LedgerPostingProcessor ledgerPostingProcessor, JournalDatabaseSimulator journalDatabaseSimulator, CashBookDatabaseSimulator cashBookDatabaseSimulator, PurchaseBookDatabaseSimulator purchaseBookDatabaseSimulator, PurchaseReturnBookDatabaseSimulator purchaseReturnBookDatabaseSimulator, SalesBookDatabaseSimulator salesBookDatabaseSimulator, SalesReturnBookDatabaseSimulator salesReturnBookDatabaseSimulator, PartyDatabaseSimulator partyDatabaseSimulator, AccountDatabaseSimulator accountDatabaseSimulator, ClosingStockDatabaseSimulator closingStockDatabaseSimulator) {
        super(subsidiaryJournalizingProcessor, cashBookJournalizingProcessor, journalizingProcessor, ledgerPostingProcessor, journalDatabaseSimulator, cashBookDatabaseSimulator, purchaseBookDatabaseSimulator, purchaseReturnBookDatabaseSimulator, salesBookDatabaseSimulator, salesReturnBookDatabaseSimulator);
        this.partyDatabaseSimulator = partyDatabaseSimulator;
        this.accountDatabaseSimulator = accountDatabaseSimulator;
        this.closingStockDatabaseSimulator = closingStockDatabaseSimulator;
    }

    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument) {
        double receiverAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        return new ParticipantAccount(null,null, accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_SALES_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(), AccountIdentifier.BUSINESS_SALES_ACCOUNT, receiverAmount);
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument) {
        double receiverAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        if(sourceDocument.getModeOfTransaction() == ModeOfTransaction.BY_PAYMENT){
            return new ParticipantAccount(null,null,accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(), AccountIdentifier.BUSINESS_BANK_ACCOUNT, receiverAmount);
        }else{
            Party receiverParty = partyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getReceiverParticipant().getPartyId());
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

    @Override
    public void onEvent(SourceDocument sourceDocument){
        super.onEvent(sourceDocument);
        double giverAmount = sourceDocument.getGiverParticipant().getAmountExchanged();
        ClosingStockAccount latestClosingStockAccount = closingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(sourceDocument.getMerchantId(), "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        if(latestClosingStockAccount.getClosureDate().isBefore(sourceDocument.getDateOfTransaction())){
            throw new RuntimeException("Wrong closing accoutn instance selected; closing stock account should be active at the time of accounting event");
        }
        latestClosingStockAccount.deductFromBalanceAccount(giverAmount);
    }


}
