package org.mql.microservices.disastertweetsalerter.buisness;

import java.util.List;

import org.mql.microservices.disastertweetsalerter.models.Contact;
import org.mql.microservices.disastertweetsalerter.models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

@Component
public class SmsAlerter implements Alerter<Tweet> {
    @Value("${sms.sender.phone}")
    private String fromPhone;
    private String smsMessage = "this SMS was sent to create an alert for this tweet : ";

    @Autowired
    private VonageClient client;

    public SmsAlerter() {
    }

    public SmsAlerter(String fromPhone, String smsMessage) {
        this.fromPhone = fromPhone;
        this.smsMessage = smsMessage;
    }

    public boolean alert(Tweet subject, List<Contact> contacts) {
        String message = subject!=null? smsMessage + subject.getTweet(): "Alert: No tweet is provided";
        int count = 0;
        for (Contact contact : contacts) {
            if(sendSMS(contact.getPhone(), message)) {
                count++;
            };
        }
        return count > 0;
    }

    private boolean sendSMS(String toPhone, String messageBody) {
        TextMessage message = new TextMessage(formatPhoneNumber(fromPhone),
                formatPhoneNumber(toPhone),
                messageBody
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully. " + response.getMessages());
            return true;
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
        return false;
    }

    private String formatPhoneNumber(String phone) {
        return phone.replace("+", "").replace("-", "").replace(" ", "");
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }

}
