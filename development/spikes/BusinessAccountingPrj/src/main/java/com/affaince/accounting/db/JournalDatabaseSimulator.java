package com.affaince.accounting.db;

import com.affaince.accounting.journal.entity.Journal;
import com.affaince.accounting.journal.entity.JournalRecord;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JournalDatabaseSimulator {
   private List<Journal> journals;
    @Autowired
   public JournalDatabaseSimulator(){
       journals = new ArrayList<>();
   }
   public Journal searchBYMerchantIdAndPeriod(String merchantId, LocalDateTime startDate, LocalDateTime endDate){
        return journals.stream().filter(jr->jr.getMerchantId().equals(merchantId) && jr.getStartDate().isEqual(startDate) && jr.getEndDate().isEqual(endDate)).findAny().orElse(null);
   }
    public Journal searchBYMerchantIdAndPostingDate(String merchantId, LocalDateTime postingDate){
        return journals.stream().filter(jr->jr.getMerchantId().equals(merchantId) &&
                (jr.getStartDate().isEqual(postingDate)||jr.getStartDate().isBefore(postingDate)) &&
                jr.getEndDate().isAfter(postingDate)).findAny().orElse(null);
    }

   public void addJournal(Journal journal){
        journals.add(journal);
   }

}
