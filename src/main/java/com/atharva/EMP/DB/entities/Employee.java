package com.atharva.EMP.DB.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * Represents an employee entity in the database.
 *
 * @author Atharva
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EmployeeDatabase")
@Entity

public class Employee {
    /**
     * Primary Key and ID of employee
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Employee_id")
    @SequenceGenerator(name = "Employee_id", sequenceName = "Employee_id", allocationSize = 1)
    private Integer eid;
    /**
     * Name of Employee
     */
    private String name;

    /**
     * Designation of Employee
     */
    private String Designation;
    /**
     * Salary of Employee
     */
    @Positive
    @JsonProperty("Employee_salary")
    private Integer salary;

    public Integer getEid() {
        return eid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Employee(String name, String designation, Integer salary) {
        this.name = name;
        Designation = designation;
        this.salary = salary;
    }
}

/*
Sql- name uid age
DB scehema = uid, name , age

@Entity
@Table
@Data
@nOaRGScONSTRUCTOER
@AllArgsConstructor
@Getter
@Setter
public class student(){

@ID
Integer uid;

employee(){}
String name;

private Integer age;
// e1= Employe =new wmploye(abc)
}//// Servicde age =10 , e1.getage
 */
