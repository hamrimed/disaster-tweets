package org.mql.microservices.disastertweetsalerter.buisness;
import org.mql.microservices.disastertweetsalerter.dao.ContactsRepository;
import org.mql.microservices.disastertweetsalerter.models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DefaultAlerterService implements AlerterService {
    
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ContactsRepository contactsRepository;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }
    public boolean alert(Tweet tweet) {
        String email = contactsRepository.findByCountry("MA").get(0).getEmail();
        sendEmail(email, "alert-service test", "this email was sent to create an alert for this tweet : " + tweet.getTweet() );
        return true;
    }
}
