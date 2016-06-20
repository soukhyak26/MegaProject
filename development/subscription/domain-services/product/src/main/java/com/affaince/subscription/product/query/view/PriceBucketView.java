package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandark on 28-01-2016.
 */

@Document
public class PriceBucketView  implements Comparable<PriceBucketView>{
    @Id
    private ProductVersionId productVersionId;
    private PriceTaggedWithProduct taggedPriceVersion;
    private LocalDate toDate;
    private double offeredPricePerUnit;
    private double percentDiscountPerUnit;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;
    private EntityStatus entityStatus;
    private double totalProfit;
    private double slope;

    public String getProductId() {
        return productVersionId.getProductId();
    }

    public void setProductId(String productId) {
        this.productVersionId.setProductId(productId);
    }

    public LocalDate getFromDate() {
        return this.productVersionId.getFromDate();
    }

    public void setFromDate(LocalDate fromDate) {
        this.productVersionId.setFromDate(fromDate);
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public double getOfferedPricePerUnit() {
        return this.offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
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

    public void setProductVersionId(ProductVersionId productVersionId) {
        this.productVersionId = productVersionId;
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

    public double getPercentDiscountPerUnit() {
        return percentDiscountPerUnit;
    }

    public void setPercentDiscountPerUnit(double percentDiscountPerUnit) {
        this.percentDiscountPerUnit = percentDiscountPerUnit;
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

    public static PriceBucketView getLatestPriceBucket(List<PriceBucketView> activePriceBuckets) {
        PriceBucketView latestPriceBucketView = null;
        LocalDate max = activePriceBuckets.get(0).getFromDate();
        for (PriceBucketView priceBucketView : activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(max) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }

    @Override
    public int compareTo(PriceBucketView o) {
        return this.getFromDate().compareTo(o.getFromDate());
    }
}
