package com.atharva.EMP.DB;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Demo EMP is used to get the Employee data from input where we use RequesBody anootation
 *
 * @author Atharva
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoEmp {
    private int id;
    private String name;
    private String designation;
    @Positive
    private int salary;

    public DemoEmp(String name, String designation, int salary) {
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }
}