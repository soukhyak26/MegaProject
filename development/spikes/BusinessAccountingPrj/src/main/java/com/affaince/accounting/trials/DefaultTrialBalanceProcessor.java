package com.affaince.accounting.trials;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//@Deprecated
@Component
public class DefaultTrialBalanceProcessor implements TrialBalanceProcessor {
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    //private final ClosingStockDatabaseSimulator closingStockDatabaseSimulator;

    @Autowired
    public DefaultTrialBalanceProcessor(AccountDatabaseSimulator accountDatabaseSimulator) {
        this.accountDatabaseSimulator = accountDatabaseSimulator;
    }

/*
    @Override
    public TrialBalance processTrialBalance(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
        List<LedgerAccount> closedLedgerAccountsOfAMerchant = accountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
        TrialBalance trialBalance = new TrialBalance(merchantId,"1", closureDate.toLocalDate());
        for (LedgerAccount ledgerAccount : closedLedgerAccountsOfAMerchant){
            LedgerAccountEntry creditLedgerEntry = ledgerAccount.getCredits().stream().filter(acc->acc.getAccountIdentifier() == AccountIdentifier.BY_BALANCE_CARRIED_DOWN).findAny().orElse(null);
            double creditBalanceBroughtDown=0;
            double debitBalanceBroughtDown=0;
            if(null != creditLedgerEntry){
                debitBalanceBroughtDown += creditLedgerEntry.getAmount();
            }
            LedgerAccountEntry debitLedgerEntry =ledgerAccount.getDebits().stream().filter(acc->acc.getAccountIdentifier()== AccountIdentifier.TO_BALANCE_CARRIED_DOWN).findAny().orElse(null);

            if(null != debitLedgerEntry){
                creditBalanceBroughtDown += debitLedgerEntry.getAmount();
            }
            if(creditBalanceBroughtDown>0 && debitBalanceBroughtDown>0){
                throw new RuntimeException("credit balance brought down and debit balance brought down both cannot be present at same time");
            }
            switch (ledgerAccount.getAccountIdentifier().getAccountQualifiers()){
                case PERSONAL_LEDGER_ACCOUNT:
                case REAL_LEDGER_ACCOUNT:
                case NOMINAL_LEDGER_ACCOUNT:
                    if(creditBalanceBroughtDown >0) {
                        trialBalance.addToCreditEntries(new CreditTrialBalanceEntry(ledgerAccount.getAccountId(), ledgerAccount.getAccountIdentifier(), null, creditBalanceBroughtDown, NatureOfBalance.CUSTOMERS));
                    }else if(debitBalanceBroughtDown>0){
                        trialBalance.addToDebitEntries(new DebitTrialBalanceEntry(ledgerAccount.getAccountId(), ledgerAccount.getAccountIdentifier(), null, debitBalanceBroughtDown, NatureOfBalance.CUSTOMERS));
                    }
                    break;
            }
        }
        return trialBalance;
    }
*/

    @Override
    public TrialBalance processTrialBalance(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
        List<LedgerAccount> activeAccountsOfAMerchant = accountDatabaseSimulator.getAllActiveAccounts(merchantId, startDate, closureDate);
        TrialBalance trialBalance = new TrialBalance(merchantId, "1", closureDate.toLocalDate());
        for (LedgerAccount ledgerAccount : activeAccountsOfAMerchant) {
            List<LedgerAccountEntry> creditLedgerEntries = ledgerAccount.getCredits().stream().
                    filter(ledgerAccountEntry -> ledgerAccountEntry.getAccountIdentifier() != AccountIdentifier.BY_BALANCE_BROUGHT_DOWN ).
                    collect(Collectors.toList());
            List<LedgerAccountEntry> debitLedgerEntries = ledgerAccount.getDebits().stream().
                    filter(ledgerAccountEntry -> ledgerAccountEntry.getAccountIdentifier() != AccountIdentifier.TO_BALANCE_BROUGHT_DOWN).
                    collect(Collectors.toList());
            for (LedgerAccountEntry creditLedgerEntry : creditLedgerEntries) {
                if (creditLedgerEntry.getAmount() > 0) {
                    trialBalance.addToCreditEntries(new CreditTrialBalanceEntry(ledgerAccount.getAccountId(), ledgerAccount.getAccountIdentifier(), creditLedgerEntry.getPeerAccountNumber(),creditLedgerEntry.getAccountIdentifier(),null, creditLedgerEntry.getAmount(), NatureOfBalance.CUSTOMERS));
                }
            }
            for (LedgerAccountEntry debitLedgerEntry : debitLedgerEntries) {
                if (debitLedgerEntry.getAmount() > 0) {
                    trialBalance.addToDebitEntries(new DebitTrialBalanceEntry(ledgerAccount.getAccountId(), ledgerAccount.getAccountIdentifier(), debitLedgerEntry.getPeerAccountNumber(),debitLedgerEntry.getAccountIdentifier(),null, debitLedgerEntry.getAmount(), NatureOfBalance.CUSTOMERS));
                }
            }
        }
        return trialBalance;
    }

}
