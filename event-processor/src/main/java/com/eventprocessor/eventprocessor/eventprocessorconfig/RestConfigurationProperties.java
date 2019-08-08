package com.eventprocessor.eventprocessor.eventprocessorconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rest")
@Getter @Setter
public class RestConfigurationProperties {

    private int connectTimeout;
    private int readTimeout;
    private String rootUri;
}
