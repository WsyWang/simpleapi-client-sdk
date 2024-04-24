package com.wsy.simpleapiclientsdk;

import com.wsy.simpleapiclientsdk.client.SimpleApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("simpleapi.client")
@Data
@ComponentScan
public class SimpleApiConfig {
    private String accessKey;

    private String secretKey;

    @Bean
    public SimpleApiClient simpleApiClient() {
        return new SimpleApiClient(accessKey, secretKey);
    }
}
