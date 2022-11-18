package com.example.kruger.resources;

import java.net.URI;
import com.example.kruger.model.Employee;
import com.example.kruger.model.Vaccineinfo;
import com.example.kruger.service.EmployeeService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego.Alava
 */
@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeRESOURCES {
    
    @Autowired
    private EmployeeService employeeService;
     
    
    //Add new Employee -Admin
    @PostMapping
    private ResponseEntity<Employee> addNewOne(@RequestBody Employee employee) {
        Employee anEmployee = employeeService.saveNewEmployee(employee);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(anEmployee);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    //Update Employee -Admin,employee
    @PutMapping
    private ResponseEntity<Employee> updateMyInfo(@RequestBody Employee employee) {
        Employee anEmployee = employeeService.updateEmployeeInfo(employee);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(anEmployee);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    //Delete Employee -Admin
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Object> deleteAnEmployee(@PathVariable("id") Long userId) {
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            response.put("exUserId",userId);
            response.put("deleted", employeeService.deleteEmployee(userId));
            return ResponseEntity.status(HttpStatus.OK).body(response);
            
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    //Add Vaccine Info
    @PostMapping("/vaccine/user/{id}")
    private ResponseEntity<Object> addVaccine(@RequestBody Vaccineinfo vaccine, @PathVariable("id") Long userId) {
        
        Map<String, Object> response = employeeService.addVaccine(vaccine, userId);
        if (response.containsKey("exception")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
        
    }
    
    //get by vaccine status
    @GetMapping("/get/vaccine/status/{status}")
    public List<Employee> getByVaccineStatus(@PathVariable("status") int status) {
        return employeeService.getByVaccineStatus(status);
    }
    
    //get by vaccine type
    @GetMapping("/get/vaccine/type/{vaccineType}")
    public List<Employee> getByVaccineType(@PathVariable("vaccineType") String vaccineType) {
        return employeeService.getByVaccineType(vaccineType);
    }
    
    //get by vaccine dates
    @GetMapping("/get/vaccine/dates")
    public List<Employee> getByVaccineDate(@RequestBody Map<String, String> vaccine) throws ParseException {
        
        log.info(vaccine.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin =  sdf.parse(vaccine.get("begin"));
        Date end =  sdf.parse(vaccine.get("end"));

        log.info("fecha de inicio"+vaccine.get("begin"));
        log.info("fecha fin"+vaccine.get("end"));
        return employeeService.getByVaccineDate(begin,end);
    }
    
    //get all employees
    @GetMapping("/get/all")
    public List<Employee> getAllEmployees() throws ParseException {
        return employeeService.getAllEmployees();
    }
    
}