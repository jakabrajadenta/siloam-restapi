package com.siloam.restapi.service;

import com.siloam.restapi.dto.EmployeeDto;
import com.siloam.restapi.dto.EmployeeRequestDto;
import com.siloam.restapi.repository.EmployeeRepository;
import com.siloam.restapi.service.genericspecification.GenericSpecificationService;
import com.siloam.restapi.service.genericspecification.model.SearchCriteria;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MapperFacade mapperFacade;

    public Page<EmployeeDto> findAll(EmployeeRequestDto requestDto, Pageable pageable){
        GenericSpecificationService genericSpecificationService = new GenericSpecificationService();
        genericSpecificationService.add(new SearchCriteria<>("id", requestDto.getId(), SearchCriteria.SearchOperation.EQUAL));
        genericSpecificationService.add(new SearchCriteria<>("name", requestDto.getName(), SearchCriteria.SearchOperation.EQUAL));
        genericSpecificationService.add(new SearchCriteria<>("email", requestDto.getEmail(), SearchCriteria.SearchOperation.EQUAL));
        genericSpecificationService.add(new SearchCriteria<>("jobTitle", requestDto.getJobTitle(), SearchCriteria.SearchOperation.EQUAL));
        genericSpecificationService.add(new SearchCriteria<>("trainerId", requestDto.getTrainerId(), SearchCriteria.SearchOperation.EQUAL));
        genericSpecificationService.add(new SearchCriteria<>("classId", requestDto.getClassId(), SearchCriteria.SearchOperation.EQUAL));
//        return employeeRepository.findAll().stream().map(employee ->
//                mapperFacade.map(employee,EmployeeDto.class)).collect(Collectors.toList());
        return employeeRepository.findAll(genericSpecificationService, pageable).map(o ->
                mapperFacade.map(o,EmployeeDto.class));
    }
}
