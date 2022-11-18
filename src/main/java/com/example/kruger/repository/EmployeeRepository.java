package com.example.kruger.repository;

import com.example.kruger.model.Employee;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Diego.Alava
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    @Query("SELECT e FROM employee  as e WHERE vaccination_status = ?1")
    List<Employee> getByVaccineStatus(int status);
    
    @Query("SELECT e FROM vaccineinfo as v JOIN v.employee as e WHERE v.typeOfVaccine = ?1")
    List<Employee> getByVaccineType(String vaccineType);
    
    @Query("SELECT distinct(e) FROM vaccineinfo as v JOIN v.employee as e WHERE v.vaccineDate BETWEEN ?1 AND ?2")
    List<Employee> getByVaccineDate(Date begin, Date end);
    
    @Query("SELECT e FROM userinfo as u JOIN u.employee as e WHERE u.username = ?1 AND u.password = ?2")
    List<Employee> employeeExists(String username,String password);
    
}