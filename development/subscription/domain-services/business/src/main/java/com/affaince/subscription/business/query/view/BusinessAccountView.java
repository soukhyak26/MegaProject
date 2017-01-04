package com.affaince.subscription.business.query.view;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by anayonkar on 29/4/16.
 */
@Document(collection = "BusinessAccountView")
public class BusinessAccountView {

    //TODO: Check if business account would be singleton - and hence only one id (as of now it will use year of provision date as id)
    @Id
    private Integer id;
    private LocalDateTime dateForProvision;
    private LocalDateTime endDate;
    private double defaultPercentFixedExpensePerUnitPrice =1.0;
    private double defaultPercentVariableExpensePerUnitPrice=1.0;

    public BusinessAccountView(Integer id, LocalDateTime dateForProvision, LocalDateTime endDate, double defaultPercentFixedExpensePerUnitPrice, double defaultPercentVariableExpensePerUnitPrice) {
        this.id = id;
        this.dateForProvision = dateForProvision;
        this.endDate = endDate;
        this.defaultPercentFixedExpensePerUnitPrice = defaultPercentFixedExpensePerUnitPrice;
        this.defaultPercentVariableExpensePerUnitPrice = defaultPercentVariableExpensePerUnitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateForProvision() {
        return dateForProvision;
    }

    public void setDateForProvision(LocalDateTime dateForProvision) {
        this.dateForProvision = dateForProvision;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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
}
