package com.eventtracker.trackevent.eventtrackercontroller;

import com.eventtracker.trackevent.eventtrackerdto.SendEventMessageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    EventTrackerController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @ApiOperation(value= "Post the message to the queue")
    @PostMapping("/{user_id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully sent message to the queue"),
            @ApiResponse(code = 404, message = "Messaging service not found"),
            @ApiResponse(code = 500, message = "Internal Sever Error")
    })
    public void sendMessage(@Valid @RequestBody SendEventMessageRequest sendEventMessageRequest) throws Exception {

        if(sendEventMessageRequest.getUserId() == "string" ||
                sendEventMessageRequest.getEventType() == "string"){
            //TODO: create more meaningful Exception
            throw  new Exception("userId and eventType is required");

        }
        jmsTemplate.convertAndSend("EventTrackingQueue", sendEventMessageRequest);


    }

}
