package com.affaince.subscription.date;

import com.mongodb.*;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
public final class SysDateTime {
    private static Logger logger = LoggerFactory.getLogger(SysDateTime.class);
    private LocalDateTime localDateTime;
    private static SysDateTime sysDateTime;
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
        dbCollection = db.getCollection(resourceBundle.getString("sysdatetime.view.db.collection"));
        productionMode = Boolean.parseBoolean(resourceBundle.getString("subscription.productionMode"));
    }

    private SysDateTime() {

    }

    public static void setCurrentDateTime(LocalDateTime currentDateTime) {
        dbCollection.drop();
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("dd-MM-yyyy-hh:mm:ss");
        String date = formatter.print(currentDateTime);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("currentDateTime", date);
        dbCollection.insert(basicDBObject);
    }

    public static LocalDateTime now() {
        if (productionMode) {
            return LocalDateTime.now();
        }
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("dd-MM-yyyy-hh:mm:ss");
        DBObject dbObject = dbCollection.find().next();
        return LocalDateTime.parse(dbObject.get("currentDateTime").toString(), formatter);
    }
}