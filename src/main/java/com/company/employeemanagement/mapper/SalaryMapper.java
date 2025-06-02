package com.company.employeemanagement.mapper;

import com.company.employeemanagement.dto.SalaryDTO;
import com.company.employeemanagement.entity.Salary;

import java.util.List;
import java.util.stream.Collectors;

public class SalaryMapper {

    public static SalaryDTO toDTO(Salary salary) {
        SalaryDTO dto = new SalaryDTO();
        dto.setSalary(salary.getSalary());
        dto.setEffectiveFrom(salary.getEffectiveFrom());
        return dto;
    }
    public static List<SalaryDTO> toDTOList(List<Salary> salaries) {
        return salaries.stream()
                .map(SalaryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
