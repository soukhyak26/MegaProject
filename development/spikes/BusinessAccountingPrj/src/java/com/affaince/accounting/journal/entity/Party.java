package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.PartyTypes;

public class Party {
    private String partyId;
    private PartyTypes partyType;
    private ExchangeableItems itemExchanged;
    private double amountExchanged ;
}
