package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by mandar on 31-12-2016.
 */
@WritingConverter
public class ProductwiseTaggedPriceVersionIdWriterConverter implements Converter<ProductwiseTaggedPriceVersionId, DBObject> {
    public DBObject convert(ProductwiseTaggedPriceVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("priceBucketId", source.getTaggedPriceVersionId());
        return dbo;
    }

}
