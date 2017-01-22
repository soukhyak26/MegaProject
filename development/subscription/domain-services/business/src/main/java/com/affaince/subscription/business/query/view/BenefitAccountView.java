package com.affaince.subscription.business.query.view;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "BenefitAccountView")
public class BenefitAccountView {
    @Id
    private Integer year;
    private double provisonAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public BenefitAccountView(Integer year, double provisonAmount, LocalDateTime startDate, LocalDateTime endDate) {
        this.year = year;
        this.provisonAmount = provisonAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public double getProvisonAmount() {
        return provisonAmount;
    }

    public void setProvisonAmount(double provisonAmount) {
        this.provisonAmount = provisonAmount;
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

}
