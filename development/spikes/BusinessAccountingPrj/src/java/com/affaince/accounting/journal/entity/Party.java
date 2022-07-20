package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.PartyTypes;

public class Party {
    private String partyId;
    private PartyTypes partyType;
    private ExchangeableItems itemExchanged;
    private double amountExchanged ;

    public Party(String partyId, PartyTypes partyType, ExchangeableItems itemExchanged, double amountExchanged) {
        this.partyId = partyId;
        this.partyType = partyType;
        this.itemExchanged = itemExchanged;
        this.amountExchanged = amountExchanged;
    }

    public String getPartyId() {
        return partyId;
    }

    public PartyTypes getPartyType() {
        return partyType;
    }

    public ExchangeableItems getItemExchanged() {
        return itemExchanged;
    }

    public double getAmountExchanged() {
        return amountExchanged;
    }
}
