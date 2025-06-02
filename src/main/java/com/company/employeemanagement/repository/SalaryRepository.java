package com.company.employeemanagement.repository;

import com.company.employeemanagement.entity.Salary;
import com.company.employeemanagement.entity.SalaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {
}
