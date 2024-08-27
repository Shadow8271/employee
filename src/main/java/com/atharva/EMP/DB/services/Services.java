package com.atharva.EMP.DB.services;
import com.atharva.EMP.DB.DTO.employeeDTO;
import com.atharva.EMP.DB.DemoEmp;
import com.atharva.EMP.DB.entities.Employee;
import com.atharva.EMP.DB.mapper.employeeMapper;
import com.atharva.EMP.DB.repositories.Repositories;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This is Service Layer of application which contains business Logic for all APIs
 *
 * @author Atharva
 */
@Service
public class Services {
    /**
     * Here we are Injecting Repository using field Injection
     */
    @Autowired
    Repositories repositories;

   @Autowired
    employeeMapper employeeMapper;

    /**
     * Logic to update Employee
     *
     * @param demo Object similar to Employee which will be updated
     * @return Returns the Updated Employee with status code
     */
    public ResponseEntity<?> updateEmployee(DemoEmp demo) {


        Optional<Employee> opcurrent = repositories.findById(demo.getId());
        if (opcurrent.isPresent()) {
            Employee current = opcurrent.get();
            current.setName(demo.getName());
            current.setDesignation(demo.getDesignation());
            current.setSalary(demo.getSalary());
            repositories.save(current);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>("Not Present", HttpStatus.BAD_REQUEST);


    }


    /**
     * Adds an employee to the database based on the provided data from the DemoEmp object using json.
     *
     * @param demoemp The DemoEmp object containing the data of the employee to be added.
     * @return A ResponseEntity containing the added Employee object with HTTP status code 201 (Created).
     */
    public ResponseEntity<?> addEmployee(DemoEmp demoemp) {
        // Create a new Employee object and populate it with data from the DemoEmp object
        Employee e1 = new Employee();
     //   e1.setEid(demoemp.getId());
        e1.setName(demoemp.getName());
        e1.setDesignation(demoemp.getDesignation());
        e1.setSalary(demoemp.getSalary());
        // Save the Employee object to the database
        repositories.save(e1);
        // Return a ResponseEntity with the added Employee object and HTTP status code 201 (Created)
        return new ResponseEntity<>(e1, HttpStatus.CREATED);
    }

    /**
     * Retrieves all employees from the database.
     *
     * @return A list of all employees.
     */

    public List<Employee> GetAllEmployee() {
        return repositories.findAll();
    }

    /**
     * Adds an employee to the database with the provided data.
     *

     * @param name        The name of the employee.
     * @param designation The designation of the employee.
     * @param salary      The salary of the employee.
     * @return A ResponseEntity containing a message with the details of the added employee and HTTP status code 201 (Created).
     */

    public ResponseEntity<?> addEmployeeByGet(String name, String designation, int salary) {
        Employee e1 = new Employee( name, designation, salary);
        repositories.save(e1);
        return new ResponseEntity<>("Userid" + e1.getEid() + " UserName" + e1.getName() + " User Desgn" + e1.getDesignation() + " User Salasy" + e1.getSalary(), HttpStatus.CREATED);
    }

    /**
     * Retrieves an employee from the database by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return A ResponseEntity containing the retrieved employee if found, or a message indicating not found with HTTP status code 404 (Not Found).
     */
    public ResponseEntity<?> findById(int id) {
        Optional<Employee> e2 = repositories.findById(id);

        if (e2.isPresent()) return new ResponseEntity<>(e2, HttpStatus.ACCEPTED);

        else return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);

    }


    /**
     * Deletes an employee from the database by ID.
     *
     * @param id The ID of the employee to delete.
     * @return A ResponseEntity containing a message indicating success or failure of the deletion with HTTP status code 204 (No Content) or 400 (Bad Request) respectively.
     */
    public ResponseEntity<?> deleteEmployee(int id) {

        Optional<Employee> opcurrent = repositories.findById(id);
        if(opcurrent.isPresent()) {
            repositories.deleteAllById(Collections.singleton(id));
            return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
        }
        else
            return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);

    }


    /**
     * It's a test API used in start No business Logic
     *
     * @return Nothing specific
     */
    public String test() {
        Employee e2 = new Employee();
        e2.setEid(133);
        e2.setName("bantya");
        e2.setSalary(500);
        e2.setDesignation("abc");
        repositories.save(e2);
        return "Done";
    }


    public ResponseEntity<?> addManyEmp(List<DemoEmp> demoEmpList) throws IOException {
     List<employeeDTO> ep = new ArrayList<employeeDTO>();

        for(DemoEmp demo:demoEmpList){
            Employee e1=new Employee(demo.getName(),demo.getDesignation(),demo.getSalary());
            repositories.save(e1);
         ep.add(employeeMapper.mapper(e1));

        }

        return new ResponseEntity<>(ep,HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteAll() {

        repositories.deleteAll();
        return new ResponseEntity<>("Done deleting All", HttpStatus.OK);

    }
}
