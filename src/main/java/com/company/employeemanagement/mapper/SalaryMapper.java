package com.company.employeemanagement.mapper;

import com.company.employeemanagement.dto.SalaryDTO;
import com.company.employeemanagement.entity.Salary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SalaryMapper {
    private static final Logger logger = LoggerFactory.getLogger(SalaryMapper.class);

    public static SalaryDTO toDTO(Salary salary) {
        if (salary == null) {
            logger.warn("Attempting to map null Salary to DTO");
            return null;
        }

        logger.debug("Mapping Salary entity to DTO - Amount: {}, Date: {}",
                    salary.getSalary(), salary.getEffectiveFrom());
        SalaryDTO dto = new SalaryDTO();
        dto.setSalary(salary.getSalary());
        dto.setEffectiveFrom(salary.getEffectiveFrom());

        logger.debug("Successfully mapped Salary to DTO");
        return dto;
    }

    public static List<SalaryDTO> toDTOList(List<Salary> salaries) {
        if (salaries == null) {
            logger.warn("Attempting to map null Salary list to DTO list");
            return null;
        }

        logger.debug("Mapping {} Salary entities to DTOs", salaries.size());
        List<SalaryDTO> dtos = salaries.stream()
                .map(SalaryMapper::toDTO)
                .collect(Collectors.toList());
        logger.debug("Successfully mapped {} Salary entities to DTOs", dtos.size());
        return dtos;
    }

    public static List<Salary> toEntityList(List<SalaryDTO> salaryDTOs) {
        if (salaryDTOs == null) {
            logger.warn("Attempting to map null SalaryDTO list to Entity list");
            return null;
        }

        logger.debug("Mapping {} SalaryDTOs to entities", salaryDTOs.size());
        List<Salary> entities = salaryDTOs.stream()
                .map(SalaryMapper::toEntity)
                .collect(Collectors.toList());
        logger.debug("Successfully mapped {} SalaryDTOs to entities", entities.size());
        return entities;
    }

    public static Salary toEntity(SalaryDTO salaryDTO) {
        logger.debug("Mapping SalaryDTO to Entity - Amount: {}, Date: {}",
                    salaryDTO.getSalary(), salaryDTO.getEffectiveFrom());
        Salary salary = new Salary();
        salary.setSalary(salaryDTO.getSalary());
        salary.setEffectiveFrom(salaryDTO.getEffectiveFrom());

        logger.debug("Successfully mapped SalaryDTO to Entity");
        return salary;
    }
}
