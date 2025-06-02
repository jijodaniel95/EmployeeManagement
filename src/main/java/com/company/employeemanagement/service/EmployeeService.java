package com.company.employeemanagement.service;

import com.company.employeemanagement.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    List<EmployeeDTO> getAllEmployeesByPagination(int pageNo, int pageSize);
    EmployeeDTO getEmployeeById(Integer id);
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
}
