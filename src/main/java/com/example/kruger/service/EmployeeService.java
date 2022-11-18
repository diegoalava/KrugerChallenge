package com.example.kruger.service;

import java.util.List;
import com.example.kruger.model.Employee;
import com.example.kruger.model.Userinfo;
import com.example.kruger.model.Vaccineinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kruger.repository.EmployeeRepository;
import com.example.kruger.repository.UserinfoRepository;
import com.example.kruger.repository.VaccineinfoRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Diego.Alava
 */
@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Autowired
    private VaccineinfoRepository vaccineinfoRepository;
    
    
    //save a new employee method
    public Employee saveNewEmployee(Employee emp){
        Employee insertedEmployee = employeeRepository.save(emp);
        String username = insertedEmployee.getEmail();
        String password = insertedEmployee.getDni().toString();
        
        Userinfo userinfo = new Userinfo(username, password, insertedEmployee);
        userinfoRepository.save(userinfo);
        
        return insertedEmployee;
    }
    
    //update an employee info
    public Employee updateEmployeeInfo(Employee emp){
       Employee thisEmployee = employeeRepository.getById(emp.getUserId());
       thisEmployee.setBirthDate(emp.getBirthDate());
       thisEmployee.setHomeAddress(emp.getHomeAddress());
       thisEmployee.setPhoneNumber(emp.getPhoneNumber());
       thisEmployee.setVaccinationStatus(emp.getVaccinationStatus());
       
       
       log.info(thisEmployee.getHomeAddress());
       
       return employeeRepository.save(thisEmployee);
    }
    
    //datele an existing employee
    public boolean deleteEmployee(Long userId) {
        try {
            employeeRepository.deleteById(userId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    //add Vaccine Info
    public Map<String, Object> addVaccine(Vaccineinfo vaccine, Long userId) {
        Employee thisEmployee = employeeRepository.getById(userId);
        Vaccineinfo vaccineToInsert = new Vaccineinfo(vaccine.getTypeOfVaccine(), vaccine.getVaccineDate(), thisEmployee);
        Map<String, Object> response = new HashMap<String, Object>();
        
        try {

            vaccineinfoRepository.save(vaccineToInsert);
            response.put("userId", userId);
            response.put("typeOfVaccine", vaccineToInsert.getTypeOfVaccine());
            response.put("vaccineDate", vaccineToInsert.getVaccineDate());
            return response;

        } catch (Exception ex) {
            response.put("exception", ex.getCause().getCause().getLocalizedMessage());
            return response;
        }
    }
    
    //get by vaccine status
    public List<Employee> getByVaccineStatus(int status) {
        List<Employee> employeesList = employeeRepository.getByVaccineStatus(status);
        return employeesList;
    }
    
    //get by vaccine type
    public List<Employee> getByVaccineType(String vaccineType) {
        List<Employee> employeesList = employeeRepository.getByVaccineType(vaccineType);
        return employeesList;
    }
    
    //get by vaccine dates
    public List<Employee> getByVaccineDate(Date begin, Date end){
        List<Employee> employeesList = employeeRepository.getByVaccineDate(begin,end);
        return employeesList;
    }
    
    //get all employees
    public List<Employee> getAllEmployees(){
        List<Employee> employeesList = employeeRepository.findAll();
        return employeesList;
    }
}
