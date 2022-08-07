package com.siloam.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siloam.restapi.dto.shopee.*;
import com.siloam.restapi.entity.ResponseTrack;
import com.siloam.restapi.repository.ResponseTrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShopeeService {

    @Autowired
    @Qualifier("shopeeScrapingRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResponseTrackRepository responseTrackRepository;

    private static final String SHOPEE_LOGOUT_URL = "https://shopee.co.id/api/v4/account/logout";
    private static final String SHOPEE_LOGIN_URL = "https://shopee.co.id/api/v4/account/login_by_password";
    private static final String SHOPEE_GET_CART = "https://shopee.co.id/api/v4/cart/get";

    private static final String SHOPEE_PHONE_NUMBER = "6282214687574";
    private static final String SHOPEE_PASSWORD = "a83605a6ad5b77aa27bd727673b5159dce99e524d061d94231f9f4a89940a4b3";
    private static final String SHOPEE_SECURITY_DEVICE = "/is6wn8vzqC4L1ZWfvyC4A==|sFirq0y8QglN9xPXbUNrc6kLky7ohMV2VZR9kfc9DCZ+44f4m0VUFdLqb2d137oiFnR/S+L6Q92Tgk/H53zKEI0+GA==|T8asL6lLs+PEMggd|05|3";

    public String logoutShopee() throws JsonProcessingException {
        var logoutResponse = restTemplate.postForEntity(SHOPEE_LOGOUT_URL,ShopeeLoginRequestDto.builder().build(), String.class);
        log.info("LogoutResponse -> {}",objectMapper.writeValueAsString(logoutResponse));
        return objectMapper.writeValueAsString(logoutResponse.getBody());
    }

    public String loginShopee() throws JsonProcessingException {
        var clientIdentifierDto = ShopeeClientIdentifierDto.builder()
                .securityDeviceFingerprint(SHOPEE_SECURITY_DEVICE).build();
        var loginRequestDto = ShopeeLoginRequestDto.builder()
                .phone(SHOPEE_PHONE_NUMBER)
                .password(SHOPEE_PASSWORD)
                .shopeeClientIdentifierDto(clientIdentifierDto)
                .build();

        log.info("LoginRequest -> {}",objectMapper.writeValueAsString(loginRequestDto));
        var loginResponse = restTemplate.postForEntity(SHOPEE_LOGIN_URL,loginRequestDto, ShopeeLoginResponseDto.class);
        log.info("LoginResponse -> {}",objectMapper.writeValueAsString(loginResponse));
        responseTrackRepository.save(ResponseTrack.builder().url(SHOPEE_LOGIN_URL)
                .responseStatus(loginResponse.getStatusCode().toString())
                .responseCode(Objects.requireNonNull(loginResponse.getBody()).getErrorCode().toString())
                .responseDescription(loginResponse.getBody().getData().getUserId().toString()).build());

        var responseExtractor = new ResponseExtractor<>(){
            @Override
            public Object extractData(ClientHttpResponse response) throws IOException {
                return Objects.requireNonNull(response.getHeaders().get(HttpHeaders.SET_COOKIE)).stream().sorted().collect(Collectors.joining());
            }
        };

        return objectMapper.writeValueAsString(loginResponse.getBody());
    }

    public String getCartShopee() throws JsonProcessingException {
        this.loginShopee();
        var cartRequsetDto = ShopeeCartRequestDto.builder()
                .shopeeTimeFilter(ShopeeTimeFilter.builder().startTime(0).build())
                .preSelectedItemList(new ArrayList<>())
                .version(3)
                .build();

        log.info("CartRequest -> {}",objectMapper.writeValueAsString(cartRequsetDto));
        var cartResponse = restTemplate.postForEntity(SHOPEE_GET_CART, cartRequsetDto,ShopeeCartResponseDto.class);
        log.info("CartResponse -> {}",objectMapper.writeValueAsString(cartResponse));
        responseTrackRepository.save(ResponseTrack.builder().url(SHOPEE_GET_CART)
                .responseStatus(cartResponse.getStatusCode().toString())
                .responseCode(Objects.requireNonNull(cartResponse.getBody()).getErrorCode().toString())
                .responseDescription(cartResponse.getBody().getErrorMessage()).build());

        this.logoutShopee();
        return objectMapper.writeValueAsString(cartResponse.getBody());
    }
}
