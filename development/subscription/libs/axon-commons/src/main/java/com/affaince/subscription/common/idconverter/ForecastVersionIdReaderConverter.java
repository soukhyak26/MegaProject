package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@ReadingConverter
public class ForecastVersionIdReaderConverter implements Converter<DBObject, ForecastVersionId> {
    @Override
    public ForecastVersionId convert(DBObject o) {

        String productId = (String) o.get("productId");
        String fromDate = (String) o.get("fromDate");
        String forecastDate=(String) o.get("forecastDate");

        ForecastVersionId id = new ForecastVersionId(productId, LocalDate.parse(fromDate),LocalDate.parse(forecastDate));
        return id;
    }
}
