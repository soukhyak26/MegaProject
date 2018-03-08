package com.test.verifier.domains.business.view;

import com.affaince.subscription.date.SysDate;
import com.test.verifier.domains.business.exception.NotEnoughProvisionException;
import com.test.verifier.domains.business.vo.ProvisionCalendar;
import com.test.verifier.domains.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "PurchaseCostAccountView")
public class PurchaseCostAccountView {
    @Id
    private String businessAccountId;
    private double provisionAmount;
    private ProvisionCalendar provisionCalendar;
    private LocalDate startDate;
    private LocalDate endDate;

    public PurchaseCostAccountView(String businessAccountId, double provisionAmount, List<ProvisionSegment> distributionCalendar, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.provisionAmount = provisionAmount;
        this.provisionCalendar=provisionCalendar;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionCalendar=new ProvisionCalendar(startDate,endDate);
        this.provisionCalendar.setInstallmentCalendar(distributionCalendar);
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(String businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public double getProvisionAmount() {
        return provisionAmount;
    }

    public void setProvisionAmount(double provisionAmount) {
        this.provisionAmount = provisionAmount;
    }

    public ProvisionCalendar getProvisionCalendar() {
        return provisionCalendar;
    }

    public void setProvisionCalendar(ProvisionCalendar provisionCalendar) {
        this.provisionCalendar = provisionCalendar;
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

    public void debit(double amount) throws NotEnoughProvisionException {
        this.provisionAmount -= amount;
        final LocalDate currentDate = SysDate.now();
        debit(amount,currentDate);
    }

    public void credit(double amount) {
        this.provisionAmount += amount;
        final LocalDate currentDate = SysDate.now();
        this.provisionCalendar.addProvisionForAPeriod(amount,currentDate);
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, currentAmount));
    }

    public void debit(double amount, LocalDate startDate, LocalDate endDate) throws NotEnoughProvisionException{
        this.provisionAmount -= amount;
        this.provisionCalendar.deductProvisionForAPeriod(amount,startDate,endDate);
    }

    public void debit(double amount, LocalDate transactionDate) throws NotEnoughProvisionException {
        this.provisionAmount -= amount;
        this.provisionCalendar.deductProvisionForAPeriod(amount,transactionDate);
    }

    public void credit(double amount,LocalDate startDate, LocalDate endDate) {
        this.provisionAmount += amount;
        this.provisionCalendar.addProvisionForAPeriod(amount,startDate,endDate);
    }


}
