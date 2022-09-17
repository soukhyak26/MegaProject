package com.affaince.accounting.db;

import com.affaince.accounting.trials.TrialBalance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TrialBalanceDatabaseSimulator {
    private static final List<TrialBalance> allTrialBalances = new ArrayList<>();
    public static void addTrialBalance(TrialBalance trialBalance){
        allTrialBalances.add(trialBalance);
    }

    public static TrialBalance searchLatestTrialBalance(String merchantId){
        List<TrialBalance> trialBalancesOfAMerchant = allTrialBalances.stream().filter(trbl->trbl.getMerchantId().equals(merchantId)).sorted(Comparator.comparing(TrialBalance::getDate).reversed()).collect(Collectors.toList());
        if(null != trialBalancesOfAMerchant && trialBalancesOfAMerchant.size()>0 ){
            return trialBalancesOfAMerchant.get(0);
        }
        return null;
    }

}
