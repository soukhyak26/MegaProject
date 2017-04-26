package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.ProductwiseFixedExpenseId;
import com.affaince.subscription.product.vo.ProductwiseVariableExpenseId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 4/26/2017.
 */
public class ProductwiseVariableExpenseIdReaderConverter implements Converter<DBObject, ProductwiseVariableExpenseId> {

    @Override
    public ProductwiseVariableExpenseId convert(DBObject o) {

        String productId = (String) o.get("productId");
        String fromDate = (String) o.get("fromDate");

        ProductwiseVariableExpenseId id = new ProductwiseVariableExpenseId(productId, LocalDate.parse(fromDate));

        return id;
    }

}
