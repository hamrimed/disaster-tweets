package org.mql.microservices.disastertweetsalerter.buisness;

import java.util.List;

import org.mql.microservices.disastertweetsalerter.models.Contact;
import org.mql.microservices.disastertweetsalerter.models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailAlerter implements Alerter<Tweet> {
    @Autowired
    private JavaMailSender javaMailSender;
    private static String MAIL_SUBJECT = "alert-service";
    private static String MAIL_MSG = "this email was sent to create an alert for this tweet : ";
    
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }

    public boolean alert(Tweet subject, List<Contact> contacts) {
        for (Contact contact : contacts) {
            String body = subject!=null? MAIL_MSG + subject.getTweet(): "No tweet is provided";
            sendEmail(contact.getEmail(), MAIL_SUBJECT, body);     
        }
        return true;
    }
    
}
