package com.atharva.EMP.DB.controllers;
import com.atharva.EMP.DB.DemoEmp;
import com.atharva.EMP.DB.entities.Employee;
import com.atharva.EMP.DB.services.Services;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is controller for the Project It consists all the APIs created
 *
 * @author Atharva
 */
@RestController
@Slf4j
@Validated
public class EmpController {
    /**
     * Here we are doing Field Injection using Autowired to Service layer
     */
    @Autowired
    private Services services;

    /**
     * Its a GET API
     *
     * @return list of all employees
     */
    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees")
    @GetMapping("/getallemployee")
    public ResponseEntity<?> GetAllEmployee() {
        List<Employee> emp = new ArrayList<>();
        try {
            emp = services.GetAllEmployee();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    /**
     * This POST API creates the Employee in the Database
     *
     * @param demoemp Object similar to employee which will be updated entry
     * @return Status Code after Employee inserted
     */

    @Operation(summary = "Add new employee", description = "Creates a new employee in the database")
    @PostMapping("/addemp")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody DemoEmp demoemp) {

        if (!isValidDesignation(demoemp.getDesignation()) || !isValidName(demoemp.getName())) {
            return new ResponseEntity<>("Name or Designation is invalid", HttpStatus.BAD_REQUEST);
        }
        if (demoemp.getSalary() < 0) {
            return new ResponseEntity<>("Salary is invalid", HttpStatus.BAD_REQUEST);
        }

        return services.addEmployee(demoemp);
    }

    /**
     * This is GET API adding users to DB by taking RequesParam
     *
     * @param name        name which to be stored of the employee
     * @param designation designation which to be stored of the employee
     * @param salary      salary which to be stored of the employee
     * @return It returns the added Data and Status Code
     */
    @Operation(summary = "Add employee using request parameters", description = "Creates a new employee using request parameters")
    @GetMapping("/getapi")
    public ResponseEntity<?> addEmployeeByGet(@RequestParam(name = "name") String name, @RequestParam(name = "designation") String designation, @RequestParam(name = "salary") int salary) {
        if (!isValidDesignation(designation) || !isValidName(name)) {
            return new ResponseEntity<>("Name or Designation is invalid", HttpStatus.BAD_REQUEST);
        }
        if (salary < 0) {
            return new ResponseEntity<>("Salary is invalid", HttpStatus.BAD_REQUEST);
        }
        return services.addEmployeeByGet(name, designation, salary);
    }


    /**
     * This API returns the Employee with required ID
     *
     * @param id Id of employee to search
     * @return The Employee for the existing ID
     */
    @Operation(summary = "Find employee by ID", description = "Retrieves an employee by their ID")
    @GetMapping("/findbyid")
    public ResponseEntity<?> findById(@RequestParam(name = "id") int id) {
        return services.findById(id);
    }


    /**
     * This is Delete API to delete employee
     *
     * @param id id of employee which needs to be deleted to be entered
     * @return The employee is deleted or Not
     */
    @Operation(summary = "Delete employee by ID", description = "Deletes an employee by their ID")
    @DeleteMapping("/deleteemp/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") int id) {
        return services.deleteEmployee(id);
    }


    /**
     * This is update API Putmappping
     *
     * @param demo Its object similar to employee used to input data of employee
     * @return Returns status is employee updated or not
     */
    @Operation(summary = "Update employee details", description = "Updates an existing employee's details")
    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody DemoEmp demo) {
        if (!isValidDesignation(demo.getDesignation()) || !isValidName(demo.getName())) {
            return new ResponseEntity<>("Name or Designation is invalid", HttpStatus.BAD_REQUEST);
        }
        if (demo.getSalary() < 0) {
            return new ResponseEntity<>("Salary is invalid", HttpStatus.BAD_REQUEST);
        }
        return services.updateEmployee(demo);
    }

    /**
     * This is test api to see small changes No specific usage
     *
     * @param nam
     * @return
     */
    @Operation(summary = "Test API", description = "Test API for small changes")
    @GetMapping("/test/{nam}")
    public String test(@PathVariable(name = "nam") String nam) {

        return services.test();
    }

    /**
     * This is second Test API made for small testing No specific usage
     *
     * @return Not specific
     */
    @Operation(summary = "Second Test API", description = "Second test API for small testing")

    @GetMapping("/test2")
    public String test2() {

        return "Hello";
    }


    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    private boolean isValidDesignation(String designation) {
        return designation.matches("[a-zA-Z ]+");
    }



    @Operation(summary = "Add more than one employee", description = "Creates many new employee in the database")
    @PostMapping("/addmanyemp")
    public ResponseEntity<?> addManyEmp(@Valid @RequestBody List<DemoEmp> demoEmpList) throws IOException {
        List<String> errors = new ArrayList<>();
        for(DemoEmp demoemp:demoEmpList){
            if (!isValidDesignation(demoemp.getDesignation()) || !isValidName(demoemp.getName())) {
                return new ResponseEntity<>("Name or Designation is invalid for an Entity", HttpStatus.BAD_REQUEST);
            }
            if (demoemp.getSalary() < 0) {
                return new ResponseEntity<>("Salary is invalid for an Entity", HttpStatus.BAD_REQUEST);
            }
        }

        return services.addManyEmp(demoEmpList);
    }

@DeleteMapping("/deleteall")
    public ResponseEntity<?> deleteAll(){
       return services.deleteAll();
}








/*

    @GetMapping("/getallemployee")
    public ResponseEntity<?> GetAllEmployee() {
        List<Employee> emp = new ArrayList<>();
            emp = services.GetAllEmployee();
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }


    @GetMapping("/getemp")
    public List<Employe> getEmployee(){
    //logic//        s1.getemploye


    }
 */













}
