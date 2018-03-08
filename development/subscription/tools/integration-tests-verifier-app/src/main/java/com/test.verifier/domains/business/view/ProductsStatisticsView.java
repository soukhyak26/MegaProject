package com.test.verifier.domains.business.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Document(collection = "ProductsStatisticsView")
public class ProductsStatisticsView {
    @Id
    private String productId;
    private long productSubscriptionCount;
    private double subscribedProductRevenue;
    private double subscribedProductNetProfit;


    public ProductsStatisticsView() {
    }

    public ProductsStatisticsView(String productId, long productSubscriptionCount, double subscribedProductRevenue, double subscribedProductNetProfit) {
        this.productId = productId;
        this.productSubscriptionCount = productSubscriptionCount;
        this.subscribedProductRevenue = subscribedProductRevenue;
        this.subscribedProductNetProfit = subscribedProductNetProfit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
}
