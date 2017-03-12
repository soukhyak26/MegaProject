package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.deserializer.EntityStatusDeserializer;
import com.affaince.subscription.common.deserializer.LocalDateTimeDeserializer;
import com.affaince.subscription.common.deserializer.ProductPricingCategoryDeserializer;
import com.affaince.subscription.common.serializer.LocalDateTimeSerializer;
import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 19-02-2017.
 */
public class PriceBucketSample {
    private String productId;
    private String priceBucketId;
    private ProductPricingCategory productPricingCategory;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfExistingSubscriptions;
    private long numberOfDeliveredSubscriptions;
    private double expectedProfit;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private EntityStatus entityStatus;
    private double registeredPurchaseCostOfDeliveredUnits;
    private double registeredRevenue;
    private double registeredProfit;
    private double slope;
    private double offeredPriceOrPercentDiscountPerUnit;
    private PriceTaggedWithProduct taggedPriceVersion;
    public PriceBucketSample() {
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getPriceBucketId() {
        return priceBucketId;
    }
    public void setPriceBucketId(String priceBucketId) {
        this.priceBucketId = priceBucketId;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    @JsonDeserialize(using = ProductPricingCategoryDeserializer.class)
    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public void setNumberOfNewSubscriptions(long numberOfNewSubscriptions) {
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public void setNumberOfChurnedSubscriptions(long numberOfChurnedSubscriptions) {
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }

    public long getNumberOfExistingSubscriptions() {
        return numberOfExistingSubscriptions;
    }
    public void setNumberOfExistingSubscriptions(long numberOfExistingSubscriptions) {
        this.numberOfExistingSubscriptions = numberOfExistingSubscriptions;
    }
    public long getNumberOfDeliveredSubscriptions() {
        return numberOfDeliveredSubscriptions;
    }
    public void setNumberOfDeliveredSubscriptions(long numberOfDeliveredSubscriptions) {
        this.numberOfDeliveredSubscriptions = numberOfDeliveredSubscriptions;
    }
    public double getExpectedProfit() {
        return expectedProfit;
    }
    public void setExpectedProfit(double expectedProfit) {
        this.expectedProfit = expectedProfit;
    }
    @JsonSerialize (using = LocalDateTimeSerializer.class)
    public LocalDateTime getFromDate() {
        return fromDate;
    }
    @JsonSerialize (using = LocalDateTimeSerializer.class)
    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }
    @JsonSerialize (using = LocalDateTimeSerializer.class)
    public LocalDateTime getToDate() {
        return toDate;
    }
    @JsonDeserialize (using = LocalDateTimeDeserializer.class)
    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }


    @JsonDeserialize(using = EntityStatusDeserializer.class)
    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }
    public double getRegisteredPurchaseCostOfDeliveredUnits() {
        return registeredPurchaseCostOfDeliveredUnits;
    }
    public void setRegisteredPurchaseCostOfDeliveredUnits(double registeredPurchaseCostOfDeliveredUnits) {
        this.registeredPurchaseCostOfDeliveredUnits = registeredPurchaseCostOfDeliveredUnits;
    }
    public double getRegisteredRevenue() {
        return registeredRevenue;
    }
    public void setRegisteredRevenue(double registeredRevenue) {
        this.registeredRevenue = registeredRevenue;
    }
    public double getRegisteredProfit() {
        return registeredProfit;
    }
    public void setRegisteredProfit(double registeredProfit) {
        this.registeredProfit = registeredProfit;
    }
    public double getSlope() {
        return slope;
    }
    public void setSlope(double slope) {
        this.slope = slope;
    }
    public void setOfferedPriceOrPercentDiscountPerUnit(double offeredPriceOrPercentDiscountPerUnit) {
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
    }
    public double getOfferedPriceOrPercentDiscountPerUnit() {
        return offeredPriceOrPercentDiscountPerUnit;
    }
    public void setTaggedPriceVersion(PriceTaggedWithProduct taggedPriceVersion) {
        this.taggedPriceVersion = taggedPriceVersion;
    }
    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }


    @Override
    public String toString() {
        return "PriceBucketSample{" +
                "productId='" + productId + '\'' +
                ", priceBucketId='" + priceBucketId + '\'' +
                ", productPricingCategory=" + productPricingCategory +
                ", numberOfNewSubscriptions=" + numberOfNewSubscriptions +
                ", numberOfChurnedSubscriptions=" + numberOfChurnedSubscriptions +
                ", numberOfExistingSubscriptions=" + numberOfExistingSubscriptions +
                ", numberOfDeliveredSubscriptions=" + numberOfDeliveredSubscriptions +
                ", expectedProfit=" + expectedProfit +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", entityStatus=" + entityStatus +
                ", registeredPurchaseCostOfDeliveredUnits=" + registeredPurchaseCostOfDeliveredUnits +
                ", registeredRevenue=" + registeredRevenue +
                ", registeredProfit=" + registeredProfit +
                ", slope=" + slope +
                ", offeredPriceOrPercentDiscountPerUnit=" + offeredPriceOrPercentDiscountPerUnit +
                ", taggedPriceVersion=" + taggedPriceVersion +

                '}';
    }
}
