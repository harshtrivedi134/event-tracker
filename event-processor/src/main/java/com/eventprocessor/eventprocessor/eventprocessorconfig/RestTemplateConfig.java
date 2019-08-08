package com.eventprocessor.eventprocessor.eventprocessorconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestConfigurationProperties restConfigurationProperties;

    @Bean
    public RestTemplate restTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
        return new RestTemplateBuilder()
                    .setConnectTimeout(restConfigurationProperties.getConnectTimeout())
                    .setReadTimeout(restConfigurationProperties.getReadTimeout())
                    .rootUri(restConfigurationProperties.getRootUri())
                    .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }
}
