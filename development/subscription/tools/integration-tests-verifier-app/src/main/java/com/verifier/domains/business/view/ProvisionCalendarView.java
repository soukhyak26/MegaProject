package com.verifier.domains.business.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.business.vo.ProvisionCalendar;
import com.verifier.domains.business.vo.ProvisionCalendarVersionId;
import com.verifier.domains.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

public class ProvisionCalendarView {
    @Id
    private ProvisionCalendarVersionId provisionCalendarVersionId;
    private double  provisionAmount;
    private double expectedAmount;
    private ForecastContentStatus forecastContentStatus;

    public ProvisionCalendarView(){
        this.provisionCalendarVersionId =  new ProvisionCalendarVersionId();
        this.forecastContentStatus =ForecastContentStatus.ACTIVE;
    }
    public ProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        this.provisionCalendarVersionId =  new ProvisionCalendarVersionId(businessAccountId,startDate,endDate);
        this.forecastContentStatus =ForecastContentStatus.ACTIVE;
    }

    public ProvisionCalendarVersionId getProvisionCalendarVersionId() {
        return provisionCalendarVersionId;
    }

    public double getProvisionAmount() {
        return provisionAmount;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

/*
    public void distributeProvision(String id, LocalDate startDate, LocalDate endDate, double provision) {
        provisionCalendar.distributeProvisionAcrossYear(provision, startDate, endDate);
    }
*/


    public void addProvisionSegment(ProvisionSegment matchingProvisionSegment, LocalDate startDate, LocalDate endDate){
        this.provisionAmount= matchingProvisionSegment.getProvisionedAmount();
        this.expectedAmount=matchingProvisionSegment.getExpectedAmount();
    }

    public void setProvisionAmount(double provisionAmount) {
        this.provisionAmount = provisionAmount;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
