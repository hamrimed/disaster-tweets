package org.mql.microservices.disastertweetsalerter.models;

public class ClassificationResult {
    private boolean relevant;

    public ClassificationResult() {
    }

    public ClassificationResult(boolean relevant) {
        this.relevant = relevant;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

        
    
}
