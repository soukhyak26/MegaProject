package com.affiance.mock;

import com.affiance.mock.MongoReader;
import com.affiance.business.view.BusinessAccountView;
import org.bson.Document;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessMongoReader extends MongoReader<BusinessAccountView> {


    public List<BusinessAccountView> find(Map<String, Object> queryParams) {
        List<Document> recordList = super.findRecord(queryParams);
        List<BusinessAccountView> businessAccountViews = new ArrayList<>();
        for (Document document : recordList) {
            BusinessAccountView view =new BusinessAccountView((String)document.get("id"), (String)document.get("merchantId"), LocalDate.parse((String) document.get("startDate")), LocalDate.parse((String) document.get("endDate")), LocalDate.parse("dateForProvision"));
            businessAccountViews.add(view);
            System.out.println("Business Account View: " + view);
        }
        return businessAccountViews;
    }

    public static void main(String[] args){
        BusinessMongoReader reader = new BusinessMongoReader();
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("startDate","2018-01-01");
        queryParams.put("endDate","2018-12-31");
        List<BusinessAccountView> accounts = reader.find(queryParams);
        for(BusinessAccountView account: accounts){
            System.out.println("account:" + account);
        }
    }
} 
