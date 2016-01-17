package com.affaince.notification.publisher;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anayonkar on 16/1/16.
 */
public class GenericMailEventPublisher {
    @Autowired
    public GenericMailEventPublisher(){}

    public void getNotificationEvent(Object event){
        System.out.println("\n\t\t\t\t******************************\n\t\t\t\t"
                + event.getClass() + "\t" + event
                + "\n\t\t\t\t******************************\n");
    }
}
