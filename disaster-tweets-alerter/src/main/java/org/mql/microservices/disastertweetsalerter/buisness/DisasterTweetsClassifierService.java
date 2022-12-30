package org.mql.microservices.disastertweetsalerter.buisness;

import org.mql.microservices.disastertweetsalerter.models.ClassificationResult;
import org.mql.microservices.disastertweetsalerter.models.Tweet;

public interface DisasterTweetsClassifierService {
    public ClassificationResult classifyTweet(Tweet tweet);
}
