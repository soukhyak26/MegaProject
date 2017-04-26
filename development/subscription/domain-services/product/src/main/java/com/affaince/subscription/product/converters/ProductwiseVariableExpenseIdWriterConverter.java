package com.affaince.subscription.product.converters;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.vo.ProductwiseVariableExpenseId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 4/26/2017.
 */
public class ProductwiseVariableExpenseIdWriterConverter implements Converter<ProductwiseVariableExpenseId, DBObject> {
    public DBObject convert(ProductwiseVariableExpenseId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("fromDate", source.getFromDate().toString());
        return dbo;
    }

}
