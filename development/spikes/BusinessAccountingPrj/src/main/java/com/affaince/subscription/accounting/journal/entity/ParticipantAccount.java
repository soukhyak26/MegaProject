package com.affaince.subscription.accounting.journal.entity;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.journal.qualifiers.PartyTypes;

public class ParticipantAccount {
    private String partyId;
    private PartyTypes partyType;
    private String accountId;
    private AccountIdentifier accountIdentifier;
    private double amountExchanged;

    public ParticipantAccount(String partyId,PartyTypes partyType,String accountId, AccountIdentifier accountIdentifier, double amountExchanged) {
        this.partyId=partyId;
        this.partyType=partyType;
        this.accountId = accountId;
        this.accountIdentifier = accountIdentifier;
        this.amountExchanged = amountExchanged;
    }

    public String getPartyId() {
        return partyId;
    }

    public PartyTypes getPartyType() {
        return partyType;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public double getAmountExchanged() {
        return amountExchanged;
    }
}
