package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@WritingConverter
public class ForecastVersionIdWriterConverter implements Converter<ForecastVersionId, DBObject> {

    public DBObject convert(ForecastVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("productId", source.getProductId());
        dbo.put("fromDate", source.getFromDate().toString());
        dbo.put("forecastDate",source.getForecastDate().toString());
        return dbo;
    }
}
