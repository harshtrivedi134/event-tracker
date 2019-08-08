package com.eventprocessor.eventprocessor.eventprocessordto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.time.ZonedDateTime;

@Component
public class EventHandler {

    private final JmsTemplate jmsTemplate;
    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.root_uri}")
    private final String apiUrl;

    EventHandler(JmsTemplate jmsTemplate, RestTemplate restTemplate,
                 @Value("${rest.root_uri}") String apiUrl) {

        this.jmsTemplate = jmsTemplate;
        this.apiUrl = apiUrl;
    }


    @JmsListener(destination = "EventTrackingQueue", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage() throws Exception {
        Message message = jmsTemplate.receive("EventTrackingQueue");
        log.info("Received event from ActiveMQ");

        TextMessage textMessage = (TextMessage) message;
        String messageContents = textMessage.getText();

        JSONObject jsonObject = new JSONObject(messageContents);

        SendEventMessageRequest eventToBePersisted = generateEventEntity(jsonObject);
        persistEvent(eventToBePersisted);


    }

    private SendEventMessageRequest generateEventEntity(JSONObject jsonObject) throws JSONException {

        SendEventMessageRequest sendEventMessageRequest = new SendEventMessageRequest();

        sendEventMessageRequest.setAgent(jsonObject.getString("agent"));
        sendEventMessageRequest.setDeviceId(jsonObject.getString("deviceId"));
        sendEventMessageRequest.setEventType(jsonObject.getString("eventType"));
        sendEventMessageRequest.setUserId(jsonObject.getString("userId"));
        sendEventMessageRequest.setTimestamp(ZonedDateTime.now());

        return sendEventMessageRequest;
    }

    private void persistEvent(SendEventMessageRequest sendEventMessageRequest) throws Exception {
        try {
            log.info("Calling EventProcessor API to persist event in the database");
            String result = restTemplate.postForObject(apiUrl, sendEventMessageRequest, String.class);
            System.out.println(result);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e);
        }
    }
}
