package com.atharva.EMP.DB.mapper;

import com.atharva.EMP.DB.DTO.employeeDTO;
import com.atharva.EMP.DB.entities.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class employeeMapper {
    private ObjectMapper objectMapper;

    public employeeDTO mapper(Employee employee) throws IOException {
        return objectMapper.convertValue(employee,employeeDTO.class);
    }
}
