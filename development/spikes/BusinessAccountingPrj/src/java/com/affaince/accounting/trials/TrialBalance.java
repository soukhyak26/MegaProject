package com.affaince.accounting.trials;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class TrialBalance {
    private String merchantId;
    private String id;
    private LocalDate date;
    private List<TrialBalanceEntry> creditEntries;
    private List<TrialBalanceEntry> debitEntries;

    public TrialBalance(String merchantId,String id, LocalDate date) {
        this.merchantId=merchantId;
        this.id = id;
        this.date = date;
        this.creditEntries= new ArrayList<>();
        this.debitEntries = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<TrialBalanceEntry> getCreditEntries() {
        return creditEntries;
    }

    public List<TrialBalanceEntry> getDebitEntries() {
        return debitEntries;
    }

    public void addToDebitEntries(DebitTrialBalanceEntry debitEntry){
        this.debitEntries.add(debitEntry);
    }
    public void addToCreditEntries(CreditTrialBalanceEntry creditEntry){
        this.creditEntries.add(creditEntry);
    }

    public boolean isTrialBalanceTallied(){
        double creditBalance = creditEntries.stream().mapToDouble(crent->crent.getBalanceAmount()).sum();
        System.out.println("trial Balance :: credit balance : " +creditBalance);
        double debitBalance = debitEntries.stream().mapToDouble(drent->drent.getBalanceAmount()).sum();
        System.out.println("trial balance :: debit balance : " + debitBalance);
        return creditBalance== debitBalance;
    }

    @Override
    public String toString() {
        return "TrialBalance{" +
                "Id='" + id + '\'' +
                ", date=" + date +
                ", creditEntries=" + creditEntries +
                ", debitEntries=" + debitEntries +
                '}';
    }
}
