package com.company.employeemanagement.dto;

public class RoleDTO {
    private Integer roleId;
    private String roleName;
    // Getters and setters
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName
            (String roleName) {
        this.roleName = roleName;
    }
    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

}
