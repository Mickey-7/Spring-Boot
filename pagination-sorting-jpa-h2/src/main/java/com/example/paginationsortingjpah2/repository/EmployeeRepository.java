package com.example.paginationsortingjpah2.repository;

import com.example.paginationsortingjpah2.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {
}
