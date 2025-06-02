package com.company.employeemanagement.mapper;

import com.company.employeemanagement.dto.DepartmentDTO;
import com.company.employeemanagement.entity.Department;

public class DepartmentMapper {
    public static DepartmentDTO toDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDeptId(department.getDeptId());
        dto.setDeptName(department.getDeptName());
        return dto;
    }
}
