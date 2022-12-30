package org.mql.microservices.disastertweetsalerter.dao;
import java.util.List;

import org.mql.microservices.disastertweetsalerter.models.Contact;

public interface ContactsRepository {
    public List<Contact> findByCountry(String country);
}
