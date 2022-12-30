package org.mql.microservices.disastertweetsalerter.buisness;

import java.util.HashMap;
import java.util.Map;

import org.mql.microservices.disastertweetsalerter.models.ClassificationResult;
import org.mql.microservices.disastertweetsalerter.models.Tweet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClassifierRestTemplate implements DisasterTweetsClassifierService {
   

    public ClassificationResult classifyTweet(Tweet tweet) {
        RestTemplate restTemplate = new RestTemplate();
        // Set the URL of the Flask API endpoint
        String apiUrl = "http://4.152.192.132:5000/predict";

        // Set the request body (e.g. the tweet to be classified)
        //String requestBody = String.format("{\"tweet\": \"%s\"}", tweet.getTweet());
        Map<String, String> requestBody = new HashMap<>();
        //String tweet_test = "RT @user: Breaking news: A major earthquake has struck the coast of Indonesia. Many people are injured and buildings have been damaged. Please stay safe and follow the advice of local authorities.";
        requestBody.put("tweet", tweet.getTweet());

        // Make the POST request and get the response as a JSON string
        String responseBody = restTemplate.postForObject(apiUrl, requestBody, String.class);

        // Parse the JSON string to get the classification result
        // For example, if the JSON string is {"classification": "relevant"}, you can use the following code to get the classification value:
        boolean classification = responseBody.contains("true");
        return new ClassificationResult(classification);
    }
}
