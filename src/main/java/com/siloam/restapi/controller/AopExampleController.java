package com.siloam.restapi.controller;

import com.siloam.restapi.service.AopExamplesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
@RequestMapping("/aop-service")
public class AopExampleController {

    @Autowired
    private AopExamplesService aopExamplesService;

    @GetMapping("/encrypt")
    public String getMessageController(@RequestBody String message) throws IOException {
        return aopExamplesService.getMessage(message);
    }

    @GetMapping("/decrypt")
    public String decryptMessage(@RequestBody String message) throws IOException {
        return aopExamplesService.decrypt(message);
    }

}
