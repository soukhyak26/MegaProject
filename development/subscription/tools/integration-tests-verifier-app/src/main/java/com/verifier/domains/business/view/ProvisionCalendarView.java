package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.ProvisionCalendar;
import com.verifier.domains.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

public class ProvisionCalendarView {
    @Id
    private String businessAccountId;
    private ProvisionCalendar provisionCalendar;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProvisionCalendarView(){
        this.provisionCalendar=new ProvisionCalendar();
    }
    public ProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionCalendar=new ProvisionCalendar(startDate,endDate);
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

    public void distributeProvision(String id, LocalDate startDate, LocalDate endDate, double provision) {
        provisionCalendar.distributeProvisionAcrossYear(provision, startDate, endDate);
    }


    public void addProvisionSegment(ProvisionSegment matchingProvisionSegment, LocalDate startDate, LocalDate endDate){
        this.provisionCalendar.addProvisionSegment(matchingProvisionSegment,startDate,endDate);
    }


}
