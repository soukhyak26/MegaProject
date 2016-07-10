package com.affaince.notification.events;

import com.affaince.subscription.mail.EMailSender;
import com.affaince.subscription.mail.MailAccount;
import com.affaince.subscription.mail.MailConstants;
import org.apache.commons.mail.EmailException;

/**
 * Created by anayonkar on 10/7/16.
 */
public abstract class NotificationEvent {
    protected String subject;
    protected String receiver;

    public void sendEmail() {
        String message = getEmailMessage();
        EMailSender eMailSender = new EMailSender(MailConstants.FROM, MailConstants.PASSWORD, receiver, MailAccount.GMAIL, subject, message);
        try {
            eMailSender.sendEMail();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String getEmailMessage();
}

