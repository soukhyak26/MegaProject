package com.affaince.subscription.expensedistribution.query.view;

import com.affaince.subscription.common.deserializer.LocalDateTimeDeserializer;
import com.affaince.subscription.common.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandark on 28-01-2016.
 */
public class ProductVersionId implements Serializable {
    private String productId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate fromDate;

    public ProductVersionId() {
    }

    public ProductVersionId(String productId, LocalDate fromDate) {
        this.productId = productId;
        this.fromDate = fromDate;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDate getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public String toString() {
        return "ProductVersionId{" +
                "productId='" + productId + '\'' +
                ", fromDate=" + fromDate +
                '}';
    }
}
