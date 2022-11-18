package com.example.kruger.resources;

import java.net.URI;
import com.example.kruger.model.Employee;
import com.example.kruger.model.Response;
import com.example.kruger.model.Vaccineinfo;
import com.example.kruger.service.EmployeeService;
import java.io.IOException;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

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
    private ResponseEntity<Response> addNewOne(@RequestBody Employee employee, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        Response BodyToResponse = new Response();
        Map<String, Object> information = new HashMap<String, Object>();

        try {
            if (employeeService.getRol(authorization).equals("ADMIN")) {
                Employee anEmployee = employeeService.saveNewEmployee(employee);
                
                BodyToResponse.setInfo(anEmployee);
                BodyToResponse.setStatus(HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(BodyToResponse);
                
            } else {
                
                information.put("exception", "There are no permissions to make this request");
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
            }
        } catch (Exception ex) {
            information.put("exception", ex.getCause().getCause().getLocalizedMessage());
            BodyToResponse.setInfo(information);
            BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
        }
    }
    
    //Update Employee -Admin,employee
    @PutMapping
    private ResponseEntity<Response> updateMyInfo(@RequestBody Employee employee, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        Response BodyToResponse = new Response();
        Map<String, Object> information = new HashMap<String, Object>();

        try {
            if (employeeService.getRol(authorization).equals("ADMIN") || employeeService.getRol(authorization).equals("EMPLOYEE")) {
                
                Employee anEmployee = employeeService.updateEmployeeInfo(employee);
                BodyToResponse.setInfo(anEmployee);
                BodyToResponse.setStatus(HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(BodyToResponse);
                
                
            } else {
                
                information.put("exception", "There are no permissions to make this request");
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
            }

        } catch (Exception ex) {
            
            information.put("exception", ex.getCause().getCause().getLocalizedMessage());
            BodyToResponse.setInfo(information);
            BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
        }
    }
    
    //Delete Employee -Admin
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Response> deleteAnEmployee(@PathVariable("id") Long userId, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        Response BodyToResponse = new Response();
        Map<String, Object> information = new HashMap<String, Object>();

        try {
            if (employeeService.getRol(authorization).equals("ADMIN")) {
                
                information.put("exUserId",userId);
                information.put("deleted", employeeService.deleteEmployee(userId));
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(BodyToResponse);
                
            }else{
                
                information.put("exception", "There are no permissions to make this request");
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
            }

        } catch (Exception ex) {
            
            information.put("exception", ex.getCause().getCause().getLocalizedMessage());
            BodyToResponse.setInfo(information);
            BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
        }
    }
    
    //Add Vaccine Info
    @PostMapping("/vaccine/user/{id}")
    private ResponseEntity<Response> addVaccine(@RequestBody Vaccineinfo vaccine, @PathVariable("id") Long userId, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) throws IOException {
        Response BodyToResponse = new Response();
        Map<String, Object> information = new HashMap<String, Object>();
        
        
        if (employeeService.getRol(authorization).equals("ADMIN") || employeeService.getRol(authorization).equals("EMPLOYEE")) {
            
            information = employeeService.addVaccine(vaccine, userId);
            if (information.containsKey("exception")) {
                
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
                
            }
            
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(BodyToResponse);

        }else{
            
                information.put("exception", "There are no permissions to make this request");
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
        }
    }
    
    //get by vaccine status
    @GetMapping("/get/vaccine/status/{status}")
    public ResponseEntity<Response> getByVaccineStatus(@PathVariable("status") int status, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        Response BodyToResponse = new Response();
        Map<String, Object> information = new HashMap<String, Object>();

        try {
            if (employeeService.getRol(authorization).equals("ADMIN")) {
                BodyToResponse.setInfo(employeeService.getByVaccineStatus(status));
                BodyToResponse.setStatus(HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(BodyToResponse);

            } else {

                information.put("exception", "There are no permissions to make this request");
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);

            }

        } catch (Exception ex) {
            information.put("exception", ex.getCause().getCause().getLocalizedMessage());
            BodyToResponse.setInfo(information);
            BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
        }
    }
    
    //get by vaccine type
    @GetMapping("/get/vaccine/type/{vaccineType}")
    public ResponseEntity<Response> getByVaccineType(@PathVariable("vaccineType") String vaccineType, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        Response BodyToResponse = new Response();
        Map<String, Object> information = new HashMap<String, Object>();
        
        try {
            if (employeeService.getRol(authorization).equals("ADMIN")) {
                BodyToResponse.setInfo(employeeService.getByVaccineType(vaccineType));
                BodyToResponse.setStatus(HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(BodyToResponse);

            } else {

                information.put("exception", "There are no permissions to make this request");
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);

            }

        } catch (Exception ex) {
            information.put("exception", ex.getCause().getCause().getLocalizedMessage());
            BodyToResponse.setInfo(information);
            BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
        }
    }
    
    //get by vaccine dates
    @GetMapping("/get/vaccine/dates")
    public ResponseEntity<Response> getByVaccineDate(@RequestBody Map<String, String> vaccine, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) throws ParseException {
        Response BodyToResponse = new Response();
        Map<String, Object> information = new HashMap<String, Object>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin =  sdf.parse(vaccine.get("begin"));
        Date end =  sdf.parse(vaccine.get("end"));

        try {
            if (employeeService.getRol(authorization).equals("ADMIN")) {
                BodyToResponse.setInfo(employeeService.getByVaccineDate(begin,end));
                BodyToResponse.setStatus(HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(BodyToResponse);

            } else {

                information.put("exception", "There are no permissions to make this request");
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);

            }

        } catch (Exception ex) {
            information.put("exception", ex.getCause().getCause().getLocalizedMessage());
            BodyToResponse.setInfo(information);
            BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
        }
        
       
    }
    
    //get all employees
    @GetMapping("/get/all")
    public ResponseEntity<Response> getAllEmployees(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) throws ParseException {
        Response BodyToResponse = new Response();
        Map<String, Object> information = new HashMap<String, Object>();

        try {
            if (employeeService.getRol(authorization).equals("ADMIN")) {
                BodyToResponse.setInfo(employeeService.getAllEmployees());
                BodyToResponse.setStatus(HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(BodyToResponse);

            } else {

                information.put("exception", "There are no permissions to make this request");
                BodyToResponse.setInfo(information);
                BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);

            }

        } catch (Exception ex) {
            information.put("exception", ex.getCause().getCause().getLocalizedMessage());
            BodyToResponse.setInfo(information);
            BodyToResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BodyToResponse);
        }
    }
    
}