package com.affiance.mock;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MongoReader<T> {
    private static MongoClient mongoClient;
    private static MongoDatabase db;

    public MongoReader(){
        final List<ServerAddress> serverAddresses = new ArrayList<>();
        serverAddresses.add(new ServerAddress("localhost", 27017));
        final MongoClientOptions clientOptions = new MongoClientOptions.Builder()
                .connectTimeout(25000)
                .readPreference(ReadPreference.primaryPreferred())
                .build();
       // List<MongoCredential> credList = new ArrayList<>();
/*
        MongoCredential mongoCredential = MongoCredential.createCredential("","business","qscvplmnb1029".toCharArray());
        credList.add(mongoCredential);
*/
        this.mongoClient = new MongoClient(serverAddresses, clientOptions);
        this.db=this.mongoClient.getDatabase("business");
    }

    protected List<Document> findRecord(Map<String,Object> queryParams){
        MongoCollection<Document> mongoCollection= db.getCollection("BusinessAccountView");
        BasicDBObject query = new BasicDBObject();
        for(Map.Entry<String,Object> entry: queryParams.entrySet()){
            query.put(entry.getKey(),entry.getValue());
        }
        return mongoCollection.find(query).into(new ArrayList<Document>());
    }

    public abstract List<T> find(Map<String,Object> queryParams);
    public static void drop(){
        db.drop();
    }
} 
