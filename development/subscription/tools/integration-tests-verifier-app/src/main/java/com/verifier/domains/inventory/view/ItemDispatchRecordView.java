package com.verifier.domains.inventory.view;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.verifier.domains.inventory.vo.ItemDispatchRecordId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ItemDispatchRecordView {
    @Id
    private ItemDispatchRecordId itemDispatchRecordId;
    private long outRecordCount;
    private double aggregateOfferPrice;
    private PriceTaggedWithProduct taggedPriceVersion;
    private LocalDate changeDate;

    public ItemDispatchRecordView(ItemDispatchRecordId itemDispatchRecordId, long outRecordCount, double aggregateOfferPrice, PriceTaggedWithProduct taggedPriceVersion, LocalDate changeDate) {
        this.itemDispatchRecordId = itemDispatchRecordId;
        this.outRecordCount = outRecordCount;
        this.aggregateOfferPrice = aggregateOfferPrice;
        this.taggedPriceVersion = taggedPriceVersion;
        this.changeDate = changeDate;
    }

    public ItemDispatchRecordView() {
    }

    public ItemDispatchRecordId getItemDispatchRecordId() {
        return itemDispatchRecordId;
    }

    public long getOutRecordCount() {
        return outRecordCount;
    }

    public double getAggregateOfferPrice() {
        return aggregateOfferPrice;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }
}
