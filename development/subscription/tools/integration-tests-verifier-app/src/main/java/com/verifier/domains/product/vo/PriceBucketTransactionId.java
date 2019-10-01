package com.verifier.domains.product.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 5/2/2017.
 */
public class PriceBucketTransactionId implements Serializable{
    private final String productId;
    private final String priceBucketId;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate transactionDate;

    public PriceBucketTransactionId(String productId, String priceBucketId, LocalDate transactionDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.transactionDate = transactionDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
