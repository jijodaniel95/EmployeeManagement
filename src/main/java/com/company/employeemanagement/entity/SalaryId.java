package com.company.employeemanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SalaryId implements Serializable {

    private Integer employee;
    private LocalDate effectiveFrom;

    public SalaryId() {}

    public SalaryId(Integer employee, LocalDate effectiveFrom) {
        this.employee = employee;
        this.effectiveFrom = effectiveFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalaryId)) return false;
        SalaryId that = (SalaryId) o;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(effectiveFrom, that.effectiveFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, effectiveFrom);
    }
}
