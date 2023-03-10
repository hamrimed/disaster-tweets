package org.mql.microservices.disastertweetsalerter.buisness;
import java.util.List;

import org.mql.microservices.disastertweetsalerter.dao.ContactsRepository;
import org.mql.microservices.disastertweetsalerter.models.Contact;
import org.mql.microservices.disastertweetsalerter.models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultAlerterService implements AlerterService {
    @Autowired
    private ContactsRepository contactsRepository;
    @Autowired
    private List<Alerter<Tweet>> alerters;
    
    public boolean alert(Tweet tweet) {
        List<Contact> contacts = contactsRepository.findByCountry("MA");
        int count = 0;
        for (Alerter<Tweet> alerter : alerters) {
            if(alerter.alert(tweet, contacts)) {
                count++;
            }
        }
        return count > 0;
    }
}
