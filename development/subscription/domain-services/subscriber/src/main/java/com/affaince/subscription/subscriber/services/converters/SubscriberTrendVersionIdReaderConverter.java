package com.affaince.subscription.subscriber.services.converters;

import com.affaince.subscription.subscriber.vo.SubscriberTrendVersionId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@ReadingConverter
public class SubscriberTrendVersionIdReaderConverter implements Converter<DBObject, SubscriberTrendVersionId> {
    @Override
    public SubscriberTrendVersionId convert(DBObject o) {

        String trendSettingDate = (String) o.get("trendSettingDate");
        String startDate = (String) o.get("startDate");

        SubscriberTrendVersionId id = new SubscriberTrendVersionId(LocalDate.parse(trendSettingDate),LocalDate.parse(startDate));
        return id;
    }
}
