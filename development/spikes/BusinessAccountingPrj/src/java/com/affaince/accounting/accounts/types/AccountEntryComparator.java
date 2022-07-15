package com.affaince.accounting.accounts.types;

import org.joda.time.DateTimeComparator;

import java.util.Comparator;

public class AccountEntryComparator implements Comparator<AccountEntry> {
    @Override
    public int compare(AccountEntry o1, AccountEntry o2) {
        return DateTimeComparator.getInstance().compare(o1.getDate(),o2.getDate());
    }
}
