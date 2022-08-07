package com.siloam.restapi.configuration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.siloam.restapi.service.ShopeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
@Configuration
public class SchedulerConfiguration {

    @Autowired
    private ShopeeService shopeeService;

    @Scheduled(cron = "0 0 * * * *")
    public void getCart() throws JsonProcessingException {
        log.info("Scheduler get cart start at {}", LocalDateTime.now());
        shopeeService.getCartShopee();
        log.info("Scheduler get cart end at {}", LocalDateTime.now());
    }
}
