package com.siloam.restapi.controller;

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
        return response;
    }

}
