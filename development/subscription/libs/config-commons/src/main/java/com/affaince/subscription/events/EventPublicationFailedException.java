package com.affaince.subscription.events;

import org.axonframework.common.AxonException;

/**
 * Created by mandark on 05-08-2015.
 */
public class EventPublicationFailedException extends AxonException {
    public EventPublicationFailedException(String message) {
        super(message);
    }

    public EventPublicationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
