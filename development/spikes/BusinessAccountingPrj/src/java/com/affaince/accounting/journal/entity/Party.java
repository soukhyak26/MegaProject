package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.PartyTypes;

public class Party {
    private String merchantId;
    private String partyId;
    private String accountId;
    private PartyTypes partyType;



    public Party(String merchantId,String partyId, String accountId,PartyTypes partyType) {
        this.merchantId=merchantId;
        this.partyId = partyId;
        this.partyType = partyType;
        this.accountId=accountId;
    }

    public String getMerchantId() {
        return merchantId;
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
}
