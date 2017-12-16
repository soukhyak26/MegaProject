package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandar on 31-12-2016.
 */
@ReadingConverter
public class ProductwiseTaggedPriceVersionIdReaderConverter implements Converter<DBObject, ProductwiseTaggedPriceVersionId> {
    @Override
    public ProductwiseTaggedPriceVersionId convert(DBObject o) {
        String productId = (String) o.get("productId");
        String taggedPriceVersionId = (String) o.get("taggedPriceVersionId");
        ProductwiseTaggedPriceVersionId id = new ProductwiseTaggedPriceVersionId(productId, taggedPriceVersionId);

        return id;
    }

}