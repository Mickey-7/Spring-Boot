package com.example.paginationsortingjpah2.controller;

import com.example.paginationsortingjpah2.model.Employee;
import com.example.paginationsortingjpah2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private static final int PAGE_SIZE = 3;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/employee/page/{pageNo}")
    public Page<Employee> getEmployeePaginated(@PathVariable int pageNo){
        /**
         * Return the Page object containing list of 3 employees of requested
         * page no.
         */
        Pageable pageable = PageRequest.of(pageNo,PAGE_SIZE);
        return employeeRepository.findAll(pageable);
    }

    @GetMapping(value = "/employee/page/sort/{pageNo}")
    public Page<Employee> getEmployeePaginatedAndSorted(@PathVariable int pageNo){
        /**
         * Return the Page object containing list of 3 employees of requested
         * page and sorted by the first name
         */
        Pageable pageableSortedByFirstName = PageRequest.of(pageNo,PAGE_SIZE, Sort.by("firstName"));

        /**
         * Return the Page object containing list of 3 employees of requested
         * page and sorted by the city in descending order
         */
        Pageable pageableSortedByCityDesc = PageRequest.of(pageNo,PAGE_SIZE,Sort.by("city").descending());

        /**
         * Return the Page object containing list of 3 employees of page 0 and
         * sorted by the city in descending order and first name in ascending
         * order
         */
        Pageable pageableSortedByCityDescFirstNameAsc = PageRequest.of(pageNo,PAGE_SIZE,Sort.by("city").descending().and(Sort.by("firstName")));

        // return employeeRepository.findAll(pageableSortedByFirstName);

        // return employeeRepository.findAll(pageableSortedByCityDesc);

        return employeeRepository.findAll(pageableSortedByCityDescFirstNameAsc);

    }

    @GetMapping(value = "/employee/sort")
    public Iterable<Employee> getEmployeeSorted(){
        /**
         * Returns all entities sorted by the given options.
         */
        return employeeRepository.findAll(Sort.by("lastName"));
    }


}
