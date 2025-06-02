package com.company.employeemanagement.mapper;

import com.company.employeemanagement.dto.DepartmentDTO;
import com.company.employeemanagement.entity.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentMapper {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentMapper.class);

    public static DepartmentDTO toDTO(Department department) {
        if (department == null) {
            logger.debug("Attempting to map null Department to DTO");
            return null;
        }

        logger.debug("Mapping Department entity to DTO - ID: {}, Name: {}",
                    department.getDeptId(), department.getDeptName());
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDeptId(department.getDeptId());
        dto.setDeptName(department.getDeptName());

        logger.debug("Successfully mapped Department to DTO");
        return dto;
    }
}
