package com.verifier.domains.payments.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/26/2017.
 */
@Document()
public class ProductOfferPricesView {
    @Id
    private ProductwisePriceBucketId productwisePriceBucketId;
    private ProductPricingCategory productPricingCategory;
    private double offerPriceOrPercent;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime fromDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime toDate;

    public ProductOfferPricesView() {
    }

    public ProductOfferPricesView(ProductwisePriceBucketId productwisePriceBucketId, ProductPricingCategory productPricingCategory, double offerPriceOrPercent, LocalDateTime fromDate) {
        this.productwisePriceBucketId = productwisePriceBucketId;
        this.productPricingCategory = productPricingCategory;
        this.offerPriceOrPercent = offerPriceOrPercent;
        this.fromDate=fromDate;
        this.toDate=new LocalDateTime(9999,12,31,23,59,00);
    }

    public ProductwisePriceBucketId getProductwisePriceBucketId() {
        return productwisePriceBucketId;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public double getOfferPriceOrPercent() {
        return offerPriceOrPercent;
    }

    public void setOfferPriceOrPercent(double offerPriceOrPercent) {
        this.offerPriceOrPercent = offerPriceOrPercent;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getFromDate() {
        return fromDate;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }
}
