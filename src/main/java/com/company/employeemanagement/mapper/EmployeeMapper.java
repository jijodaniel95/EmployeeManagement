package com.company.employeemanagement.mapper;

import com.company.employeemanagement.dto.EmployeeDTO;
import com.company.employeemanagement.dto.SalaryDTO;
import com.company.employeemanagement.entity.Employee;
import com.company.employeemanagement.entity.Salary;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpId(employee.getEmpId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setHireDate(employee.getHireDate());
        dto.setSalaries(employee.getSalaries().stream().map(SalaryMapper::toDTO).collect(Collectors.toList()));
        dto.setDepartment(DepartmentMapper.toDTO(employee.getDepartment()));
        dto.setRole(RoleMapper.toDTO(employee.getRole()));
        return dto;
    }

    public static List<EmployeeDTO> toDTOList(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public  static List<SalaryDTO> toSalaryDTOList(List<Salary> salaries) {
        return salaries.stream()
                .map(SalaryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
