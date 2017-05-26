package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by mandar on 29-12-2016.
 */
@WritingConverter
public class ProductwisePricebucketIdWriterConverter implements Converter<ProductwisePriceBucketId, DBObject> {
    public DBObject convert(ProductwisePriceBucketId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("priceBucketId", source.getPriceBucketId());
        return dbo;
    }

}
