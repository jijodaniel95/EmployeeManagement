package com.company.employeemanagement.repository;

import com.company.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
     @Query("select e.empId from Employee e where e.email=?1")
     Optional<Employee> findByEmail(String email);
}