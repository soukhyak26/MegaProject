package com.affaince.subscription.subscriber.services.converters;

import com.affaince.subscription.subscriber.vo.DeliveryVersionId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@ReadingConverter
public class DeliveryTrendVersionIdReaderConverter implements Converter<DBObject, DeliveryVersionId> {
    @Override
    public DeliveryVersionId convert(DBObject o) {

        String trendSettingDate = (String) o.get("trendSettingDate");
        String startDate = (String) o.get("startDate");
        String weightRangeMin = (String) o.get("weightRangeMin");
        String weightRangeMax=(String) o.get("weightRangeMax");

        DeliveryVersionId id = new DeliveryVersionId(LocalDate.parse(trendSettingDate),LocalDate.parse(startDate), Double.parseDouble(weightRangeMin),Double.parseDouble(weightRangeMax));
        return id;
    }
}
