package com.atharva.EMP.DB.repositories;


import com.atharva.EMP.DB.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Employee entities in the database.
 *
 * @author Atharva
 */
@Repository
public interface Repositories extends JpaRepository<Employee, Integer> {


    //ORM- Object relational Mapping==  Employe== Services MApping
}
