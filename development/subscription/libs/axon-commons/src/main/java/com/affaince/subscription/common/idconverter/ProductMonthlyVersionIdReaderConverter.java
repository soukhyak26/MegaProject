package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ProductMonthlyVersionId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandark on 28-02-2016.
 */
public class ProductMonthlyVersionIdReaderConverter implements Converter<DBObject, ProductMonthlyVersionId> {
    @Override
    public ProductMonthlyVersionId convert(DBObject o) {

        String productId = (String) o.get("productId");
        YearMonth yearMonth = (YearMonth) o.get("monthOfYear");

        ProductMonthlyVersionId id = new ProductMonthlyVersionId(productId, yearMonth);

        return id;
    }
}
