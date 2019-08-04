package com.eventtracker.trackevent.eventtrackerdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEventMessageRequest {

    @NotNull
    private String userId;

    private ZonedDateTime timestamp;

    @NotNull
    private String eventType;

    private String deviceId;

    private String agent;
}
