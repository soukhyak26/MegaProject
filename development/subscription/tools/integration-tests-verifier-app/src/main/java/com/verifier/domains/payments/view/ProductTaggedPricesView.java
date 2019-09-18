package com.verifier.domains.payments.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/26/2017.
 */
@Document(collection="ProductTaggedPricesView")
public class ProductTaggedPricesView {
    @Id
    private ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId;
    private double purchasePricePerUnit;
    private double MRP;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate taggedPriceStartDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate taggedPriceEndaDate;

    public ProductTaggedPricesView() {
    }

    public ProductTaggedPricesView(ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId, double purchasePricePerUnit, double MRP, LocalDate taggedPriceStartDate) {
        this.productwiseTaggedPriceVersionId = productwiseTaggedPriceVersionId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedPriceStartDate=taggedPriceStartDate;
        this.taggedPriceEndaDate=new LocalDate(9999,12,31);
    }

    public ProductwiseTaggedPriceVersionId getProductwiseTaggedPriceVersionId() {
        return productwiseTaggedPriceVersionId;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public LocalDate getTaggedPriceStartDate() {
        return taggedPriceStartDate;
    }

    public LocalDate getTaggedPriceEndaDate() {
        return taggedPriceEndaDate;
    }

    public void setTaggedPriceEndaDate(LocalDate taggedPriceEndaDate) {
        this.taggedPriceEndaDate = taggedPriceEndaDate;
    }
}
