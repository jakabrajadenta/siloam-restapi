package com.siloam.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String name;
    private String email;
    private String jobTitle;
    private Long trainerId;
    private Long classId;
}
