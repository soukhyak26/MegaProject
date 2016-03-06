package com.affaince.subscription.pricing.query.view;

import com.affaince.subscription.common.vo.ProductMonthlyVersionId;
import org.joda.time.YearMonth;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Document(collection = "ProductStatisticsView")
public class ProductStatisticsView {
    @Id
    private final ProductMonthlyVersionId productMonthlyVersionId;
    private double purchasePrice;
    private double MRP;
    private long productSubscriptionCount;
    private double subscribedProductRevenue;
    private double subscribedProductNetProfit;
    private double fixedOperatingExpense;
    private double variableOperatingExpense;


    public ProductStatisticsView(String productId, YearMonth monthOfYear) {
        this.productMonthlyVersionId= new ProductMonthlyVersionId(productId,monthOfYear);
    }

    public ProductStatisticsView(ProductMonthlyVersionId productMonthlyVersionId, double purchasePrice, double MRP, long productSubscriptionCount, double subscribedProductRevenue, double subscribedProductNetProfit, double fixedOperatingExpense, double variableOperatingExpense) {
        this.productMonthlyVersionId = productMonthlyVersionId;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.productSubscriptionCount = productSubscriptionCount;
        this.subscribedProductRevenue = subscribedProductRevenue;
        this.subscribedProductNetProfit = subscribedProductNetProfit;
        this.fixedOperatingExpense = fixedOperatingExpense;
        this.variableOperatingExpense = variableOperatingExpense;
    }

    public long getProductSubscriptionCount() {
        return productSubscriptionCount;
    }

    public void setProductSubscriptionCount(long productSubscriptionCount) {
        this.productSubscriptionCount = productSubscriptionCount;
    }

    public double getSubscribedProductRevenue() {
        return subscribedProductRevenue;
    }

    public void setSubscribedProductRevenue(double subscribedProductRevenue) {
        this.subscribedProductRevenue = subscribedProductRevenue;
    }

    public double getSubscribedProductNetProfit() {
        return subscribedProductNetProfit;
    }

    public void setSubscribedProductNetProfit(double subscribedProductNetProfit) {
        this.subscribedProductNetProfit = subscribedProductNetProfit;
    }

    public ProductMonthlyVersionId getProductMonthlyVersionId() {
        return this.productMonthlyVersionId;
    }

    public double getFixedOperatingExpense() {
        return this.fixedOperatingExpense;
    }

    public void setFixedOperatingExpense(double fixedOperatingExpense) {
        this.fixedOperatingExpense = fixedOperatingExpense;
    }

    public double getVariableOperatingExpense() {
        return this.variableOperatingExpense;
    }

    public void setVariableOperatingExpense(double variableOperatingExpense) {
        this.variableOperatingExpense = variableOperatingExpense;
    }

    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMRP() {
        return this.MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ProductStatisticsView that = (ProductStatisticsView) o;

        return this.getProductMonthlyVersionId().equals(that.getProductMonthlyVersionId());

    }

    @Override
    public int hashCode() {
        return this.getProductMonthlyVersionId().hashCode();
    }
}