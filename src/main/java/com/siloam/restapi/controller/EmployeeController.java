package com.siloam.restapi.controller;

import antlr.StringUtils;
import com.siloam.restapi.dto.EmployeeDto;
import com.siloam.restapi.dto.EmployeeRequestDto;
import com.siloam.restapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employee-service")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/filter")
    public Page<EmployeeDto> findAllFilter(EmployeeRequestDto requestDto, Pageable pageable){
        System.out.println("=============================================================");
        Long start = System.currentTimeMillis();
        System.out.println("started at "+LocalDateTime.now());
        Page<EmployeeDto> response = employeeService.findAllFilter(requestDto, pageable);
        Long end = System.currentTimeMillis();
        System.out.println("ended at "+LocalDateTime.now());
        System.out.println("Time: " + (end-start) + "ms");
        return response;
    }

    @GetMapping("/batch")
    public List<EmployeeDto> findAllBatc(){
        System.out.println("=============================================================");
        Long start = System.currentTimeMillis();
        System.out.println("started at "+LocalDateTime.now());
        List<EmployeeDto> response = employeeService.findAllBatch();
        Long end = System.currentTimeMillis();
        System.out.println("ended at "+LocalDateTime.now());
        System.out.println("Time: " + (end-start) + "ms");
        return response;
    }

    @GetMapping
    public List<EmployeeDto> findAll(){
        System.out.println("=============================================================");
        Long start = System.currentTimeMillis();
        System.out.println("started at "+LocalDateTime.now());
        List<EmployeeDto> response = employeeService.findAll();
        Long end = System.currentTimeMillis();
        System.out.println("ended at "+LocalDateTime.now());
        System.out.println("Time: " + (end-start) + "ms");
        soutPhoneNumber();
        return response;
    }

    public void soutPhoneNumber(){
        String ZERO = "0";
        String bin = "7788";
        String[] vaNumber = {"7788000812345678","7788008123456789","7788081234567890","7788812345678901"};
        System.out.println("==========RAW==========");
        for (String va : vaNumber) {
            System.out.println(va);
        }
        System.out.println("==========001==========");
        for (String s : vaNumber) {
            String phoneNumber = s.substring(bin.length());
            Long noZeroPhoneNumber = Long.valueOf(phoneNumber);
            System.out.println(ZERO.concat(String.valueOf(noZeroPhoneNumber)));
        }
        System.out.println("==========002==========");
        for (String s : vaNumber) {
            String phoneNumber = StringUtils.stripFront(s.substring(bin.length()),ZERO);
            System.out.println(ZERO.concat(String.valueOf(phoneNumber)));
        }
        System.out.println("==========003==========");
    }
}
