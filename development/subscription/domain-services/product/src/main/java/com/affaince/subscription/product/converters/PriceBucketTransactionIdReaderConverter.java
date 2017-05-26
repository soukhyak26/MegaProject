package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandar on 29-12-2016.
 */
@ReadingConverter
public class PriceBucketTransactionIdReaderConverter implements Converter<DBObject, PriceBucketTransactionId> {

    @Override
    public PriceBucketTransactionId convert(DBObject o) {

        String productId = (String) o.get("productId");
        String priceBucketId = (String) o.get("priceBucketId");
        LocalDate transactionDate= new LocalDate((String)o.get("transactionDate")) ;

        PriceBucketTransactionId id = new PriceBucketTransactionId(productId, priceBucketId,transactionDate);

        return id;
    }

}
