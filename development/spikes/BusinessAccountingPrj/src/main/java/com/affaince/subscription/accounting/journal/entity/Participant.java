package com.affaince.subscription.accounting.journal.entity;

import com.affaince.subscription.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.subscription.accounting.journal.qualifiers.PartyTypes;

public class Participant {
    private String merchantId;
    private String partyId;
    private PartyTypes partyType;
    private ExchangeableItems itemExchanged;
    private double amountExchanged ;

    public Participant(String merchantId,String partyId,PartyTypes partyType, ExchangeableItems itemExchanged, double amountExchanged) {
        this.merchantId=merchantId;
        this.partyId = partyId;
        this.partyType = partyType;
        this.itemExchanged = itemExchanged;
        this.amountExchanged = amountExchanged;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public PartyTypes getPartyType() {
        return partyType;
    }

    public String getPartyId() {
        return partyId;
    }

    public ExchangeableItems getItemExchanged() {
        return itemExchanged;
    }

    public double getAmountExchanged() {
        return amountExchanged;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "merchantId='" + merchantId + '\'' +
                ", partyId='" + partyId + '\'' +
                ", partyType=" + partyType +
                ", itemExchanged=" + itemExchanged +
                ", amountExchanged=" + amountExchanged +
                '}';
    }
}
