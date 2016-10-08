package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandark on 28-01-2016.
 */

@Document
public class RecommendedPriceBucketView implements Comparable<RecommendedPriceBucketView> {
    @Id
    private final ProductVersionId productVersionId;
    private PriceTaggedWithProduct taggedPriceVersion;
    private LocalDateTime toDate;
    private double offeredPriceOrPercentDiscountPerUnit;
    //private double percentDiscountPerUnit;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;
    private EntityStatus entityStatus;
    private double totalProfit;
    private double slope;

    public RecommendedPriceBucketView(ProductVersionId productVersionId) {
        this.productVersionId = productVersionId;
    }

    public static RecommendedPriceBucketView getLatestPriceBucket(List<RecommendedPriceBucketView> activePriceBuckets) {
        RecommendedPriceBucketView latestPriceBucketView = null;
        LocalDateTime max = activePriceBuckets.get(0).getFromDate();
        for (RecommendedPriceBucketView priceBucketView : activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(max) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }

    public String getProductId() {
        return productVersionId.getProductId();
    }

    public void setProductId(String productId) {
        this.productVersionId.setProductId(productId);
    }

    public LocalDateTime getFromDate() {
        return this.productVersionId.getFromDate();
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.productVersionId.setFromDate(fromDate);
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public double getOfferedPriceOrPercentDiscountPerUnit() {
        return this.offeredPriceOrPercentDiscountPerUnit;
    }

    public void setOfferedPriceOrPercentDiscountPerUnit(double offeredPriceOrPercentDiscountPerUnit) {
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
    }

    public long getNumberOfNewCustomersAssociatedWithAPrice() {
        return this.numberOfNewCustomersAssociatedWithAPrice;
    }

    public void setNumberOfNewCustomersAssociatedWithAPrice(long numberOfNewCustomersAssociatedWithAPrice) {
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
    }

    public long getNumberOfChurnedCustomersAssociatedWithAPrice() {
        return this.numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public void setNumberOfChurnedCustomersAssociatedWithAPrice(long numberOfChurnedCustomersAssociatedWithAPrice) {
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public long getNumberOfExistingCustomersAssociatedWithAPrice() {
        return this.numberOfExistingCustomersAssociatedWithAPrice;
    }

    public void setNumberOfExistingCustomersAssociatedWithAPrice(long numberOfExistingCustomersAssociatedWithAPrice) {
        this.numberOfExistingCustomersAssociatedWithAPrice = numberOfExistingCustomersAssociatedWithAPrice;
    }

    public ProductVersionId getProductVersionId() {
        return this.productVersionId;
    }

    public EntityStatus getEntityStatus() {
        return this.entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public void setTaggedPriceVersion(PriceTaggedWithProduct taggedPriceVersion) {
        this.taggedPriceVersion = taggedPriceVersion;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    @Override
    public int compareTo(RecommendedPriceBucketView o) {
        return this.getFromDate().compareTo(o.getFromDate());
    }

    public double recalculateOfferedPriceBasedOnActualDemand() {
        if (slope != 0) {
            return (taggedPriceVersion.getMRP() + slope * numberOfExistingCustomersAssociatedWithAPrice);
        } else {
            return offeredPriceOrPercentDiscountPerUnit;
        }

    }
}
