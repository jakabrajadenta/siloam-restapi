package com.siloam.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {

    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private Long trainerId;
    private Long classId;
}
