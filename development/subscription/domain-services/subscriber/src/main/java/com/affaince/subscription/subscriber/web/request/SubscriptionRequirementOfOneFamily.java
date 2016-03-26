package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rsavaliya on 26/3/16.
 */
public class SubscriptionRequirementOfOneFamily {

    private String category;
    private String subCategory;
    private String productName;
    private int productWeightInGrms;
    private double monthlyConsumption;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductWeightInGrms() {
        return productWeightInGrms;
    }

    public void setProductWeightInGrms(int productWeightInGrms) {
        this.productWeightInGrms = productWeightInGrms;
    }

    public double getMonthlyConsumption() {
        return monthlyConsumption;
    }

    public void setMonthlyConsumption(double monthlyConsumption) {
        this.monthlyConsumption = monthlyConsumption;
    }
}
