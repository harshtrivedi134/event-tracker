package com.eventprocessor.eventprocessor.eventprocessordto;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@ComponentScan("com.eventprocessor.eventprocessor")
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
