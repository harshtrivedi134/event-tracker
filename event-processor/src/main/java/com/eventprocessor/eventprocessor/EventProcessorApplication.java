package com.eventprocessor.eventprocessor;

import com.eventprocessor.eventprocessor.eventprocessorconfig.RestConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(RestConfigurationProperties.class)
@SpringBootApplication
public class EventProcessorApplication {

    public static void main(String[] args) {
        //TODO: Implement global ExceptionHandler class - @ControllerAdvice
        SpringApplication.run(EventProcessorApplication.class, args);
    }
}
