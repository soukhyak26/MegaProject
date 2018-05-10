package com.verifier.domains.subscriber.services.converters;

import com.mongodb.DBObject;
import com.verifier.domains.subscriber.vo.SubscriberVersionId;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@ReadingConverter
public class SubscriberTrendVersionIdReaderConverter implements Converter<DBObject, SubscriberVersionId> {
    @Override
    public SubscriberVersionId convert(DBObject o) {

        String trendSettingDate = (String) o.get("trendSettingDate");
        String startDate = (String) o.get("startDate");

        SubscriberVersionId id = new SubscriberVersionId(LocalDate.parse(trendSettingDate),LocalDate.parse(startDate));
        return id;
    }
}
