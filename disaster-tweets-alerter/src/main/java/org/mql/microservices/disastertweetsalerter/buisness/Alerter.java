package org.mql.microservices.disastertweetsalerter.buisness;

import java.util.List;

import org.mql.microservices.disastertweetsalerter.models.Contact;

public interface Alerter<T> {
    public boolean alert(T subject, List<Contact> contacts);
}
