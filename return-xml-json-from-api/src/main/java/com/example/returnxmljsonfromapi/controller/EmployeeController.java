package com.example.returnxmljsonfromapi.controller;

import com.example.returnxmljsonfromapi.dto.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping(
            value = "/entityEmployees",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_XML_VALUE
            }
    )
    public ResponseEntity<List<Employee>> getEmployeesEntity(){
        return new ResponseEntity<>(employees(), HttpStatus.OK);
    }

    private List<Employee> employees() {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Developer"),
                new Employee("Michael", "Sr Developer"),
                new Employee("Harris", "Developer"),
                new Employee("Kala", "Sr Developer"),
                new Employee("Jerome", "Manager")
        );
        return employees;
    }

    @GetMapping(
            value = "/employees",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_XML_VALUE
            }
    )
    public List<Employee> getEmployees(){
        return employees();
    }

    @GetMapping(
            value = "/entityEmployee",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_XML_VALUE
            }
    )
    public ResponseEntity<Employee> getEmployeeEntity(){
        return new ResponseEntity<>(new Employee("John","Developer"),HttpStatus.OK);
    }

    @GetMapping(
            value = "/employee",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_XML_VALUE
            }
    )
    public Employee getEmployee(){
        return new Employee("John", "Developer");
    }

}
