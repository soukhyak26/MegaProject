package com.affaince.subscription.date;

import com.mongodb.*;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
public final class SysDate {
    private static Logger logger = LoggerFactory.getLogger(SysDate.class);
    private LocalDate localDate;
    private static SysDate sysDate;
    private static boolean productionMode;
    private static DBCollection dbCollection;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("sysdatesetting");
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(resourceBundle.getString("view.db.host"), Integer.parseInt(resourceBundle.getString("view.db.port")));
        } catch (UnknownHostException e) {
            logger.info("Cannot connect to host: " + e.getMessage());
        }
        DB db = mongoClient.getDB(resourceBundle.getString("view.db.name"));
        dbCollection = db.getCollection(resourceBundle.getString("sysdate.view.db.collection"));
        productionMode = Boolean.parseBoolean(resourceBundle.getString("subscription.productionMode"));
    }

    private SysDate() {

    }

    public synchronized static void setCurrentDate(LocalDate currentDate) {
        dbCollection.drop();
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("dd-MM-yyyy");
        String date = formatter.print(currentDate);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("currentDate", date);
        dbCollection.insert(basicDBObject);
    }

    public synchronized static LocalDate now() {
        if (productionMode) {
            return LocalDate.now();
        }
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("dd-MM-yyyy");
        //System.out.println("###########################333" + dbCollection.getName());
        DBObject dbObject = dbCollection.find().next();
        return LocalDate.parse(dbObject.get("currentDate").toString(), formatter);
    }
}
