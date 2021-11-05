package com.siloam.restapi.service;

import com.siloam.restapi.dto.EmployeeDto;
import com.siloam.restapi.repository.EmployeeRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MapperFacade mapperFacade;

    public List<EmployeeDto> findAll(){
        return employeeRepository.findAll().stream().map(employee ->
                mapperFacade.map(employee,EmployeeDto.class)).collect(Collectors.toList());
    }
}
