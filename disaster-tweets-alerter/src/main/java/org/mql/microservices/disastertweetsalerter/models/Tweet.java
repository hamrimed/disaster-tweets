package org.mql.microservices.disastertweetsalerter.models;

public class Tweet {
    private String tweet;

    public Tweet() {
    }

    public Tweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
    

}
