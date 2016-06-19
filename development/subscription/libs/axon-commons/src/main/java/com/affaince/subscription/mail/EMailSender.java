package com.affaince.subscription.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import static com.affaince.subscription.mail.MailConstants.GMAIL_HOST_NAME;
import static com.affaince.subscription.mail.MailConstants.SMTP_PORT;

/**
 * Created by anayonkar on 19/6/16.
 * Currently supports gmail only
 * Disable security at https://www.google.com/settings/security/lesssecureapps
 */
public class EMailSender {
    private final String from;
    private final String password;
    private final String to;
    private final MailAccount mailAccount;
    private final String subject;
    private final String message;

    protected EMailSender(String from, String password, String to, MailAccount mailAccount, String subject, String message) {
        if(mailAccount == null) {
            throw new IllegalArgumentException("Invalid mail account type");
        }
        this.from = from;
        this.password = password;
        this.to = to;
        this.mailAccount = mailAccount;
        this.subject = subject;
        this.message = message;
    }

    protected void sendEMail() throws EmailException {
        Email email = new SimpleEmail();
        if(mailAccount == null || !MailAccount.GMAIL.equals(mailAccount)) {
            throw new IllegalArgumentException("Invalid mail account type");
        }
        email.setHostName(GMAIL_HOST_NAME);
        email.setSmtpPort(SMTP_PORT);
        email.setAuthenticator(new DefaultAuthenticator(from, password));
        email.setSSLOnConnect(true);
        email.setFrom(from);
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(to);
        email.send();
    }
}
