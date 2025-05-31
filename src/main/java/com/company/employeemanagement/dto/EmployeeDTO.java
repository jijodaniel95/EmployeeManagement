package com.company.employeemanagement.dto;

import com.company.employeemanagement.entity.Department;
import com.company.employeemanagement.entity.Role;
import com.company.employeemanagement.entity.Salary;

import java.time.LocalDate;
import java.util.List;

public class EmployeeDTO {
    private Integer empId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate hireDate;
    private DepartmentDTO department;
    private RoleDTO role;
    private List<SalaryDTO> salaries;

    // Getters and setters
    public Integer getEmpId() {
        return empId;
    }
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public LocalDate getHireDate() {
        return hireDate;
    }
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentDTO department){
        this.department = department;
    }
    public RoleDTO getRole() {
        return role;
    }
    public void setRole(RoleDTO role) {
        this.role = role;
    }
    public List<SalaryDTO> getSalaries() {
        return salaries;
    }
    public void setSalaries(List<SalaryDTO> salaries) {
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hireDate=" + hireDate +
                ", department=" + department +
                ", role=" + role +
                ", salaries=" + salaries +
                '}';
    }
}
