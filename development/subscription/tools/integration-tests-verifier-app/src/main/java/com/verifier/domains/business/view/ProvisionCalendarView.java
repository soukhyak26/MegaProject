package com.verifier.domains.business.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
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
    private ForecastContentStatus forecastContentStatus;

    public ProvisionCalendarView(){
        this.provisionCalendar=new ProvisionCalendar();
        this.forecastContentStatus=ForecastContentStatus.ACTIVE;
    }
    public ProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionCalendar=new ProvisionCalendar(startDate,endDate);
        this.forecastContentStatus=ForecastContentStatus.ACTIVE;
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

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
