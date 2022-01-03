package com.siloam.restapi.controller;

import com.siloam.restapi.service.AopExamplesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/aop-service")
public class AopExampleController {

    @Autowired
    private AopExamplesService aopExamplesService;

    @GetMapping
    public String getMessageController(@RequestBody String message){
        return aopExamplesService.getMessage(message);
    }

}
