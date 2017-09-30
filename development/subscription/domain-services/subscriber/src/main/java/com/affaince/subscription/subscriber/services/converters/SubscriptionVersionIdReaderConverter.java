package com.affaince.subscription.subscriber.services.converters;

import com.affaince.subscription.subscriber.vo.SubscriptionVersionId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@ReadingConverter
public class SubscriptionVersionIdReaderConverter implements Converter<DBObject, SubscriptionVersionId> {
    @Override
    public SubscriptionVersionId convert(DBObject o) {

        String trendSettingDate = (String) o.get("trendSettingDate");
        String startDate = (String) o.get("startDate");
        String weightRangeMin = (String) o.get("weightRangeMin");
        String weightRangeMax=(String) o.get("weightRangeMax");

        SubscriptionVersionId id = new SubscriptionVersionId(LocalDate.parse(trendSettingDate),LocalDate.parse(startDate), Double.parseDouble(weightRangeMin),Double.parseDouble(weightRangeMax));
        return id;
    }
}
