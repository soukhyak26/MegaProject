package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.ProvisionCalendar;
import com.verifier.domains.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

import java.util.Set;

public abstract class ProvisionAccountView {
    @Id
    private String businessAccountId;
    private double provisionAmount;
    //private ProvisionCalendar provisionCalendar;
    private LocalDate startDate;
    private LocalDate endDate;
    public ProvisionAccountView(String businessAccountId, double provisionAmount, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.provisionAmount = provisionAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        //this.provisionCalendar=new ProvisionCalendar(startDate,endDate);
    }
    public ProvisionAccountView(){}
    public ProvisionAccountView(String businessAccountId, double provisionAmount, Set<ProvisionSegment> distributionCalendar, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.provisionAmount = provisionAmount;
        this.startDate = startDate;
        this.endDate = endDate;
/*
        this.provisionCalendar=new ProvisionCalendar(startDate,endDate);
        this.provisionCalendar.setInstallmentCalendar(distributionCalendar);
*/
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


    public void debit(double amount, LocalDate startDate, LocalDate endDate) {
        this.provisionAmount -= amount;
        //this.provisionCalendar.deductProvisionForAPeriod(amount,startDate,endDate);
    }
    public void credit(double amount,LocalDate startDate, LocalDate endDate) {
        try {
            this.provisionAmount += amount;
            //this.provisionCalendar.addProvisionForAPeriod(amount, startDate, endDate);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void registerProvision(String id, LocalDate startDate, LocalDate endDate, double provision) {
        this.startDate = startDate;
        this.endDate = endDate;
        //Manual provision should be directly registered in provisionAmount
        this.provisionAmount = provision;
        //this.provisionCalendar.setInstallmentCalendar(provisionDistributionCalendar);
        //distributeProvision(id,startDate,endDate,provision);
    }
}
