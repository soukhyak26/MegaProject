package com.affiance.business.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BusinessAccountView")
public class BusinessAccountView {

    //TODO: Check if business account would be singleton - and hence only one id (as of now it will use year of provision date as id)
    @Id
    private String id;
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dateForProvision;

    private double defaultPercentFixedExpensePerUnitPrice =1.0;
    private double defaultPercentVariableExpensePerUnitPrice=1.0;

    public BusinessAccountView(String id, String merchantId,LocalDate startDate,LocalDate endDate,LocalDate dateForProvision) {
        this.id = id;
        this.merchantId=merchantId;
        this.startDate=startDate;
        this.endDate = endDate;
        this.dateForProvision = dateForProvision;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateForProvision() {
        return dateForProvision;
    }

    public void setDateForProvision(LocalDate dateForProvision) {
        this.dateForProvision = dateForProvision;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getDefaultPercentFixedExpensePerUnitPrice() {
        return defaultPercentFixedExpensePerUnitPrice;
    }

    public void setDefaultPercentFixedExpensePerUnitPrice(double defaultPercentFixedExpensePerUnitPrice) {
        this.defaultPercentFixedExpensePerUnitPrice = defaultPercentFixedExpensePerUnitPrice;
    }

    public double getDefaultPercentVariableExpensePerUnitPrice() {
        return defaultPercentVariableExpensePerUnitPrice;
    }

    public void setDefaultPercentVariableExpensePerUnitPrice(double defaultPercentVariableExpensePerUnitPrice) {
        this.defaultPercentVariableExpensePerUnitPrice = defaultPercentVariableExpensePerUnitPrice;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "BusinessAccountView{" +
                "id='" + id + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dateForProvision=" + dateForProvision +
                '}';
    }
}
