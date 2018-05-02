package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.ProvisionCalendar;
import com.verifier.domains.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "TaxesAccountView")
public class TaxesAccountView {
    @Id
    private String businessAccountId;
    private double provisionAmount;
    private ProvisionCalendar provisionCalendar;
    private LocalDate startDate;
    private LocalDate endDate;

    public TaxesAccountView(){}
    public TaxesAccountView(String businessAccountId, double provisionAmount, List<ProvisionSegment> distributionCalendar, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.provisionAmount = provisionAmount;
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

    public void debit(double amount) {
        this.provisionAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, expectedProvisionAmount));
    }

    public void credit(double amount) {
        this.provisionAmount += amount;
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, expectedProvisionAmount));
    }

}
