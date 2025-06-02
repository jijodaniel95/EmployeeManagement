package com.company.employeemanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 15)
    private String phone;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Salary> salaries=new ArrayList<>();

    // Getters & setters...
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
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department){
        this.department = department;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public List<Salary> getSalaries() {
        return salaries;
    }
    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName
                + '\'' + ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hireDate=" + hireDate +
                ", department=" + department +
                ", role=" + role +
                ", salaries=" + salaries +
                '}';
    }

    public void addSalary(Salary newSalary) {
        salaries.add(newSalary);
        newSalary.setEmployee(this);
    }
}
