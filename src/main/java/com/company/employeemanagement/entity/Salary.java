package com.company.employeemanagement.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "salaries")
@IdClass(SalaryId.class)
public class Salary {

    @Id
    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @Id
    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    // Getters
    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    // Setters
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    // Composite primary key class
    // This nested class also needs explicit getters, setters, constructors, equals, and hashCode
    public static class SalaryId implements Serializable {
        private Integer employee; // Corresponds to the 'empId' of the Employee entity
        private LocalDate effectiveFrom;

        // Constructors
        public SalaryId() {
        }

        public SalaryId(Integer employee, LocalDate effectiveFrom) {
            this.employee = employee;
            this.effectiveFrom = effectiveFrom;
        }

        // Getters
        public Integer getEmployee() {
            return employee;
        }

        public LocalDate getEffectiveFrom() {
            return effectiveFrom;
        }

        // Setters
        public void setEmployee(Integer employee) {
            this.employee = employee;
        }

        public void setEffectiveFrom(LocalDate effectiveFrom) {
            this.effectiveFrom = effectiveFrom;
        }

        // Equals and HashCode are crucial for composite keys!
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SalaryId salaryId = (SalaryId) o;
            return Objects.equals(employee, salaryId.employee) &&
                    Objects.equals(effectiveFrom, salaryId.effectiveFrom);
        }

        @Override
        public int hashCode() {
            return Objects.hash(employee, effectiveFrom);
        }
    }

    @Override
    public String toString() {
        return "Salary{" +
                ", effectiveFrom=" + effectiveFrom +
                ", salary=" + salary +
                '}';
    }
}
