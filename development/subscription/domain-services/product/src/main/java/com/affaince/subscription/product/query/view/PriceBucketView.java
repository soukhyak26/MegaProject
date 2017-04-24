package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.DeliveredSubscriptionsAgainstTaggedPrice;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mandark on 28-01-2016.
 */

@Document (collection = "PriceBucketView")
public class PriceBucketView  implements Comparable<PriceBucketView>{
    @Id
    private final ProductwisePriceBucketId productwisePriceBucketId;
    private PriceTaggedWithProduct taggedPriceVersion;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private double offeredPriceOrPercentDiscountPerUnit;
    //private double percentDiscountPerUnit;
    private long numberOfNewSubscriptionsAssociatedWithAPrice;
    private long numberOfChurnedSubscriptionsAssociatedWithAPrice;
    private long numberOfExistingSubscriptionsAssociatedWithAPrice;
    private EntityStatus entityStatus;
    private double expectedPurchaseCost;
    private double expectedRevenue;
    private double expectedProfit;

    protected Set<DeliveredSubscriptionsAgainstTaggedPrice> deliveredSubscriptionsAgainstTaggedPrices;
    protected Map<LocalDate,Double> registeredProfit;
    protected Map<LocalDate,Double> registeredPurchaseCostOfDeliveredUnits;
    protected Map<LocalDate,Double> registeredRevenue;

    private double slope;

    public PriceBucketView(ProductwisePriceBucketId productwisePriceBucketId) {
        this.productwisePriceBucketId = productwisePriceBucketId;
    }

    public static PriceBucketView getLatestPriceBucket(List<PriceBucketView> activePriceBuckets) {
        PriceBucketView latestPriceBucketView = null;
        LocalDateTime max = activePriceBuckets.get(0).getFromDate();
        for (PriceBucketView priceBucketView : activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(max) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }

    public String getProductId() {
        return productwisePriceBucketId.getProductId();
    }

    public ProductwisePriceBucketId getProductwisePriceBucketId() {
        return productwisePriceBucketId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
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

    public long getNumberOfNewSubscriptionsAssociatedWithAPrice() {
        return this.numberOfNewSubscriptionsAssociatedWithAPrice;
    }

    public void setNumberOfNewSubscriptionsAssociatedWithAPrice(long numberOfNewSubscriptionsAssociatedWithAPrice) {
        this.numberOfNewSubscriptionsAssociatedWithAPrice = numberOfNewSubscriptionsAssociatedWithAPrice;
    }

    public long getNumberOfChurnedSubscriptionsAssociatedWithAPrice() {
        return this.numberOfChurnedSubscriptionsAssociatedWithAPrice;
    }

    public void setNumberOfChurnedSubscriptionsAssociatedWithAPrice(long numberOfChurnedSubscriptionsAssociatedWithAPrice) {
        this.numberOfChurnedSubscriptionsAssociatedWithAPrice = numberOfChurnedSubscriptionsAssociatedWithAPrice;
    }

    public long getNumberOfExistingSubscriptionsAssociatedWithAPrice() {
        return this.numberOfExistingSubscriptionsAssociatedWithAPrice;
    }

    public void setNumberOfExistingSubscriptionsAssociatedWithAPrice(long numberOfExistingSubscriptionsAssociatedWithAPrice) {
        this.numberOfExistingSubscriptionsAssociatedWithAPrice = numberOfExistingSubscriptionsAssociatedWithAPrice;
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


    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public double getExpectedPurchaseCost() {
        return expectedPurchaseCost;
    }

    public void setExpectedPurchaseCost(double expectedPurchaseCost) {
        this.expectedPurchaseCost = expectedPurchaseCost;
    }

    public void addToExpectedPurchaseCost(double incrementalPurchaseCost){
        this.expectedPurchaseCost += incrementalPurchaseCost;
    }
    public void deductFromExpectedPurchaseCost(double deductiblePurchaseCost){
        this.expectedPurchaseCost -= deductiblePurchaseCost;
    }
    public double getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(double expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }

    public void addToExpectedRevenue(double incrementalRevenue){
        this.expectedRevenue +=incrementalRevenue;
    }
    public void deductFromExpectedRevenue(double deductibleRevenue){
        this.expectedRevenue -= deductibleRevenue;
    }
    public double getExpectedProfit() {
        return expectedProfit;
    }

    public void setExpectedProfit(double expectedProfit) {
        this.expectedProfit = expectedProfit;
    }

    public void addToExpectedProfit(double incrementalProfit){
        this.expectedProfit +=incrementalProfit;
    }
    public void deductFromExpectedProfit(double deductibleProfit){
        this.expectedProfit -= deductibleProfit;
    }
    @Override
    public int compareTo(PriceBucketView o) {
        return this.getFromDate().compareTo(o.getFromDate());
    }

    public double recalculateOfferedPriceBasedOnActualDemand(){
        if(slope!= 0){
            return ( taggedPriceVersion.getMRP()+ slope* numberOfExistingSubscriptionsAssociatedWithAPrice);
        }else{
            return offeredPriceOrPercentDiscountPerUnit;
        }
    }

    public void addToNewSubscriptions(int subscriptionCount){
        this.numberOfNewSubscriptionsAssociatedWithAPrice=this.numberOfNewSubscriptionsAssociatedWithAPrice+subscriptionCount;
        this.numberOfExistingSubscriptionsAssociatedWithAPrice +=subscriptionCount;
    }

    public void addToChurnedSubscriptions( int subscriptionCount){
        this.numberOfChurnedSubscriptionsAssociatedWithAPrice=this.numberOfChurnedSubscriptionsAssociatedWithAPrice + subscriptionCount;
        this.numberOfExistingSubscriptionsAssociatedWithAPrice -=subscriptionCount;
    }

    public Set<DeliveredSubscriptionsAgainstTaggedPrice> getDeliveredSubscriptionsAgainstTaggedPrices() {
        return deliveredSubscriptionsAgainstTaggedPrices;
    }

    public void addToDeliveredSubscriptionsAgainstTaggedPrices(DeliveredSubscriptionsAgainstTaggedPrice deliveredSubscriptionsAgainstTaggedPrice){
        this.deliveredSubscriptionsAgainstTaggedPrices.add(deliveredSubscriptionsAgainstTaggedPrice);
    }

    public Map<LocalDate, Double> getRegisteredProfit() {
        return registeredProfit;
    }
    public void addToRegisteredProfit(LocalDate profitDate,double profitAmount ){
        registeredProfit.put(profitDate,profitAmount);
    }
    public Map<LocalDate, Double> getRegisteredPurchaseCostOfDeliveredUnits() {
        return registeredPurchaseCostOfDeliveredUnits;
    }

    public void addToRegisteredPurchaseCostOfDeliveredUnits(LocalDate deliveryDate,double purchaseCost){
        registeredPurchaseCostOfDeliveredUnits.put(deliveryDate,purchaseCost);
    }
    public Map<LocalDate, Double> getRegisteredRevenue() {
        return registeredRevenue;
    }

    public void addToRegisteredRevenue(LocalDate deliveryDate, double revenue){
        this.registeredRevenue.put(deliveryDate,revenue);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceBucketView)) return false;

        PriceBucketView that = (PriceBucketView) o;

        return getProductwisePriceBucketId() != null ? getProductwisePriceBucketId().equals(that.getProductwisePriceBucketId()) : that.getProductwisePriceBucketId() == null;
    }

    @Override
    public int hashCode() {
        return getProductwisePriceBucketId() != null ? getProductwisePriceBucketId().hashCode() : 0;
    }
}
