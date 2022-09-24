package com.affaince.accounting.client;

import org.joda.time.LocalDateTime;

public class AccountingTransactionRequest {
    private String merchantId;
    private AccountingEvent accountingEvent;
    private double transactionAmount;
    private LocalDateTime dateOfTransaction;
    private boolean isTransactionOnCredit;
    private PartyTypes giverParticipantType;
    private String giverPartyId;
    private double giverAmount;
    private ExchangeableItems exchangeableItem;
    private PartyTypes receiverParticipantType;
    private String receiverPartyId;
    private double receiverAmount;

    public String getMerchantId() {
        return merchantId;
    }

    public AccountingEvent getAccountingEvent() {
        return accountingEvent;
    }

    public void setAccountingEvent(AccountingEvent accountingEvent) {
        this.accountingEvent = accountingEvent;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public ExchangeableItems getExchangeableItem() {
        return exchangeableItem;
    }

    public void setExchangeableItem(ExchangeableItems exchangeableItem) {
        this.exchangeableItem = exchangeableItem;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public boolean isTransactionOnCredit() {
        return isTransactionOnCredit;
    }

    public void setTransactionOnCredit(boolean transactionOnCredit) {
        this.isTransactionOnCredit = transactionOnCredit;
    }

    public PartyTypes getGiverParticipantType() {
        return giverParticipantType;
    }

    public void setGiverParticipantType(PartyTypes giverParticipantType) {
        this.giverParticipantType = giverParticipantType;
    }

    public String getGiverPartyId() {
        return giverPartyId;
    }

    public void setGiverPartyId(String giverPartyId) {
        this.giverPartyId = giverPartyId;
    }

    public double getGiverAmount() {
        return giverAmount;
    }

    public void setGiverAmount(double giverAmount) {
        this.giverAmount = giverAmount;
    }

    public PartyTypes getReceiverParticipantType() {
        return receiverParticipantType;
    }

    public void setReceiverParticipantType(PartyTypes receiverParticipantType) {
        this.receiverParticipantType = receiverParticipantType;
    }

    public String getReceiverPartyId() {
        return receiverPartyId;
    }

    public void setReceiverPartyId(String receiverPartyId) {
        this.receiverPartyId = receiverPartyId;
    }

    public double getReceiverAmount() {
        return receiverAmount;
    }

    public void setReceiverAmount(double receiverAmount) {
        this.receiverAmount = receiverAmount;
    }
}
