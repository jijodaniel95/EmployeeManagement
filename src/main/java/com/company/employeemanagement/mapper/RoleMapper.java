package com.company.employeemanagement.mapper;

import com.company.employeemanagement.dto.RoleDTO;
import com.company.employeemanagement.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleMapper {
    private static final Logger logger = LoggerFactory.getLogger(RoleMapper.class);

    public static RoleDTO toDTO(Role role) {
        if (role == null) {
            logger.debug("Attempting to map null Role to DTO");
            return null;
        }

        logger.debug("Mapping Role entity to DTO - ID: {}, Name: {}", role.getRoleId(), role.getRoleName());
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());

        logger.debug("Successfully mapped Role to DTO");
        return dto;
    }
}
