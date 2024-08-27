package com.atharva.EMP.DB.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class employeeDTO {
    private String name;
    private String Designation;
    private Integer salary;
}
