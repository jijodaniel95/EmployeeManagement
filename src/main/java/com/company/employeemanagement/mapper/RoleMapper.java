package com.company.employeemanagement.mapper;

import com.company.employeemanagement.dto.RoleDTO;
import com.company.employeemanagement.entity.Role;

public class RoleMapper {
    public static RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());
        return dto;
    }
}
