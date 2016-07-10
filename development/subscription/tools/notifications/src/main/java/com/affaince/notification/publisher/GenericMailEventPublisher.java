package com.affaince.notification.publisher;

import com.affaince.notification.events.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anayonkar on 16/1/16.
 */
public class GenericMailEventPublisher {
    @Autowired
    public GenericMailEventPublisher() {
    }

    public void getNotificationEvent(Object event) {
        System.out.println("\n\t\t\t\t******************************\n\t\t\t\t"
                + event.getClass() + "\t" + event
                + "\n\t\t\t\t******************************\n");
        if(NotificationEvent.class.isAssignableFrom(event.getClass())) {
            //TODO: refine configuration for email sender
            //((NotificationEvent)event).sendEmail();
        }
    }
}
