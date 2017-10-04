package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.vo.InstallmentPerCalendarPeriod;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "PurchaseCostAccountView")
public class PurchaseCostAccountView {
    @Id
    private Integer year;
    private double startAmount;
    private double currentAmount;
    private List<InstallmentPerCalendarPeriod> calendar;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public PurchaseCostAccountView(Integer year, double startAmount,List<InstallmentPerCalendarPeriod> calendar, double currentAmount, LocalDateTime startDate, LocalDateTime endDate) {
        this.year = year;
        this.startAmount = startAmount;
        this.calendar=calendar;
        this.currentAmount = currentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void debit(double amount) {
        this.currentAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
    }

    public void credit(double amount) {
        this.currentAmount += amount;
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, currentAmount));
    }

    public void debit(double amount, LocalDate startDate, LocalDate endDate) {
        this.currentAmount -= amount;
        for(InstallmentPerCalendarPeriod installment: this.calendar){
            if(installment.getStartDate().equals(startDate) && installment.getEndDate().equals(endDate)){
                installment.deductAmount(amount);
                break;
            }
        }
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
    }

    public void debit(double amount, LocalDate transactionDate) {
        this.currentAmount -= amount;
        for(InstallmentPerCalendarPeriod installment: this.calendar){
            if((transactionDate.equals(installment.getStartDate())|| transactionDate.isAfter(installment.getStartDate())) && transactionDate.isBefore(installment.getEndDate())){
                installment.deductAmount(amount);
                break;
            }
        }
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
    }

    public void credit(double amount,LocalDate startDate, LocalDate endDate) {
        this.currentAmount += amount;
        for(InstallmentPerCalendarPeriod installment: this.calendar){
            if(installment.getStartDate().equals(startDate) && installment.getEndDate().equals(endDate)){
                installment.addAmount(amount);
            }
        }
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, currentAmount));
    }

    public List<InstallmentPerCalendarPeriod> getCalendar() {
        return calendar;
    }
}
