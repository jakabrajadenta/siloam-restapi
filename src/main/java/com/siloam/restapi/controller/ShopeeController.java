package com.siloam.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siloam.restapi.service.ShopeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopee-service")
public class ShopeeController {

    @Autowired
    private ShopeeService shopeeService;

    @GetMapping("/login")
    public String loginShopee() throws JsonProcessingException {
        return shopeeService.loginShopee();
    }

    @GetMapping("/logout")
    public String logoutShopee() throws JsonProcessingException {
        return shopeeService.logoutShopee();
    }

    @GetMapping("/carts")
    public String getCartsShopee() throws JsonProcessingException {
        return shopeeService.getCartShopee();
    }
}
