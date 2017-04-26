package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.ProductwiseFixedExpenseId;
import com.affaince.subscription.product.vo.ProductwiseVariableExpenseId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 4/26/2017.
 */
public class ProductwiseFixedExpenseIdWriterConverter implements Converter<ProductwiseFixedExpenseId, DBObject> {
    public DBObject convert(ProductwiseFixedExpenseId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("fromDate", source.getFromDate().toString());
        return dbo;
    }

}
