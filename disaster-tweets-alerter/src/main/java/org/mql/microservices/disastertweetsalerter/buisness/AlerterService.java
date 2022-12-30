package org.mql.microservices.disastertweetsalerter.buisness;

import org.mql.microservices.disastertweetsalerter.models.Tweet;

public interface AlerterService {
    public boolean alert(Tweet tweet);
}
