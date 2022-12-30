package org.mql.microservices.disastertweetsalerter.dao;
import java.util.List;
import java.util.Vector;

import org.mql.microservices.disastertweetsalerter.models.Contact;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultContactsRepository implements ContactsRepository {
    private List<Contact> list = new Vector<Contact>();
    
    public DefaultContactsRepository() {
        list.add(new Contact(101, "MA", "med.fes63@gmail.com", "+212682905700"));
        list.add(new Contact(101, "USA","issambenhamou1@gmail.com", "+212682905700"));
    }
    public List<Contact> findByCountry(String country) {
        return list;
    }
}