package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by mandar on 29-12-2016.
 */
@WritingConverter
public class PriceBucketTransactionIdWriterConverter implements Converter<PriceBucketTransactionId, DBObject> {
    public DBObject convert(PriceBucketTransactionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("priceBucketId", source.getPriceBucketId());
        dbo.put("transactionDate",source.getTransactionDate().toString());
        return dbo;
    }

}
