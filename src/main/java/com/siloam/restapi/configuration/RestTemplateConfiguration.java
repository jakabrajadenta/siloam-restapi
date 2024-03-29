package com.siloam.restapi.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    @Qualifier("ServiceRestTemplate")
    public RestTemplate getServiceRestTemplate() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMillis(100000))
                .setConnectTimeout(Duration.ofMillis(100000))
                .build();
    }

    @Bean()
    @Qualifier("ShopeeScrapingRestTemplate")
    public RestTemplate getShopeeScrapingRestTemplate() {
        return new RestTemplateBuilder()
                .interceptors(new ShopeeScrapingInterceptor())
                .build();
    }
}
