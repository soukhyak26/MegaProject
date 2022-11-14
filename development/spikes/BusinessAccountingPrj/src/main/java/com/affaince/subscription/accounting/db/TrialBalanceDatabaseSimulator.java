package com.affaince.subscription.accounting.db;

import com.affaince.subscription.accounting.trials.TrialBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class TrialBalanceDatabaseSimulator {
    private List<TrialBalance> allTrialBalances = new ArrayList<>();
    @Autowired
    public TrialBalanceDatabaseSimulator(){
        allTrialBalances = new ArrayList<>();
    }
    public void addTrialBalance(TrialBalance trialBalance){
        allTrialBalances.add(trialBalance);
    }

    public TrialBalance searchLatestTrialBalance(String merchantId){
        List<TrialBalance> trialBalancesOfAMerchant = allTrialBalances.stream().filter(trbl->trbl.getMerchantId().equals(merchantId)).sorted(Comparator.comparing(TrialBalance::getDate).reversed()).collect(Collectors.toList());
        if(null != trialBalancesOfAMerchant && trialBalancesOfAMerchant.size()>0 ){
            return trialBalancesOfAMerchant.get(0);
        }
        return null;
    }

}
