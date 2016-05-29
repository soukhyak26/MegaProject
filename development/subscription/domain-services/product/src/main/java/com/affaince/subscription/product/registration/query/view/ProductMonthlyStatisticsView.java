package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.product.registration.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.registration.vo.ProductMonthlyVersionId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Document(collection = "ProductMonthlyStatisticsView")
public class ProductMonthlyStatisticsView {
    @Id
    private final ProductMonthlyVersionId productMonthlyVersionId;
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;
    private long productSubscriptionCount;
    private double subscribedProductRevenue;
    private double subscribedProductNetProfit;
    private double fixedOperatingExpense;
    private double variableOperatingExpense;


    public ProductMonthlyStatisticsView(String productId, YearMonth monthOfYear) {
        this.productMonthlyVersionId= new ProductMonthlyVersionId(productId,monthOfYear);
        taggedPriceVersions= new TreeSet<>();
    }

    public ProductMonthlyStatisticsView(String productId, YearMonth monthOfYear, long productSubscriptionCount, double purchasePricePerUnit,double MRP,double subscribedProductRevenue, double subscribedProductNetProfit, double fixedOperatingExpense, double variableOperatingExpense) {
        this.productMonthlyVersionId= new ProductMonthlyVersionId(productId,monthOfYear);
        this.productSubscriptionCount = productSubscriptionCount;
        this.subscribedProductRevenue = subscribedProductRevenue;
        this.subscribedProductNetProfit = subscribedProductNetProfit;
        this.fixedOperatingExpense= fixedOperatingExpense;
        this.variableOperatingExpense=variableOperatingExpense;
        LocalDate firstDayOfMonth=monthOfYear.toLocalDate(1).dayOfMonth().withMinimumValue();
        LocalDate lastDayOfMonth= monthOfYear.toLocalDate(1).dayOfMonth().withMaximumValue();

        if(null != taggedPriceVersions && taggedPriceVersions.size()>0){
            taggedPriceVersions.add(new PriceTaggedWithProduct(purchasePricePerUnit,MRP,firstDayOfMonth,lastDayOfMonth));
        }else{
            taggedPriceVersions=new TreeSet<>();
            taggedPriceVersions.add(new PriceTaggedWithProduct(purchasePricePerUnit,MRP,firstDayOfMonth,lastDayOfMonth));
        }

    }

    public ProductMonthlyStatisticsView(ProductMonthlyVersionId productMonthlyVersionId, long productSubscriptionCount, double subscribedProductRevenue, double subscribedProductNetProfit) {
        this.productMonthlyVersionId = productMonthlyVersionId;
        this.productSubscriptionCount = productSubscriptionCount;
        this.subscribedProductRevenue = subscribedProductRevenue;
        this.subscribedProductNetProfit = subscribedProductNetProfit;
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

    public SortedSet<PriceTaggedWithProduct> getTaggedPriceVersions() {
        return taggedPriceVersions;
    }

    public void setTaggedPriceVersions(SortedSet<PriceTaggedWithProduct> taggedPriceVersions) {
        this.taggedPriceVersions = taggedPriceVersions;
    }
}
