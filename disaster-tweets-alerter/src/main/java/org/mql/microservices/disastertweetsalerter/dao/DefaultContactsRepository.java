package org.mql.microservices.disastertweetsalerter.dao;
import java.util.List;
import java.util.Vector;

import org.mql.microservices.disastertweetsalerter.models.Contact;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultContactsRepository implements ContactsRepository {
    private List<Contact> list = new Vector<Contact>();
    
    public DefaultContactsRepository() {
        init();
    }

    private void init() {
        list.add(new Contact(101, "MA", "med.fes63@gmail.com", "+212682905700"));
        list.add(new Contact(102, "USA","issambenhamou1@gmail.com", "+212650496770"));
    }

    public List<Contact> findByCountry(String country) {
        return list.stream().filter(c -> country.equals(c.getcountry())).toList();
    }

    public List<Contact> getAllContacts() {
        return list;
    }
}