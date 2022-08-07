package com.siloam.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siloam.restapi.dto.shopee.ShopeeCartRequestDto;
import com.siloam.restapi.dto.shopee.ShopeeClientIdentifierDto;
import com.siloam.restapi.dto.shopee.ShopeeLoginRequestDto;
import com.siloam.restapi.dto.shopee.ShopeeTimeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShopeeService {

    @Autowired
    @Qualifier("scrapingRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String SHOPEE_LOGIN_URL = "https://shopee.co.id/api/v4/account/login_by_password";
    private static final String SHOPEE_GET_CART = "https://shopee.co.id/api/v4/cart/get";

    private static final String SHOPEE_PHONE_NUMBER = "6282214687574";
    private static final String SHOPEE_PASSWORD = "a83605a6ad5b77aa27bd727673b5159dce99e524d061d94231f9f4a89940a4b3";
    private static final String SHOPEE_SECURITY_DEVICE = "/is6wn8vzqC4L1ZWfvyC4A==|sFirq0y8QglN9xPXbUNrc6kLky7ohMV2VZR9kfc9DCZ+44f4m0VUFdLqb2d137oiFnR/S+L6Q92Tgk/H53zKEI0+GA==|T8asL6lLs+PEMggd|05|3";

    public String loginShopee() throws JsonProcessingException {
        var clientIdentifierDto = ShopeeClientIdentifierDto.builder()
                .securityDeviceFingerprint(SHOPEE_SECURITY_DEVICE).build();
        var loginRequestDto = ShopeeLoginRequestDto.builder()
                .phone(SHOPEE_PHONE_NUMBER)
                .password(SHOPEE_PASSWORD)
                .shopeeClientIdentifierDto(clientIdentifierDto)
                .build();

        log.info("LoginRequest -> {}",objectMapper.writeValueAsString(loginRequestDto));
        var loginResponse = restTemplate.postForEntity(
                SHOPEE_LOGIN_URL,loginRequestDto,String.class);
        log.info("LoginResponse -> {}",objectMapper.writeValueAsString(loginResponse));

        var cartRequsetDto = ShopeeCartRequestDto.builder()
                .shopeeTimeFilter(ShopeeTimeFilter.builder().startTime(0).build())
                .preSelectedItemList(new ArrayList<>())
                .version(3)
                .build();
        log.info("CartRequest -> {}",objectMapper.writeValueAsString(cartRequsetDto));
        var cartResponse = restTemplate.postForEntity(
                SHOPEE_GET_CART, cartRequsetDto,String.class);
        log.info("CartResponse -> {}",objectMapper.writeValueAsString(cartResponse));

        return objectMapper.writeValueAsString(cartResponse.getBody());
    }
}
