package org.mql.microservices.disastertweetsalerter.web.controllers;

import org.mql.microservices.disastertweetsalerter.buisness.AlerterService;
import org.mql.microservices.disastertweetsalerter.buisness.DisasterTweetsClassifierService;
import org.mql.microservices.disastertweetsalerter.models.ClassificationResult;
import org.mql.microservices.disastertweetsalerter.models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("alert")
public class ClassificationController {
    
    @Autowired
    private DisasterTweetsClassifierService classifierService;
    @Autowired
    private AlerterService alerterService;
    
    @PostMapping("/verify")
    public ResponseEntity<String> checkAndAlert(@RequestBody Tweet tweet) {
        ClassificationResult result = classifierService.classifyTweet(tweet);
        boolean alertResult = false;
        //alertResult = alerterService.alert();
        if(result.isRelevant()) {
           alertResult = alerterService.alert(tweet); 
        }
        return ResponseEntity.ok(String.valueOf(alertResult));
    }

	@GetMapping("/alert")
    public ResponseEntity<String> alert() {
        boolean alertResult = alerterService.alert(null);
        return ResponseEntity.ok(String.valueOf(alertResult));
    }

}
