package com.eventtracker.trackevent.eventtrackercontroller;

import com.eventtracker.trackevent.eventtrackerdto.SendEventMessageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "Event Tracking system")
@RequestMapping("/track/event/")
public class EventTrackerController {

    private final JmsTemplate jmsTemplate;
    private static Logger log = LoggerFactory.getLogger(EventTrackerController.class);

    EventTrackerController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @ApiOperation(value = "Post the message to the queue")
    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully sent message to the queue"),
            @ApiResponse(code = 404, message = "Messaging service not found"),
            @ApiResponse(code = 500, message = "Internal Sever Error")
    })
    public void sendMessage(@Valid @RequestBody SendEventMessageRequest sendEventMessageRequest) throws Exception {

        if ((StringUtils.isBlank(sendEventMessageRequest.getUserId())
                || sendEventMessageRequest.getUserId().equals("string"))
                ||
                (StringUtils.isBlank(sendEventMessageRequest.getEventType())
                        || sendEventMessageRequest.getEventType().equals("string"))) {

            throw new Exception("userId and eventType is required");

        }
        try {
            log.info("Sending message to queue");
            jmsTemplate.convertAndSend("EventTrackingQueue", sendEventMessageRequest);

        } catch (Exception e) {
            log.error("Error sending message to the queue", e.getMessage());
        }
    }
}
