package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ProductMonthlyVersionId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandark on 28-02-2016.
 */
public class ProductMonthlyVersionIdWriterConverter implements Converter<ProductMonthlyVersionId,DBObject > {

    public DBObject convert(ProductMonthlyVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("monthOfYear", source.getMonthOfYear());
        return dbo;
    }
}
