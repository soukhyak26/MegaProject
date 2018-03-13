package com.affiance.business.view;

import com.affiance.business.vo.ProvisionCalendar;
import com.affiance.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "BenefitAccountView")
public class BenefitAccountView {
    @Id
    private String year;
    private double provisonAmount;
    private ProvisionCalendar provisionCalendar;
    private LocalDate startDate;
    private LocalDate endDate;

    public BenefitAccountView(String year, double provisonAmount, List<ProvisionSegment> distributionCalendar, LocalDate startDate, LocalDate endDate) {
        this.year = year;
        this.provisonAmount = provisonAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionCalendar=new ProvisionCalendar(startDate,endDate);
        this.provisionCalendar.setInstallmentCalendar(distributionCalendar);

    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getProvisonAmount() {
        return provisonAmount;
    }

    public void setProvisonAmount(double provisonAmount) {
        this.provisonAmount = provisonAmount;
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

    public ProvisionCalendar getProvisionCalendar() {
        return provisionCalendar;
    }
}
