package com.eventprocessor.eventprocessor.eventprocessorcontroller;

import com.eventprocessor.eventprocessor.eventprocessordto.SendEventMessageRequest;
import com.eventprocessor.eventprocessor.eventprocessormodel.Event;
import com.eventprocessor.eventprocessor.eventprocessorrepository.EventRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "Event Tracking system")
@RequestMapping("/event/processor/")
public class EventProcessorApiController {

    private final EventRepository eventRepository;

    private static final Logger log = LoggerFactory.getLogger(EventRepository.class);

    EventProcessorApiController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @ApiOperation(value = "Post the message to the queue")
    @PostMapping("/persist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully inserted event in the db"),
            @ApiResponse(code = 404, message = "Error inserting event in the db"),
            @ApiResponse(code = 500, message = "Internal Sever Error -  Event Processor")
    })
    private void persistEventMessage(@Valid @RequestBody SendEventMessageRequest sendEventMessageRequest)
            throws Exception {
        try {
            Event event = new Event();
            event.setUserId(sendEventMessageRequest.getUserId());
            event.setAgent(sendEventMessageRequest.getAgent());
            event.setDeviceId(sendEventMessageRequest.getDeviceId());
            event.setEventTime(sendEventMessageRequest.getTimestamp());
            event.setEventType(sendEventMessageRequest.getEventType());

            log.info("Persisting event entity  in the database");

            eventRepository.save(event);

            log.info("persisted event in the database", event.toString());

        } catch (Exception e) {
            log.error("Error persisting event in the database", e.getStackTrace());
            throw new Exception(e);
        }

    }
}
