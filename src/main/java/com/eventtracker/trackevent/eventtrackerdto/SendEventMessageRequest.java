package com.eventtracker.trackevent.eventtrackerdto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class SendEventMessageRequest {

    @NotNull
    private String userId;
    private ZonedDateTime timestamp;

    @NotNull
    private String eventType;
    private String deviceId;
    private String agent;
}
