package com.affaince.subscription.business.domain;

import com.affaince.subscription.business.event.ProfitCreditedEvent;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
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

    public void addProfitToProfitAccount(Integer businessAccountId,String productId,double amountToBeCredited){
        apply( new ProfitCreditedEvent(businessAccountId,productId,amountToBeCredited, SysDate.now()));
    }

    @EventSourcingHandler
    public void on(ProfitCreditedEvent event){
        credit(event.getAmountToBeCredited());
    }
    public void credit(double profitAmount){
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
