package com.affaince.accounting.db;

import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.journal.entity.Party;
import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.PartyTypes;

import java.util.ArrayList;
import java.util.List;

public class PartyDatabaseSimulator {

    private static List<Party> parties=new ArrayList<>();

    public static void buildDatabase(){
        Party merchant1 = new Party("merchant1", "merchant1",PartyTypes.MERCHANT);
        parties.add(merchant1);

        Party supplierOfProduct1 = new Party("supplierOfProduct1", "supplierOfProduct1",PartyTypes.SUPPLIER_OF_GOODS);
        Party supplierOfProduct2 = new Party("supplierOfProduct2", "supplierOfProduct2",PartyTypes.SUPPLIER_OF_GOODS);
        Party supplierOfProduct3 = new Party("supplierOfProduct3", "supplierOfProduct3",PartyTypes.SUPPLIER_OF_GOODS);
        parties.add(supplierOfProduct1);
        parties.add(supplierOfProduct2);
        parties.add(supplierOfProduct3);

        Party distributionServiceProvider1 = new Party("distributionServiceProvider1", "distributionServiceProvider1",PartyTypes.DISTRIBUTION_SUPPLIER);
        Party distributionServiceProvider2 = new Party("distributionServiceProvider2", "distributionServiceProvider2",PartyTypes.DISTRIBUTION_SUPPLIER);
        parties.add(distributionServiceProvider1);
        parties.add(distributionServiceProvider2);

        Party subscriber1 = new Party("subscriber1", "subscriber1",PartyTypes.SUBSCRIBER);
        Party subscriber2 = new Party("subscriber2","subscriber2", PartyTypes.SUBSCRIBER);
        Party subscriber3 = new Party("subscriber3","subscriber3", PartyTypes.SUBSCRIBER);
        parties.add(subscriber1);
        parties.add(subscriber2);
        parties.add(subscriber3);
    }

    public static Party searchByPartyId(String partyId){
            return parties.stream().filter(party->party.getPartyId().equals(partyId)).findFirst().get();
    }
}
