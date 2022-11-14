package com.affaince.subscription.accounting.journal.events;

import com.affaince.accounting.db.*;
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
import com.affaince.subscription.accounting.db.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//in case of credit ->liability is reduced as the amount owed to the supplier is cancelled, this reduction is balanced by the increase in owners equity.
//sender->supplier,receiver-> purchase return
//in case of credit -> debit purchase return and credit supplier
//in case of cash -> supplier gives the cash and takes the goods back
//in case of cash-> debit purchase return credit bank account
@Component
public class PurchaseReturnEventProcessor extends AbstractAccountingEventListener {
    private final PartyDatabaseSimulator partyDatabaseSimulator;
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    private final ClosingStockDatabaseSimulator closingStockDatabaseSimulator;
    @Autowired
    public PurchaseReturnEventProcessor(SubsidiaryJournalizingProcessor subsidiaryJournalizingProcessor, CashBookJournalizingProcessor cashBookJournalizingProcessor, JournalizingProcessor journalizingProcessor, LedgerPostingProcessor ledgerPostingProcessor, JournalDatabaseSimulator journalDatabaseSimulator, CashBookDatabaseSimulator cashBookDatabaseSimulator, PurchaseBookDatabaseSimulator purchaseBookDatabaseSimulator, PurchaseReturnBookDatabaseSimulator purchaseReturnBookDatabaseSimulator, SalesBookDatabaseSimulator salesBookDatabaseSimulator, SalesReturnBookDatabaseSimulator salesReturnBookDatabaseSimulator, PartyDatabaseSimulator partyDatabaseSimulator, AccountDatabaseSimulator accountDatabaseSimulator, ClosingStockDatabaseSimulator closingStockDatabaseSimulator) {
        super(subsidiaryJournalizingProcessor, cashBookJournalizingProcessor, journalizingProcessor, ledgerPostingProcessor, journalDatabaseSimulator, cashBookDatabaseSimulator, purchaseBookDatabaseSimulator, purchaseReturnBookDatabaseSimulator, salesBookDatabaseSimulator, salesReturnBookDatabaseSimulator);
        this.partyDatabaseSimulator = partyDatabaseSimulator;
        this.accountDatabaseSimulator = accountDatabaseSimulator;
        this.closingStockDatabaseSimulator = closingStockDatabaseSimulator;
    }

    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        if (sourceDocument.getModeOfTransaction() == ModeOfTransaction.ON_CREDIT) {
            return new ParticipantAccount(null,null, accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(), AccountIdentifier.BUSINESS_PURCHASE_RETURN_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(), AccountIdentifier.BUSINESS_PURCHASE_RETURN_ACCOUNT, receivedAmount);
        }else {
            Party giverParty = partyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(), sourceDocument.getGiverParticipant().getPartyId());
            String giverAccountId = giverParty.getAccountId();
            return new ParticipantAccount(giverParty.getPartyId(), giverParty.getPartyType(), giverAccountId, giverParty.getPartyType().getAccountIdentifier(), receivedAmount);
        }
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        if (sourceDocument.getModeOfTransaction() == ModeOfTransaction.ON_CREDIT) {
            Party giverParty = partyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(), sourceDocument.getGiverParticipant().getPartyId());
            String giverAccountId = giverParty.getAccountId();
            return new ParticipantAccount(giverParty.getPartyId(), giverParty.getPartyType(), giverAccountId, giverParty.getPartyType().getAccountIdentifier(), receivedAmount);
        } else {
            return new ParticipantAccount(null,null,accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(), AccountIdentifier.BUSINESS_BANK_ACCOUNT, receivedAmount);
        }
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        return null;
    }
    @Override
    public void onEvent(SourceDocument sourceDocument){
        super.onEvent(sourceDocument);
        double giverAmount = sourceDocument.getGiverParticipant().getAmountExchanged();
        ClosingStockAccount latestClosingStockAccount = closingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(sourceDocument.getMerchantId(), "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        if(latestClosingStockAccount.getClosureDate().isBefore(sourceDocument.getDateOfTransaction())){
            throw new RuntimeException("Wrong closing account instance selected; closing stock account should be active at the time of accounting event");
        }
        latestClosingStockAccount.deductFromBalanceAccount(giverAmount);
    }

}
