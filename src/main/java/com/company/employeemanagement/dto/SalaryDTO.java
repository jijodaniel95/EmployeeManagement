package com.company.employeemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalaryDTO {
    private BigDecimal salary;
    private LocalDate effectiveFrom;

    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }
    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
    @Override
    public String toString() {
        return "SalaryDTO{" +
                ", salary=" + salary +
                ", effectiveFrom=" + effectiveFrom +
                '}';
    }
}
