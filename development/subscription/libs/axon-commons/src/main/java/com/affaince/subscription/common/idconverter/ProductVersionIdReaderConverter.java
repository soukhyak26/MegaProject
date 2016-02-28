package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.mongodb.DBObject;

import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@ReadingConverter
public class ProductVersionIdReaderConverter implements Converter<DBObject, ProductVersionId> {
    @Override
    public ProductVersionId convert(DBObject o) {

        String productId = (String) o.get("productId");
        String fromDate = (String) o.get("fromDate");

        ProductVersionId id = new ProductVersionId(productId, LocalDate.parse(fromDate));

        return id;
    }
}
