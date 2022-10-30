package com.affaince.accounting.db;

import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PartyDatabaseSimulator {
    private List<Party> parties;
    @Autowired
    public PartyDatabaseSimulator() {
        this.parties = new ArrayList<>();
        buildDatabase();
    }

    public void buildDatabase(){
        Party merchant1 = new Party("merchant1", "merchant1","capital",PartyTypes.MERCHANT);
        parties.add(merchant1);

        Party supplierOfProduct1 = new Party("merchant1","supplierOfProduct1", "supplierOfProduct1",PartyTypes.SUPPLIER_OF_GOODS);
        Party supplierOfProduct2 = new Party("merchant1","supplierOfProduct2", "supplierOfProduct2",PartyTypes.SUPPLIER_OF_GOODS);
        Party supplierOfProduct3 = new Party("merchant1","supplierOfProduct3", "supplierOfProduct3",PartyTypes.SUPPLIER_OF_GOODS);
        parties.add(supplierOfProduct1);
        parties.add(supplierOfProduct2);
        parties.add(supplierOfProduct3);

        Party distributionServiceProvider1 = new Party("merchant1","distributionServiceProvider1", "distributionServiceProvider1",PartyTypes.DISTRIBUTION_SUPPLIER);
        Party distributionServiceProvider2 = new Party("merchant1","distributionServiceProvider2", "distributionServiceProvider2",PartyTypes.DISTRIBUTION_SUPPLIER);
        parties.add(distributionServiceProvider1);
        parties.add(distributionServiceProvider2);

        Party subscriber1 = new Party("merchant1","subscriber1", "subscriber1",PartyTypes.SUBSCRIBER);
        Party subscriber2 = new Party("merchant1","subscriber2","subscriber2", PartyTypes.SUBSCRIBER);
        Party subscriber3 = new Party("merchant1","subscriber3","subscriber3", PartyTypes.SUBSCRIBER);
        parties.add(subscriber1);
        parties.add(subscriber2);
        parties.add(subscriber3);
    }

    public Party searchByMerchantIdAndPartyId(String merchantId, String partyId){
            return parties.stream().filter(party->party.getPartyId().equals(partyId) && party.getMerchantId().equals(merchantId)).findFirst().get();
    }
}
