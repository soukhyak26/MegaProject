package com.affaince.subscription.business.command.domain;

import org.apache.avro.generic.GenericData;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 30-01-2017.
 */
public class ProfitAccount extends AbstractAnnotatedEntity {
    private double accumulatedProfitAmount;
    private List<ProfitDebitAccount> excessProfitsToDebit;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProfitAccount(LocalDate startDate,LocalDate endDate){
        this.startDate=startDate;
        this.endDate = endDate;
        this.excessProfitsToDebit=new ArrayList<ProfitDebitAccount>();
    }

    public double getAccumulatedProfitAmount() {
        double excessProfitToDebitAmount=0;
        for(ProfitDebitAccount profitDebitAccount: excessProfitsToDebit){
            excessProfitToDebitAmount +=profitDebitAccount.getAmountToBeDebited();
        }
        return (accumulatedProfitAmount - excessProfitToDebitAmount);
    }

    public void addProfitToProfitAccount(double profitAmount){
        this.accumulatedProfitAmount +=profitAmount;
    }
    public List<ProfitDebitAccount> getExcessProfitsToDebit() {
        return excessProfitsToDebit;
    }

    public void addToExcessProfitToDebit(ProfitDebitAccount profitDebitAccount){
        this.excessProfitsToDebit.add(profitDebitAccount);
    }
    public void removeExcessProfitToDebit(ProfitDebitAccount profitDebitAccount){
        this.excessProfitsToDebit.remove(profitDebitAccount);
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
