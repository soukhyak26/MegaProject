package com.affaince.accounting.journal.entity;

import com.affaince.accounting.accounts.types.CreditEntry;
import com.affaince.accounting.accounts.types.DebitEntry;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class DoubleEJournalEntry {
    private List<DebitJournalEntry> debits;
    private List<CreditJournalEntry> credits;

    public DoubleEJournalEntry() {
        debits = new ArrayList<>();
        credits = new ArrayList<>();
    }

    public void debit(DebitJournalEntry debitJournalEntry){
        requireNonNull(debitJournalEntry);
        this.debits.add(debitJournalEntry);
    }

    public void credit(CreditJournalEntry creditJournalEntry){
        requireNonNull(creditJournalEntry);
        this.credits.add(creditJournalEntry);
    }

    public List<DebitJournalEntry> getDebits() {
        return debits;
    }

    public List<CreditJournalEntry> getCredits() {
        return credits;
    }
}
