package com.company.employeemanagement.mapper;

import com.company.employeemanagement.dto.EmployeeDTO;
import com.company.employeemanagement.dto.SalaryDTO;
import com.company.employeemanagement.entity.Employee;
import com.company.employeemanagement.entity.Salary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeMapper.class);

    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) {
            logger.warn("Attempting to map null Employee to DTO");
            return null;
        }

        logger.debug("Mapping Employee entity to DTO - ID: {}", employee.getEmpId());
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

        logger.debug("Successfully mapped Employee to DTO - Name: {} {}", dto.getFirstName(), dto.getLastName());
        return dto;
    }

    public static List<EmployeeDTO> toDTOList(List<Employee> employees) {
        if (employees == null) {
            logger.warn("Attempting to map null Employee list to DTO list");
            return null;
        }

        logger.debug("Mapping {} Employee entities to DTOs", employees.size());
        List<EmployeeDTO> dtos = employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        logger.debug("Successfully mapped {} Employee entities to DTOs", dtos.size());
        return dtos;
    }

    public static List<SalaryDTO> toSalaryDTOList(List<Salary> salaries) {
        if (salaries == null) {
            logger.warn("Attempting to map null Salary list to DTO list");
            return null;
        }

        logger.debug("Mapping {} Salary entities to DTOs", salaries.size());
        List<SalaryDTO> dtos = salaries.stream()
                .map(SalaryMapper::toDTO)
                .collect(Collectors.toList());
        logger.debug("Successfully mapped {} Salary entities to DTOs", dtos.size());
        return dtos;
    }

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        logger.debug("Mapping EmployeeDTO to Entity - Email: {}", employeeDTO.getEmail());
        Employee employee = new Employee();
        employee.setEmpId(employeeDTO.getEmpId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setHireDate(employeeDTO.getHireDate());

        logger.debug("Successfully mapped EmployeeDTO to Entity - Name: {} {}",
                    employeeDTO.getFirstName(), employeeDTO.getLastName());
        return employee;
    }
}
