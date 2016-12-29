package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import com.mongodb.DBObject;
import org.joda.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandar on 29-12-2016.
 */
@ReadingConverter
public class ProductwisePriceBucketIdReaderConverter implements Converter<DBObject, ProductwisePriceBucketId> {

    @Override
    public ProductwisePriceBucketId convert(DBObject o) {

        String productId = (String) o.get("productId");
        String priceBucketId = (String) o.get("priceBucketId");

        ProductwisePriceBucketId id = new ProductwisePriceBucketId(productId, priceBucketId);

        return id;
    }

}
