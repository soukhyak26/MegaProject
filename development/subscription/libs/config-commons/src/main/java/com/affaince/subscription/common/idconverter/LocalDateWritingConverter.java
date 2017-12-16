package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 02-07-2016.
 */
public class LocalDateWritingConverter implements Converter<LocalDate, DBObject> {
    public DBObject convert(LocalDate date){
        DBObject dbo = new BasicDBObject();
        dbo.put("endDate", date.toString());
        return dbo;
    }
}
